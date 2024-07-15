package com.example.WEB.Controller;

import com.example.WEB.Entity.OrderItem;
import com.example.WEB.Entity.Orders;
import com.example.WEB.Entity.Product;
import com.example.WEB.Entity.ProductSupplierId;
import com.example.WEB.Repository.Order_ItemRepository;
import com.example.WEB.Repository.OrdersRepository;
import com.example.WEB.Repository.ProductRepository;
import com.example.WEB.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderItemController {
    @Autowired
    private Order_ItemRepository orderItemRepository;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/orderitem")
    public List<OrderItem> getOrderItem() {
        return orderItemRepository.findAll();
    }

    @GetMapping("/orderitem/{id}")
    public OrderItem getOneOrderItem(@PathVariable int id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    @PutMapping("/orderitem/{id}/{order_id}/{product_id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable int id, @PathVariable int order_id, @PathVariable int product_id, @RequestBody OrderItem theOrderItem) {
        return orderItemService.updateOrderItem(id, order_id, product_id, theOrderItem);
    }

    @PostMapping("/orderitem/{order_id}/{product_id}")
    public ResponseEntity<OrderItem> addOrderItem(@PathVariable int order_id, @PathVariable int product_id, @RequestBody OrderItem theOrderItem) {
        return orderItemService.addOrderItem(order_id, product_id, theOrderItem);
    }

    @DeleteMapping("/orderitem/{id}")
    public void deleteOrderItem(@PathVariable int id) {
        orderItemRepository.deleteById(id);
    }
}
