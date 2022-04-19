package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.VerifyStatus;
import com.maishopidea.maishopidea.repo.VerifyStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyStatusServiceImpl implements VerifyStatusService{

    private VerifyStatusRepo verifyRepo;
    @Autowired
    public void setVerifyRepo(VerifyStatusRepo verifyRepo){
        this.verifyRepo = verifyRepo;
    }

    @Override
    public String saveEmailAndCode(VerifyStatus status){
        return verifyRepo.save(status).getEmail();
    }

    @Override
    public String getVerifyCode(String address) {
        return verifyRepo.findVerifyStatusByEmail(address).getVerifyCode();
    }

    @Override
    public boolean compareCode(String inputCode, String verifyCode) {
        return inputCode.equals(verifyCode);
    }
}
