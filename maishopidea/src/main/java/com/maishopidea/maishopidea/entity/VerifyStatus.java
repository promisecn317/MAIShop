package com.maishopidea.maishopidea.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VerifyStatus {
    @Id
    private String email;
    private String verifyCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailAddress) {
        this.email = emailAddress;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

}
