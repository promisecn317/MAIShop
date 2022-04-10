package com.maishopidea.maishopidea.entity;


import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int userId;
    private String username;
    private String email;
    private String password;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    //@Column(name = "userAvatar", columnDefinition = "longblob", nullable = true)
    private byte[] userAvatar;
    //@ManyToMany(fetch=FetchType.EAGER) //EAGER means when access user, the following will be auto accessed
//    @OneToMany(fetch=FetchType.LAZY,targetEntity = Product.class)
//    @JoinColumn(name = "product", columnDefinition = "longblob")
//    private List<Product> products;





    public User() {

    }

    public User(String userEmail, String password) {

        this.email = userEmail;
        this.password = password;

    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String userEmail) {
        this.email = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String userPassword) {
        this.password = userPassword;
    }

    public byte[] getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(byte[] userAvatar) {
        this.userAvatar = userAvatar;
    }

}
