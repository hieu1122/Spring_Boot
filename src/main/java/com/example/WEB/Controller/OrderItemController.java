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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderitem")
public class OrderItemController {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping()
    public List<OrderItem> getOrderItem() {
        return orderItemRepository.findAll();
    }

    @GetMapping("/{orderId}")
    public OrderItem getOneOrderItem(@PathVariable int orderId) {
        return orderItemRepository.findById(orderId).orElse(null);
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
    public void deleteOrderItem(@PathVariable int orderId) {
        orderItemRepository.deleteById(orderId);
    }
}
