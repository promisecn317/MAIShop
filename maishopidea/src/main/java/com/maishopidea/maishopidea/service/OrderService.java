package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.Order;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface OrderService {
    List<Order> getOrders(int userId);

    Order saveOrder(Order order);
}
