package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.Cart;
import com.maishopidea.maishopidea.entity.CartItem;


public interface CartService {
    public void addItem(CartItem cartItem);

    int saveCart(Cart cart);


    public Integer getTotalCount();

    public Cart findCart(int cartId);
}
