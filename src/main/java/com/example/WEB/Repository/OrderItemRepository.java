package com.example.WEB.Repository;

import com.example.WEB.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    void deleteByOrOrderItemId(Integer orderItemId);
}
