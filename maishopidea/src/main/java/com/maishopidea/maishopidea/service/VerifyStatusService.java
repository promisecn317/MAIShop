package com.maishopidea.maishopidea.service;


import com.maishopidea.maishopidea.entity.VerifyStatus;

public interface VerifyStatusService {
    void saveEmailAndCode(VerifyStatus status);
    String getVerifyCode(String address);
    boolean compareCode(String inputCode, String verifyCode);
    VerifyStatus getVerifyStatusByEmail(String email);
}
