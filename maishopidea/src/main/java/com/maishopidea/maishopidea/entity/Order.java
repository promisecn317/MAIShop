package com.maishopidea.maishopidea.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private int userId;
    @OneToMany
    @JoinColumn(name = "orderId")
    private List<Product> orderItems = new ArrayList<>();

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Product> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Product> orderItems) {
        this.orderItems = orderItems;
    }

}
