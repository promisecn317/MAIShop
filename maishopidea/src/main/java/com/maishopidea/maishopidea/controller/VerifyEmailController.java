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
    public ResponseEntity getVerifyEmail(@RequestParam("email") String address, VerifyEmail info, VerifyStatus status) {
        User user = userService.getUser(address);
        if (user != null) {
            return new ResponseEntity("Email already registered.", HttpStatus.CONFLICT);
        }

        info.setTo(address);
        status.setEmail(address);

        String verifyCode = emailService.generateCode();
        info.setVerifyCode(verifyCode);
        status.setVerifyCode(verifyCode);

        verifyService.saveEmailAndCode(status);

        emailService.sendVerifyEmail(info);
        return new ResponseEntity("Success", HttpStatus.OK);
    }

}
