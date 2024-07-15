package com.example.WEB.Controller;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.*;
import com.example.WEB.Service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OdersController {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private Order_ItemRepository orderItemRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/order")
    public List<Orders> getOrder() {
        return ordersRepository.findAll();
    }

    @GetMapping("/order/{id}")
    public Orders getOneOrder(@PathVariable int id) {
        return ordersRepository.findById(id).orElse(null);
    }

    @GetMapping("/order/getPaymentId/{id}")
    public List<Orders> getPaymentId(@PathVariable int id) {
        return ordersRepository.findByPaymentsPaymentId(id);
    }

    @PostMapping("/order")
    public Orders addOrder(@RequestBody Orders theOrders) {
        return ordersRepository.save(theOrders);
    }

    @PostMapping("/order/addPayment/{orderId}")
    public Orders addPayment(@PathVariable int orderId, @RequestBody Payment thePayment) {
        return orderService.Payment(orderId, thePayment).getBody();
    }

    @PostMapping("/order/addShipment/{orderId}")
    public Orders addShipment(@PathVariable int orderId, @RequestBody Shipment shipment) {
        return orderService.Shipment(orderId, shipment).getBody();
    }

    @PostMapping("/order/addOrderItem/{orderId}")
    public Orders addOrderItem(@PathVariable int orderId, @RequestBody List<OrderItem> orderItems) {
        return orderService.OrderItem(orderId, orderItems).getBody();
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable int id) {
        ordersRepository.deleteById(id);
    }

    @DeleteMapping("/order/{id}/deletePayment/{paymentId}")
    public void deletePayment(@PathVariable int id, @PathVariable int paymentId) {
        orderService.deletePayment(id, paymentId);
    }

    @DeleteMapping("/order/{id}/deleteShipment/{shipmentId}")
    public void deleteShipment(@PathVariable int id, @PathVariable int shipmentId) {
        orderService.deleteShipment(id, shipmentId);
    }

    @DeleteMapping("/order/{id}/deleteOrderItem/{orderitemId}")
    public void deleteOrderItem(@PathVariable int id, @PathVariable int orderitemId) {
        orderService.deleteOrderItem(id, orderitemId);
    }

//    @PostMapping("/order/{id}")
//    public ResponseEntity<Orders> addOrder(@PathVariable int id, @RequestBody Orders theOrders) {
//        Optional<Customer> result = customerRepository.findById(id);
//        {
//            Customer customer = result.get();
//
//            Orders orders = new Orders();
//            orders.setCustomer(customer);
//            orders.setTotalAmount(theOrders.getTotalAmount());
//            orders.setStatus(theOrders.getStatus());
//            orders.setDate(theOrders.getDate());
//
//            //Payment
//            if (theOrders.getPayments() != null) {
//                Payment payment = theOrders.getPayments();
//                payment.setOrder(orders);
//                orders.setPayments(payment);
//            }
//
//            //Shipment
//            if (theOrders.getShipments() != null) {
//                Shipment shipment = theOrders.getShipments();
//                shipment.setOrder(orders);
//                orders.setShipments(shipment);
//            }
//
//            //OrderItem
//            if (theOrders.getOrderItems() != null) {
//                List<OrderItem> orderItems = theOrders.getOrderItems();
//                orders.setOrderItems(orderItems);
//                for (OrderItem orderItem : orderItems) {
//                    orderItem.setOrders(orders);
//                }
//            }
//
//            Orders addOrder = ordersRepository.save(orders);
//            return ResponseEntity.ok(addOrder);
//        }
//    }

//    @PutMapping("/order/{orderId}")
//    public ResponseEntity<Orders> updateOrder(@PathVariable int orderId, @RequestBody Orders theOrders) {
//        Optional<Orders> result = ordersRepository.findById(orderId);
//        if (result.isPresent()) {
//            Orders orders = result.get();
//
//            //Orders
//            orders.setDate(theOrders.getDate());
//            orders.setStatus(theOrders.getStatus());
//            orders.setTotalAmount(theOrders.getTotalAmount());
//
//            //Payment
//            Payment payment = orders.getPayments();
//            if (payment == null) {
//                payment = new Payment();
//                orders.setPayments(payment);
//            }
//            payment.setPaymentId(theOrders.getPayments().getPaymentId());
//            payment.setDate(theOrders.getPayments().getDate());
//            payment.setAmount(theOrders.getPayments().getAmount());
//            payment.setPaymentMethod(theOrders.getPayments().getPaymentMethod());
//            paymentRepository.save(payment);
//
//            //Shipment
//            Shipment shipment = orders.getShipments();
//            if (shipment == null) {
//                shipment = new Shipment();
//                orders.setShipments(shipment);
//            }
//            shipment.setShipmentId(theOrders.getShipments().getShipmentId());
//            shipment.setShipmentDate(theOrders.getShipments().getShipmentDate());
//            shipment.setTrackingNumber(theOrders.getShipments().getTrackingNumber());
//            shipment.setDeliveryDate(theOrders.getShipments().getDeliveryDate());
//            shipment.setStatus(theOrders.getShipments().getStatus());
//            shipmentRepository.save(shipment);
//
//            //OrderItem
//            orderService.updateOrderItems(orders, theOrders.getOrderItems());
//
//            Orders updateOrder = ordersRepository.save(orders);
//            return ResponseEntity.ok(updateOrder);
//        }
//        return ResponseEntity.notFound().build();
//    }

}
