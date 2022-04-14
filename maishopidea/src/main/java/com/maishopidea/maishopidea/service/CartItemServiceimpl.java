package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.CartItem;
import com.maishopidea.maishopidea.repo.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartItemServiceimpl implements CartItemService{

    @Autowired
    CartItemRepo cartItemRepo;
    @Override
    @Transactional
    public void deleteItem(CartItem cartItem) {
        cartItemRepo.delete(cartItem);
    }

    @Override
    public void saveCartItem(List<CartItem> cartItemList) {
        cartItemRepo.saveAll(cartItemList);
    }


}
