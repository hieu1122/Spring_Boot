package com.example.WEB.Controller;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.*;
import com.example.WEB.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OdersController {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<Orders>> getOrder() {
        List<Orders> orders=ordersRepository.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity <Orders> getOneOrder(@PathVariable int orderId) {
        Orders orders= ordersRepository.findById(orderId).orElse(null);
        return ResponseEntity.ok(orders);
    }

    @PostMapping()
    public ResponseEntity<Orders> addOrder(@RequestBody Orders theOrders) {
        Orders orders=ordersRepository.save(theOrders);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity <Orders> updateOrder(@PathVariable int orderId, @RequestBody Orders theOrder) {
        return orderService.updateOrder(orderId, theOrder);
    }

    @PutMapping("/{orderId}/updatePayment/{paymentId}")
    public ResponseEntity<Orders> updatePayment(@PathVariable int orderId,@PathVariable int paymentId, @RequestBody Payment thePayment) {
        return orderService.updatePayment(orderId, paymentId,thePayment);
    }

    @PutMapping("/{orderId}/updateShipment/{shipmentId}")
    public ResponseEntity<Orders> updateShipment(@PathVariable int orderId,@PathVariable int shipmentId, @RequestBody Shipment theShipment) {
        return orderService.updateShipment(orderId, shipmentId,theShipment);
    }

    @PutMapping("/{orderId}/updateOrderItem/{itemId}")
    public ResponseEntity<Orders> updateOrderItem(@PathVariable int orderId, @PathVariable int itemId, @RequestBody OrderItem orderItem) {
        return orderService.updateOrderItems(orderId, itemId,orderItem);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) {
        ordersRepository.deleteById(orderId);
        return ResponseEntity.noContent().build();
    }
}
