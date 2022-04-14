package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.CartItem;

import java.util.List;


public interface CartItemService {
    public void deleteItem(CartItem cartItem);

    void saveCartItem(List<CartItem> cartItemList);
}
