package com.maishopidea.maishopidea.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    //@Column(name = "userAvatar", columnDefinition = "longblob", nullable = true)
    private byte[] userAvatar;
    //@ManyToMany(fetch=FetchType.EAGER) //EAGER means when access user, the following will be auto accessed
    @OneToMany(fetch=FetchType.LAZY,targetEntity = Product.class)
    @JoinColumn(name = "product", columnDefinition = "longblob")
    private List<Product> products;





    public User() {

    }

    public User(String userEmail, String userPassword) {

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

    public byte[] getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(byte[] userAvatar) {
        this.userAvatar = userAvatar;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
