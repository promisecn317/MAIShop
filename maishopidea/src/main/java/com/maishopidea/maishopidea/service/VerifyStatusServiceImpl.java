package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.VerifyStatus;
import com.maishopidea.maishopidea.repo.VerifyStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerifyStatusServiceImpl implements VerifyStatusService{

    private VerifyStatusRepo verifyRepo;
    @Autowired
    public void setVerifyRepo(VerifyStatusRepo verifyRepo){
        this.verifyRepo = verifyRepo;
    }

    @Override
    public void saveEmailAndCode(VerifyStatus status){
        verifyRepo.save(status);
    }

    @Override
    public String getVerifyCode(String address) {
        return verifyRepo.findByEmail(address).getVerifyCode();
    }

    @Override
    public boolean compareCode(String inputCode, String verifyCode) {
        return inputCode.equals(verifyCode);
    }

    @Override
    public VerifyStatus getVerifyStatusByEmail(String email) {
        Optional<VerifyStatus> verifyStatus = Optional.ofNullable(verifyRepo.findByEmail(email));
        return verifyStatus.orElse(null);
    }


}
