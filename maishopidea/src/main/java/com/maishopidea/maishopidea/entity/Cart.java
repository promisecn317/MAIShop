package com.maishopidea.maishopidea.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;能用local就用local
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY,mappedBy = "cart")
    private List<CartItem> items=new ArrayList<CartItem>();

    public Cart() {
    }

    public Cart(List<CartItem> items) {
        this.items = items;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", items=" + items +
                '}';
    }

    /*@ManyToOne
        @JoinColumn(name = "cart_item_cart_item_id")
        private CartItem cartItem;
    */
//    public CartItem getCartItem() {
//        return cartItem;
//    }




//
//    public void deleteItem(Integer id){
//        items.remove(id);
//
//    }
//
//    public  void clear(){
//
//        items.clear();
//    }
//
//    public void updateCount(Integer id, Integer count){
//        CartItem cartItem=items.get(id);
//        if (cartItem!=null){
//            cartItem.setCount(count);
//            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
//
//        }
//    }
//
//    public Integer getTotalCount() {
//        Integer totalCount=0;
//        for (CartItem value : items.values()) {
//            totalCount+=value.getCount();
//        }
////        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
////            totalCount+=entry.getValue().getCount();
////        }
//        return totalCount;
//    }
//
//
//
//    public BigDecimal getTotalPrice() {
//        BigDecimal totalPrice=new BigDecimal(0);
//        for (CartItem cartItem : items.values()) {
//            totalPrice=totalPrice.add(cartItem.getTotalPrice());
//        }
//
//        return totalPrice;
//    }
//



}
