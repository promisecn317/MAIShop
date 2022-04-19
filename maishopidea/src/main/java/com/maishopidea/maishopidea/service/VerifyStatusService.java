package com.maishopidea.maishopidea.service;


import com.maishopidea.maishopidea.entity.VerifyStatus;

public interface VerifyStatusService {
    String saveEmailAndCode(VerifyStatus status);
    String getVerifyCode(String address);
    boolean compareCode(String inputCode, String verifyCode);
}
