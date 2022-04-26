package com.maishopidea.maishopidea.entity;

import javax.persistence.*;

@Entity
public class VerifyStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(unique = true)
    private String email;
    private String verifyCode;

    public int getId() {return Id;}

    public void setId(int id) {this.Id = id;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailAddress) {
        this.email = emailAddress;
    }

    public String getVerifyCode() {return verifyCode;}

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

}
