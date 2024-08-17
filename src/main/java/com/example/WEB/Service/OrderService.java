package com.example.WEB.Service;

import com.example.WEB.Entity.OrderItem;
import com.example.WEB.Entity.Orders;
import com.example.WEB.Entity.Payment;
import com.example.WEB.Entity.Shipment;
import com.example.WEB.Repository.OrderItemRepository;
import com.example.WEB.Repository.OrdersRepository;
import com.example.WEB.Repository.PaymentRepository;
import com.example.WEB.Repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ShipmentService shipmentService;

    public Orders Condition(int orderId){
        Orders orders=ordersRepository.findById(orderId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"OrderId not found"));
        return orders;
    }

    public ResponseEntity<Orders> updateOrder(int orderId, Orders theOrders){
        Orders orders=ordersRepository.findById(orderId)
                .map(orders1 -> {
                    orders1.setDate(theOrders.getDate());
                    orders1.setStatus(theOrders.getStatus());
                    orders1.setStatus(theOrders.getStatus());
                    orders1.setTotalAmount(theOrders.getTotalAmount());
                    return ordersRepository.save(orders1);
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"OrderId not found"));
        return ResponseEntity.ok(orders);
    }

    public ResponseEntity<Orders> updateOrderItems(int orderId, int itemId,OrderItem theOrderItem) {
       Condition(orderId);

       OrderItem orderItem=orderItemService.updateOrderItem(itemId,theOrderItem).getBody();
       orderItem.setOrders(Condition(orderId));
       orderItemRepository.save(orderItem);
       return ResponseEntity.ok(Condition(orderId));
    }

    public ResponseEntity<Orders> updatePayment(int orderId, int paymentId, Payment thepayment) {
        Condition(orderId);

        Payment payment=paymentService.updatePayment(paymentId,thepayment).getBody();
        payment.setOrder(Condition(orderId));
        paymentRepository.save(payment);
        return ResponseEntity.ok(Condition(orderId));
    }

    public ResponseEntity<Orders> updateShipment(int orderId, int shipmentId, Shipment theShipment) {
        Condition(orderId);

        Shipment shipment=shipmentService.updateShipment(shipmentId,theShipment).getBody();
        shipment.setOrder(Condition(orderId));
        shipmentRepository.save(shipment);
        return ResponseEntity.ok(Condition(orderId));
    }

    public ResponseEntity<Orders> Shipment(int orderId, Shipment shipment) {

        Optional<Orders> result = ordersRepository.findById(orderId);
        if (result.isPresent()) {
            Orders orders = result.get();

            orders.setShipments(shipment);
            shipment.setOrder(orders);
            Orders addOrders = ordersRepository.save(orders);
            return ResponseEntity.ok(addOrders);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Orders> Payment(int orderId, Payment thePayment) {
        Optional<Orders> result = ordersRepository.findById(orderId);
        if (result.isPresent()) {
            Orders orders = result.get();
            orders.setPayments(thePayment);
            thePayment.setOrder(orders);
            Orders addOrders = ordersRepository.save(orders);
            return ResponseEntity.ok(addOrders);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Orders> OrderItem(int orderId, List<OrderItem> orderItems) {
        Optional<Orders> result = ordersRepository.findById(orderId);
        if (result.isPresent()) {
            Orders orders = result.get();
            orders.setOrderItems(orderItems);
            for (OrderItem orderItem : orderItems) {
                orderItem.setOrders(orders);
            }
            Orders addOrders = ordersRepository.save(orders);
            return ResponseEntity.ok(addOrders);
        }
        return ResponseEntity.notFound().build();
    }
}
