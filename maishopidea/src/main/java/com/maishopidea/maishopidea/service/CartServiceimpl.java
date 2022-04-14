package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.Cart;
import com.maishopidea.maishopidea.entity.CartItem;
import com.maishopidea.maishopidea.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceimpl implements CartService{
    @Autowired
    CartRepo cartRepo;

    @Override
    public void addItem(CartItem cartItem) {
//        CartItem item=items.get(cartItem.getCartItemId());
//
//        if (item==null){
//            items.put(cartItem.getCartItemId(),cartItem);
//        }else {
//            item.setCount(item.getCount()+1);
//            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
//        }

    }

    @Override
    public int saveCart(Cart cart){
        return cartRepo.save(cart).getCartId();
    }



    @Override
    public Integer getTotalCount() {
        return null;
    }

    @Override
    public Cart findCart(int cartId) {
        Optional<Cart> items = cartRepo.findById(cartId);
        return items.isPresent()?items.get():null;
    }


}
