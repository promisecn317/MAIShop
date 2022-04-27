package com.maishopidea.maishopidea.repo;

import com.maishopidea.maishopidea.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    List<Order> findAllByUserId(int userId);
}
