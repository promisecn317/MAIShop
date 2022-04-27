package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.Order;
import com.maishopidea.maishopidea.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepo orderRepo;
    @Autowired
    public void setOrderRepo (OrderRepo orderRepo){this.orderRepo = orderRepo;}

    @Override
    public List<Order> getOrders(int userId) {
        return orderRepo.findAllByUserId(userId);
    }

    @Override
    public Order saveOrder(Order order) {return orderRepo.save(order);}

}
