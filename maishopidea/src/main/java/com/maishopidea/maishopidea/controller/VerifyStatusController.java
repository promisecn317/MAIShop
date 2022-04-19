package com.maishopidea.maishopidea.controller;

import com.maishopidea.maishopidea.service.VerifyStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyStatusController {

    private VerifyStatusService verifyService;
    @Autowired
    public void setVerifyService(VerifyStatusService verifyService){
        this.verifyService = verifyService;
    }

    //http://localhost:1989/verify_info?address=kaize.yao19@student.xjtlu.edu.cn&code=1928224
    @GetMapping(value = "verify_info")
    public boolean verifyStatus(@RequestParam("address") String address, @RequestParam("code") String inputCode){

        String verifyCode = verifyService.getVerifyCode(address);
        return verifyService.compareCode(inputCode, verifyCode);
    }

}
