package com.example.WEB.Service;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.CustomerRepository;
import com.example.WEB.Repository.OrdersRepository;
import com.example.WEB.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public void updateOrders(Customer customer, List<Orders> theOrders) {
        Map<Integer, Orders> result = customer.getOrders().stream().collect(Collectors.toMap(Orders::getOrderId, Function.identity()));
        List<Orders> newOrders = theOrders.stream().map(orders -> result.merge(orders.getOrderId(), orders, (existing, update) -> {
            existing.setDate(update.getDate());
            existing.setStatus(update.getStatus());
            existing.setTotalAmount(update.getTotalAmount());
            return existing;
        })).collect(Collectors.toList());

        customer.setOrders(newOrders);
    }

    public void updateReview(Customer customer, List<Review> theReview) {
        Map<Integer, Review> reviewMap = customer.getReviews().stream().collect(Collectors.toMap(Review::getReviewId, Function.identity()));
        List<Review> newReview = theReview.stream().map(review -> (reviewMap.merge(review.getReviewId(), review, (existing, update) -> {
            existing.setRating(update.getRating());
            existing.setComment(update.getComment());
            existing.setReviewDate(update.getReviewDate());
            return existing;
        }))).collect(Collectors.toList());
        customer.setReviews(newReview);
    }

    public void setCustomer(Customer customer, Customer theCustomer) {
        customer.setAddress(theCustomer.getAddress());
        customer.setName(theCustomer.getName());
        customer.setPassword(theCustomer.getPassword());
        customer.setPhone(theCustomer.getPhone());
        customer.setEmail(theCustomer.getEmail());
    }

    public ResponseEntity<Customer> Orders(int customerId, List<Orders> orders) {
        Optional<Customer> result = customerRepository.findById(customerId);
        if (result.isPresent()) {
            Customer customer = result.get();
            customer.setOrders(orders);
            for (Orders orders1 : orders) {
                orders1.setCustomer(customer);
            }
            Customer addOrder = customerRepository.save(customer);
            return ResponseEntity.ok(addOrder);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Customer> Review(int customerId, List<Review> reviews) {
        Optional<Customer> result = customerRepository.findById(customerId);
        if (result.isPresent()) {
            Customer customer = result.get();

            customer.setReviews(reviews);
            for (Review review : reviews) {
                review.setCustomer(customer);
            }
            Customer addOrder = customerRepository.save(customer);
            return ResponseEntity.ok(addOrder);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Customer> Payment(int customerId,int orderId,Payment payment){
        Optional<Customer> result = customerRepository.findById(customerId);
        if (result.isPresent()) {
            Customer customer = result.get();
            Optional<Orders> result1 = ordersRepository.findById(orderId);
            if (result1.isPresent()) {
                orderService.Payment(orderId, payment);
            }
            Customer addOrder = customerRepository.save(customer);
            return ResponseEntity.ok(addOrder);
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<Customer> Shipment(int customerId,int orderId,Shipment shipment){
        Optional<Customer> result = customerRepository.findById(customerId);
        if (result.isPresent()) {
            Customer customer = result.get();
            Optional<Orders> result1 = ordersRepository.findById(orderId);
            if (result1.isPresent()) {
                orderService.Shipment(orderId, shipment);
            }
            Customer addOrder = customerRepository.save(customer);
            return ResponseEntity.ok(addOrder);
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<Customer> OrderItem(int customerId,int orderId,List<OrderItem> orderItems){
        Optional<Customer> result = customerRepository.findById(customerId);
        if (result.isPresent()) {
            Customer customer = result.get();
            Optional<Orders> result1 = ordersRepository.findById(orderId);
            if (result1.isPresent()) {
                orderService.OrderItem(orderId, orderItems);
            }
            Customer addOrder = customerRepository.save(customer);
            return ResponseEntity.ok(addOrder);
        }
        return ResponseEntity.notFound().build();
    }
    public void deleteOrder(int id, int orderId) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Optional<Orders> ordersOptional = ordersRepository.findById(orderId);
            if (ordersOptional.isPresent()) {
                Orders orders = ordersOptional.get();

                orders.setCustomer(null);
                ordersRepository.save(orders);
            }
        }
    }
    public void deleteReview(int id, int reviewId) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Optional<Review> reviewOptional= reviewRepository.findById(reviewId);
            if (reviewOptional.isPresent()) {
                Review review = reviewOptional.get();

                review.setCustomer(null);
                reviewRepository.save(review);
            }
        }
    }
}
