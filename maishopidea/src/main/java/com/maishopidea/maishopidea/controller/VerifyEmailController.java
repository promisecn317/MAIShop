package com.maishopidea.maishopidea.controller;

import com.maishopidea.maishopidea.entity.User;
import com.maishopidea.maishopidea.entity.VerifyEmail;
import com.maishopidea.maishopidea.entity.VerifyStatus;
import com.maishopidea.maishopidea.service.UserService;
import com.maishopidea.maishopidea.service.VerifyEmailService;
import com.maishopidea.maishopidea.service.VerifyStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyEmailController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService){this.userService = userService;};

    private VerifyEmailService emailService;
    @Autowired
    public void setEmailService(VerifyEmailService emailService){
        this.emailService = emailService;
    }

    private VerifyStatusService verifyService;
    @Autowired
    public void setVerifyService(VerifyStatusService verifyService){
        this.verifyService = verifyService;
    }

    @PostMapping(value = "/user/verify_email")
    public ResponseEntity getVerifyEmail(@RequestBody User user, VerifyEmail info, VerifyStatus status) {
        String email = user.getEmail();
        User checkUser = userService.getUser(email);
        if (checkUser != null) {
            return new ResponseEntity("Email already registered.", HttpStatus.CONFLICT);
        }

        info.setTo(email);
        status.setEmail(email);

        String verifyCode = emailService.generateCode();
        info.setVerifyCode(verifyCode);
        status.setVerifyCode(verifyCode);

        VerifyStatus verifyStatus = verifyService.getVerifyStatusByEmail(status.getEmail());
        if (verifyStatus == null) {
            verifyService.saveEmailAndCode(status);
        } else {
            status.setId(verifyStatus.getId());
            verifyService.saveEmailAndCode(status);
        }

        emailService.sendVerifyEmail(info);
        return new ResponseEntity("Success", HttpStatus.OK);
    }

}
