package com.example.WEB.Service;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.CustomerRepository;
import com.example.WEB.Repository.OrdersRepository;
import com.example.WEB.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
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
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private OrderItemService orderItemService;

    public Customer Condition(int customerId){
        Customer customer=customerRepository.findById(customerId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"CustomerId not found"));
        return customer;
    }

    public ResponseEntity<Customer> updateCustomer(int customerId, Customer theCustomer){
        Customer customer=customerRepository.findById(customerId)
                .map(customer1 -> {
                    customer1.setName(theCustomer.getName());
                    customer1.setEmail(theCustomer.getEmail());
                    customer1.setPassword(theCustomer.getPassword());
                    customer1.setPhone(theCustomer.getPhone());
                    customer1.setAddress(theCustomer.getAddress());
                    return customerRepository.save(customer1);
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"CustomerId not found"));
        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<Customer> updateOrders(int customerId,int orderId,Orders theOrder){
        Condition(customerId);

        Orders orders=orderService.updateOrder(orderId,theOrder).getBody();
        orders.setCustomer(Condition(customerId));
        ordersRepository.save(orders);
        return ResponseEntity.ok(Condition(customerId));
    }

    public ResponseEntity<Customer> updatePayment(int customerId,int orderId, int paymentId,Payment thePayment){
        Condition(customerId);

        Orders orders=orderService.updatePayment(orderId,paymentId,thePayment).getBody();
        orders.setCustomer(Condition(customerId));
        ordersRepository.save(orders);
        return ResponseEntity.ok(Condition(customerId));
    }

    public ResponseEntity<Customer> updateShipment(int customerId,int orderId, int shipmentId,Shipment theShipment){
        Condition(customerId);

        Orders orders=orderService.updateShipment(orderId,shipmentId,theShipment).getBody();
        orders.setCustomer(Condition(customerId));
        ordersRepository.save(orders);
        return ResponseEntity.ok(Condition(customerId));
    }

    public ResponseEntity<Customer> updateOrderItem(int customerId,int orderId, int itemId,OrderItem theOrderItem){
        Condition(customerId);

        Orders orders=orderService.updateOrderItems(orderId,itemId,theOrderItem).getBody();
        orders.setCustomer(Condition(customerId));
        ordersRepository.save(orders);
        return ResponseEntity.ok(Condition(customerId));
    }

    public ResponseEntity<Customer> updateReview(int customerId,int reviewId,Review theReview) {
        Condition(customerId);

        Review review=reviewService.updateReview(reviewId,theReview).getBody();
        review.setCustomer(Condition(customerId));
        reviewRepository.save(review);
        return ResponseEntity.ok(Condition(customerId));
    }

    public ResponseEntity<Void> deleteOrder(int customerId, int orderId) {
        Condition(customerId);

        ordersRepository.findById(orderId)
                .map(orders1 -> {
                    orders1.setCustomer(null);
                    return ordersRepository.save(orders1);
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"OrderId not found"));
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> deleteReview(int customerId, int reviewId) {
        Condition(customerId);

        reviewRepository.findById(reviewId)
                .map(review -> {
                    review.setCustomer(null);
                    return reviewRepository.save(review);
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"OrderId not found"));
        return ResponseEntity.noContent().build();
    }
}
