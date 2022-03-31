package com.maishopidea.maishopidea.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
//
//    private String userInputName;
//    private String userInputEmail;
//    private String userInputPassword;

    public user() {

    }

    public user( String userEmail, String userPassword) {

        this.userEmail = userEmail;
        this.userPassword = userPassword;

    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /** user input info*/
//    public String getUserInputName() {
//        return userInputName;
//    }
//
//    public void setUserInputName(String userInputName) {
//        this.userInputName = userInputName;
//    }
//
//    public String getUserInputEmail() {
//        return userInputEmail;
//    }
//
//    public void setUserInputEmail(String userInputEmail) {
//        this.userInputEmail = userInputEmail;
//    }
//
//    public String getUserInputPassword() {
//        return userInputPassword;
//    }
//
//    public void setUserInputPassword(String userInputPassword) {
//        this.userInputPassword = userInputPassword;
//    }


}
