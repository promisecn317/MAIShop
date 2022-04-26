package com.maishopidea.maishopidea.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true)
    private int userId;
    @Column(name = "user_name")
    private String userName;
    @Column(unique=true)
    private String email;
    private String password;
    private String userGender;
    @Lob
    @Basic(fetch = FetchType.LAZY)
//    @Column(name = "userAvatar", columnDefinition = "longblob", nullable = true)
    private byte[] userAvatar;
//    @ManyToMany(fetch=FetchType.EAGER) //EAGER means when access user, the following will be auto accessed
//    @OneToMany(fetch=FetchType.LAZY,targetEntity = Product.class)
//    @JoinColumn(name = "product", columnDefinition = "longblob")
//    private List<Product> products;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @OneToMany(mappedBy = "user")
    private List<Product> sellerProducts;

    public User() {}

    public User(int userId, String username, String email, String password, String userGender, byte[] userAvatar, Cart cart) {
        this.userId = userId;
        this.userName = username;
        this.email = email;
        this.password = password;
        this.userGender = userGender;
        this.userAvatar = userAvatar;
        this.cart = cart;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public byte[] getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(byte[] userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {this.cart = cart;}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Product> getSellerProducts() {
        return sellerProducts;
    }

    public void setSellerProducts(List<Product> sellerProducts) {
        this.sellerProducts = sellerProducts;
    }

}

