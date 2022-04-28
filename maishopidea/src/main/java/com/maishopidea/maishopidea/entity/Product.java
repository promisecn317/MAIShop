package com.maishopidea.maishopidea.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (unique=true)
    private int productId;
    private String productName;
    private String productDescription;
    private Date createdDate;
    private double productPrice;
    private int productQty;
    private boolean sellable;
    private int userId;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "longblob")
    private byte[] productImage;

    //Constructor method for new
    public Product() {}

    public double getProductPrice() { return productPrice; }

    public void setProductPrice(double productPrice) { this.productPrice = productPrice; }

    public int getProductQty() { return productQty; }

    public void setProductQty(int productQty) { this.productQty = productQty; }

    public boolean isSellable() { return sellable;  }

    public void setSellable(boolean sellable) { this.sellable = sellable; }

    public byte[] getProductImage() { return productImage; }

    public void setProductImage(byte[] productPicture) { this.productImage = productPicture; }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productInformation) {
        this.productDescription = productInformation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
