package com.example.WEB.Controller;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.CustomerRepository;
import com.example.WEB.Repository.OrdersRepository;
import com.example.WEB.Repository.PaymentRepository;
import com.example.WEB.Repository.ShipmentRepository;
import com.example.WEB.Service.CustomerService;
import com.example.WEB.Service.OrderService;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private OrderService orderService;

    @GetMapping("/customer")
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer getOneCustomer(@PathVariable int id) {
        return customerRepository.findById(id).orElse(null);
    }

//    @PostMapping("/customer")
//    public Customer addCustomer(@RequestBody Customer theCustomer) {
//        Customer customer = new Customer();
//        customerService.addCustomer(customer, theCustomer);
//        return customerRepository.save(customer);
//    }

    @PostMapping("/customer/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @PostMapping("/customer/addOrder/{customerId}")
    public Customer addOrder(@PathVariable int customerId, @RequestBody List<Orders> orders) {
       return customerService.Orders(customerId,orders).getBody();
    }

    @PostMapping("/customer/addPayment/{customerId}/{orderId}")
    public ResponseEntity<Customer> addPayment(@PathVariable int customerId, @PathVariable int orderId, @RequestBody Payment payment) {
        return customerService.Payment(customerId,orderId,payment);
    }

    @PostMapping("/customer/addShipment/{customerId}/{orderId}")
    public ResponseEntity<Customer> addShipment(@PathVariable int customerId, @PathVariable int orderId, @RequestBody Shipment shipment) {
        return customerService.Shipment(customerId,orderId,shipment);
    }

    @PostMapping("/customer/addOrderItem/{customerId}/{orderId}")
    public ResponseEntity<Customer> addOrderItem(@PathVariable int customerId, @PathVariable int orderId, @RequestBody List<OrderItem> orderItems) {
        return customerService.OrderItem(customerId,orderId,orderItems);
    }

    @PostMapping("/customer/addReview/{customerId}")
    public ResponseEntity<Customer> addReview(@PathVariable int customerId, @RequestBody List<Review> reviews) {
        return customerService.Review(customerId,reviews);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerRepository.deleteById(id);
    }

    @DeleteMapping("/customer/{id}/deleteOrder/{orderId}")
    public void deleteCustomer(@PathVariable int id,@PathVariable int orderId) {
        customerService.deleteOrder(id, orderId);
    }
    @DeleteMapping("/customer/{id}/deleteReview/{reviewId}")
    public void deleteReview(@PathVariable int id,@PathVariable int reviewId) {
        customerService.deleteReview(id, reviewId);
    }

//    @PostMapping("/customer")
//    public ResponseEntity<Customer> addCustomer(@RequestBody Customer theCustomer) {
//        Customer customer = new Customer();
//        customer.setName(theCustomer.getName());
//        customer.setEmail(theCustomer.getEmail());
//        customer.setPassword(theCustomer.getPassword());
//        customer.setPhone(theCustomer.getPhone());
//        customer.setAddress(theCustomer.getAddress());
//
//        if (theCustomer.getOrders() != null) {
//            List<Orders> orders = theCustomer.getOrders();
//            customer.setOrders(orders);
//            for (Orders orders1 : orders) {
//                orders1.setCustomer(customer);
//
//                //payment
//                if (orders1.getPayments() != null) {
//                    orderService.addPayment(orders1,orders1.getPayments());
//                }
//
//                //Shipment
//                if (orders1.getShipments() != null) {
//                    orderService.addShipment(orders1,orders1.getShipments());
//                }
//
//                //OrderItem
//                if (orders1.getShipments() != null) {
//                    orderService.addOrderItem(orders1,orders1.getOrderItems());
//                }
//            }
//            List<Review> reviews=theCustomer.getReviews();
//            customer.setReviews(reviews);
//            for(Review review : reviews){
//                review.setCustomer(customer);
//            }
//            Customer addCustomer = customerRepository.save(customer);
//            return ResponseEntity.ok(addCustomer);
//        }
//        return ResponseEntity.notFound().build();
//    }

//    @PutMapping("/customer/{id}")
//    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer theCustomer) {
//        Optional<Customer> result = customerRepository.findById(id);
//        if (result.isPresent()) {
//            Customer customer = result.get();
//
//            customer.setName(theCustomer.getName());
//            customer.setEmail(theCustomer.getEmail());
//            customer.setPassword(theCustomer.getPassword());
//            customer.setPhone(theCustomer.getPhone());
//            customer.setAddress(theCustomer.getAddress());
//
//            //Orders
//            customerService.updateOrders(customer, theCustomer.getOrders());
//
//            //Payment
//            for (Orders orders : customer.getOrders()) {
//                for (Orders orders1 : theCustomer.getOrders()) {
//                    if (orders.getOrderId() == orders1.getOrderId()) {
//                        orderService.updatePayment(orders, orders1.getPayments());
//                        break;
//                    }
//                }
//            }
//
//            //Shipment
//            for (Orders orders : customer.getOrders()) {
//                for (Orders orders1 : theCustomer.getOrders()) {
//                    if (orders.getOrderId() == orders1.getOrderId()) {
//                        orderService.updateShipment(orders, orders1.getShipments());
//                    }
//                }
//            }
//
//            //OrderItem
//            for (Orders orders : customer.getOrders()) {
//                for (Orders orders1 : theCustomer.getOrders()) {
//                    if (orders.getOrderId() == orders1.getOrderId()) {
//                        orderService.updateOrderItems(orders, orders1.getOrderItems());
//                    }
//                }
//            }
//
//            //Review
//            customerService.updateReview(customer, theCustomer.getReviews());
//
//            return customerRepository.save(customer);
//        }
//        throw new RuntimeException("Customer with id " + id + " not found");
//    }


}
