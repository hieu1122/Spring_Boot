package com.example.WEB.Controller;

import com.example.WEB.Entity.Orders;
import com.example.WEB.Entity.Payment;
import com.example.WEB.Entity.Product;
import com.example.WEB.Repository.PaymentRepository;
import com.example.WEB.Repository.ProductRepository;
import com.example.WEB.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment")
    public List<Payment> getPayment() {
        return paymentRepository.findAll();
    }

    @GetMapping("/payment/{id}")
    public Payment getOnePayment(@PathVariable int id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @GetMapping("/payment/getOrderId/{id}")
    public List<Payment> getOrder(@PathVariable int id) {
        return paymentRepository.findByOrderOrderId(id);
    }

    @PostMapping("/payment")
    public Payment addPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable int id, @RequestBody Payment thePayment) {
        return paymentService.updatePayment(id, thePayment);
    }

    @DeleteMapping("/payment/{paymentId}/{orderId}")
    public void deletePayment(@PathVariable int paymentId, @PathVariable int orderId) {
        paymentRepository.delete(paymentId, orderId);
    }

    @DeleteMapping("/payment/{paymentId}")
    public void deletePayment1(@PathVariable int paymentId) {
        Optional<Payment> result=paymentRepository.findById(paymentId);
        if(result.isPresent()){
            Payment payment=result.get();
            paymentRepository.delete(payment);
        }
    }

}