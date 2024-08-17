package com.example.WEB.Service;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.OrderItemRepository;
import com.example.WEB.Repository.OrdersRepository;
import com.example.WEB.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public ResponseEntity<OrderItem> updateOrderItem(int orderId, OrderItem theOrderItem) {
        OrderItem updateOrderItem=orderItemRepository.findById(orderId)
                .map(orderItem -> {
                    orderItem.setQuantity(theOrderItem.getQuantity());
                    orderItem.setPrice(theOrderItem.getPrice());
                    return orderItemRepository.save(orderItem);
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"OderItemId not found"));
        return ResponseEntity.ok(updateOrderItem);
    }
}
