package com.maishopidea.maishopidea.controller;

import com.maishopidea.maishopidea.entity.Order;
import com.maishopidea.maishopidea.entity.Product;
import com.maishopidea.maishopidea.service.OrderService;
import com.maishopidea.maishopidea.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class OrderController {
    private OrderService orderService;
    @Autowired
    public void setOrderService(OrderService orderService){this.orderService = orderService;}

    private ProductService productService;
    @Autowired
    public void setProductService(ProductService productService) {this.productService = productService;}

    @GetMapping(value = "submit_order")
    public ResponseEntity userOrder(@RequestParam(name = "userId") int userId, @RequestParam List<Integer> orderIdList ){
        Order newOrder = new Order();
        newOrder.setUserId(userId);
        for (int productId : orderIdList) {
            Product orderItem = productService.getProduct(productId);
            orderItem.setSellable(false);
            productService.saveProduct(orderItem);
            newOrder.getOrderItems().add(orderItem);
        }
        orderService.saveOrder(newOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
