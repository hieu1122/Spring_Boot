package com.example.WEB.Controller;

import com.example.WEB.Entity.OrderItem;
import com.example.WEB.Repository.OrderItemRepository;
import com.example.WEB.Repository.OrdersRepository;
import com.example.WEB.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderitem")
public class OrderItemController {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping()
    public ResponseEntity <List<OrderItem>> getOrderItem() {
        List<OrderItem> orderItems=orderItemRepository.findAll();
         return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderItem> getOneOrderItem(@PathVariable int orderId) {
        OrderItem orderItem=orderItemRepository.findById(orderId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"OrderItemId not found"));
        return ResponseEntity.ok(orderItem);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable int orderId, @RequestBody OrderItem theOrderItem) {
        return orderItemService.updateOrderItem(orderId, theOrderItem);
    }

    @PostMapping()
    public ResponseEntity<OrderItem> addOrderItem(@RequestBody OrderItem theOrderItem) {
        OrderItem orderItem=orderItemRepository.save(theOrderItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItem);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable int orderId) {
        orderItemRepository.deleteById(orderId);
        return ResponseEntity.noContent().build();
    }
}
