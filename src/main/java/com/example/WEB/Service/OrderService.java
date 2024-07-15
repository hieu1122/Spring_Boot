package com.example.WEB.Service;

import com.example.WEB.Entity.OrderItem;
import com.example.WEB.Entity.Orders;
import com.example.WEB.Entity.Payment;
import com.example.WEB.Entity.Shipment;
import com.example.WEB.Repository.Order_ItemRepository;
import com.example.WEB.Repository.OrdersRepository;
import com.example.WEB.Repository.PaymentRepository;
import com.example.WEB.Repository.ShipmentRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
    private Order_ItemRepository orderItemRepository;

    public void updateOrderItems(Orders orders, List<OrderItem> theOrderItem) {
        Map<Integer, OrderItem> orderItemMap = orders.getOrderItems().stream()
                .collect(Collectors.toMap(OrderItem::getOrderItemId, Function.identity()));
        List<OrderItem> newOrderItem = theOrderItem.stream()
                .map(item -> orderItemMap.merge(item.getOrderItemId(), item, (existing, update) -> {
                    existing.setQuantity(update.getQuantity());
                    existing.setPrice(update.getPrice());
                    return existing;
                })).collect(Collectors.toList());
        orders.setOrderItems(newOrderItem);
    }

    public void updatePayment(Orders orders, Payment thePayment) {
        Payment payment = orders.getPayments();
        if (payment == null) {
            payment = new Payment();
            orders.setPayments(payment);
        }
        payment.setPaymentMethod(thePayment.getPaymentMethod());
        payment.setDate(thePayment.getDate());
        payment.setAmount(thePayment.getAmount());
        paymentRepository.save(payment);

    }

    public void updateShipment(Orders orders, Shipment theShipment) {
        Shipment shipment = orders.getShipments();
        if (shipment == null) {
            shipment = new Shipment();
            orders.setShipments(shipment);
        }
        shipment.setShipmentDate(theShipment.getShipmentDate());
        shipment.setTrackingNumber(theShipment.getTrackingNumber());
        shipment.setStatus(theShipment.getStatus());
        shipment.setDeliveryDate(theShipment.getDeliveryDate());
        shipmentRepository.save(shipment);
    }

    public void setOrders(Orders orders, Orders theOrders) {
        orders.setDate(theOrders.getDate());
        orders.setTotalAmount(theOrders.getTotalAmount());
        orders.setStatus(theOrders.getStatus());
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

    public void deletePayment(int id, int paymentId) {
        Optional<Orders> orderResult = ordersRepository.findById(id);
        if (orderResult.isPresent()) {
            Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
            if (paymentOptional.isPresent()) {
                Payment payment = paymentOptional.get();

                payment.setOrder(null);
                paymentRepository.save(payment);
            }
        }
    }

    public void deleteShipment(int id, int shipmentId) {
        Optional<Orders> orderResult = ordersRepository.findById(id);
        if (orderResult.isPresent()) {
            Optional<Shipment> shipmentOptional = shipmentRepository.findById(shipmentId);
            if (shipmentOptional.isPresent()) {
                Shipment shipment = shipmentOptional.get();

                shipment.setOrder(null);
                shipmentRepository.save(shipment);
            }
        }
    }

    public void deleteOrderItem(int id, int orderitemId) {
        Optional<Orders> orderResult = ordersRepository.findById(id);
        if (orderResult.isPresent()) {
            Optional<OrderItem> orderItemOptional = orderItemRepository.findById(orderitemId);
            if (orderItemOptional.isPresent()) {
                OrderItem orderItem = orderItemOptional.get();

                orderItem.setOrders(null);
                orderItemRepository.save(orderItem);
            }
        }
    }

}
