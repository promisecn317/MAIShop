package com.maishopidea.maishopidea.controller;

import com.maishopidea.maishopidea.entity.VerifyEmail;
import com.maishopidea.maishopidea.entity.VerifyStatus;
import com.maishopidea.maishopidea.service.VerifyEmailService;
import com.maishopidea.maishopidea.service.VerifyStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyEmailController {

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

    @GetMapping(value = "verify_email")
    public String getVerifyEmail(@RequestParam("address") String address, VerifyEmail info, VerifyStatus status){
        info.setTo(address);
        status.setEmail(address);

        String verifyCode = emailService.generateCode();
        info.setVerifyCode(verifyCode);
        status.setVerifyCode(verifyCode);

        String thisEmail = verifyService.saveEmailAndCode(status);

        emailService.sendVerifyEmail(info);
        return "Verify email has been sent to " + thisEmail;
    }

}
