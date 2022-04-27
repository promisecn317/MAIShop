package com.maishopidea.maishopidea.controller;

import com.maishopidea.maishopidea.entity.Cart;
import com.maishopidea.maishopidea.entity.CartItem;
import com.maishopidea.maishopidea.entity.Product;
import com.maishopidea.maishopidea.service.CartItemService;
import com.maishopidea.maishopidea.service.CartService;
import com.maishopidea.maishopidea.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
    @Autowired//可能是在不同包调用时
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    CartItemService cartItemService;

    @PostMapping(value = "add")
    public void addItem(@RequestParam("ProductId") int productId,@RequestParam("CartId") int cartId){
        Product product= productService.getProduct(productId);

        Cart cart=cartService.findCart(cartId);
        List<CartItem> cartItemList=cart.getItems();

        if (cartItemList.size()==0){

            cartItemList.add(new CartItem(productId,product.getProductName(), 1,product.getProductPrice(),product.getProductPrice(),product.getProductImage(),cart));

        }
        else {
            for (int i = 0; i < cartItemList.size(); i++) {
                if (cartItemList.get(i).getProdId() == productId) {
                    int count = cartItemList.get(i).getCount();
                    cartItemList.get(i).setCount(count + 1);
                    cartItemList.get(i).setTotalPrice(cartItemList.get(i).getPrice() * (count + 1));
                    break;
                } else if (i == cartItemList.size() - 1) {
                    cartItemList.add(new CartItem(productId,product.getProductName(), 1, product.getProductPrice(), product.getProductPrice(), product.getProductImage(),cart));
                    //加完size就变了
                    break;
                }
            }
        }


        cart.setItems(cartItemList);
        cartService.saveCart(cart);


    }

    @PostMapping(value = "delete")
    public void deleteItem(@RequestParam("ProductId") int productId,@RequestParam("CartId") int cartId){
        Cart cart=cartService.findCart(cartId);
        List<CartItem> itemList=cart.getItems();
        int size=itemList.size();
        for (int i = 0; i <size ; i++) {
            if (itemList.get(i).getProdId()==productId){
                CartItem cartItem=itemList.get(i);
                cartItemService.deleteItem(cartItem);
                itemList.remove(cartItem);
                break;
            }
        }
        cart.setItems(itemList);
        cartService.saveCart(cart);

    }

    @PostMapping(value = "reduce")
    public void reduceItem(@RequestParam("ProductId") int productId,@RequestParam("CartId") int cartId){
        Cart cart=cartService.findCart(cartId);
        List<CartItem> cartItemList=cart.getItems();
        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).getProdId()==productId){
                int count=cartItemList.get(i).getCount();
                if (count>1) {
                    cartItemList.get(i).setCount(count-1);
                    cartItemList.get(i).setTotalPrice(cartItemList.get(i).getPrice() * (count-1));
                }
                else if (count==1)cartItemList.remove(i);
            }
        }
        cart.setItems(cartItemList);
        cartService.saveCart(cart);
    }

    @PostMapping(value = "clear")
    public void clearCart(@RequestParam("CartId") int cartId){
        Cart cart=cartService.findCart(cartId);
        List<CartItem> itemList=cart.getItems();
        itemList.clear();
        cart.setItems(itemList);
        cartService.saveCart(cart);
    }

    @PostMapping(value = "update")
    public void updateCount(@RequestParam("ProductId") int productId,@RequestParam("CartId") int cartId,@RequestParam("Count") int count){
        Cart cart=cartService.findCart(cartId);
        List<CartItem> cartItemList=cart.getItems();

        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).getProdId()==productId){
                if (count>0) {
                    cartItemList.get(i).setCount(count);
                    cartItemList.get(i).setTotalPrice(cartItemList.get(i).getPrice() * cartItemList.get(i).getCount());
                }
                else if (count==0)cartItemList.remove(i);
            }
        }
        cart.setItems(cartItemList);
        cartService.saveCart(cart);
    }

    @GetMapping(value = "totalCount")
    public int getTotalCount(@RequestParam("CartId") int cartId) {
        Cart cart=cartService.findCart(cartId);
        List<CartItem> cartItemList=cart.getItems();
        int totalCount=0;
        for (CartItem value : cartItemList) {
            totalCount+=value.getCount();
        }
        return totalCount;
    }

    @GetMapping(value = "totalPrice")
    public double getTotalPrice(@RequestParam("CartId") int cartId) {
        Cart cart=cartService.findCart(cartId);
        List<CartItem> cartItemList=cart.getItems();
        Double totalPrice=0.0;
        for (CartItem cartItem : cartItemList) {
            totalPrice+=cartItem.getTotalPrice();
        }

        return totalPrice;
    }

    @GetMapping(value = "show")
    public List<CartItem> getCart(@RequestParam("CartId") int cartId){
        Cart cart=cartService.findCart(cartId);
        return cart.getItems();
    }
}

