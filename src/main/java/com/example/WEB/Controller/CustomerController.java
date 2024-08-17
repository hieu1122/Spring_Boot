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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<Customer>> getCustomer() {
        List<Customer> customers= customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getOneCustomer(@PathVariable int customerId) {
        Customer customer=customerRepository.findById(customerId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"CustomerId not found"));
        return ResponseEntity.ok(customer);
    }

    @PostMapping()
    public ResponseEntity< Customer> addCustomer(@RequestBody Customer theCustomer){
        Customer customer=customerRepository.save(theCustomer);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int customerId, @RequestBody Customer theCustomer) {
        return customerService.updateCustomer(customerId,theCustomer);
    }

    @PutMapping("/{customerId}/updateOrder/{orderId}")
    public ResponseEntity<Customer> updateOrder(@PathVariable int customerId,@PathVariable int orderId, @RequestBody Orders theOrder) {
       return customerService.updateOrders(customerId,orderId,theOrder);
    }

    @PutMapping("/{customerId}/updateOrder/{orderId}/updatePayment/{paymentId}")
    public ResponseEntity<Customer> updatePayment(@PathVariable int customerId,@PathVariable int orderId, @PathVariable int paymentId, @RequestBody Payment thePayment) {
        return customerService.updatePayment(customerId,orderId,paymentId,thePayment);
    }

    @PutMapping("/{customerId}/updateOrder/{orderId}/updateShipment/{shipmentId}")
    public ResponseEntity<Customer> updateShipment(@PathVariable int customerId,@PathVariable int orderId, @PathVariable int shipmentId, @RequestBody Shipment theShipment) {
        return customerService.updateShipment(customerId,orderId,shipmentId,theShipment);
    }

    @PutMapping("/{customerId}/updateOrder/{orderId}/updateOrderItem/{itemId}")
    public ResponseEntity<Customer> updateOrderItem(@PathVariable int customerId,@PathVariable int orderId, @PathVariable int itemId, @RequestBody OrderItem theOrderItem) {
        return customerService.updateOrderItem(customerId,orderId,itemId,theOrderItem);
    }

    @PutMapping("/{customerId}/updateReview/{reviewId}")
    public ResponseEntity<Customer> updateReview(@PathVariable int customerId, @PathVariable int reviewId, @RequestBody Review theReview) {
        return customerService.updateReview(customerId,reviewId,theReview);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity <Void> deleteCustomer(@PathVariable int customerId) {
        customerRepository.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{customerId}/deleteOrder/{orderId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId,@PathVariable int orderId) {
        customerService.deleteOrder(customerId, orderId);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{customerId}/deleteReview/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable int customerId,@PathVariable int reviewId) {
        customerService.deleteReview(customerId, reviewId);
        return ResponseEntity.noContent().build();
    }
}
