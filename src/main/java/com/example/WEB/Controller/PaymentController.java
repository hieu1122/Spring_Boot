package com.example.WEB.Controller;

import com.example.WEB.Entity.Orders;
import com.example.WEB.Entity.Payment;
import com.example.WEB.Entity.Product;
import com.example.WEB.Repository.PaymentRepository;
import com.example.WEB.Repository.ProductRepository;
import com.example.WEB.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentService paymentService;

    @GetMapping()
    public ResponseEntity <List<Payment>> getPayment() {
        List<Payment> payments= paymentRepository.findAll();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getOnePayment(@PathVariable int paymentId) {
        Payment payment=paymentRepository.findById(paymentId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"PaymentId not found"));
        return ResponseEntity.ok(payment);
    }

    @PostMapping()
    public ResponseEntity<Payment> addPayment(@RequestBody Payment thePayment) {
        Payment payment= paymentRepository.save(thePayment);
        return ResponseEntity.ok(payment);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<Payment> updatePayment(@PathVariable int paymentId, @RequestBody Payment thePayment) {
        return paymentService.updatePayment(paymentId, thePayment);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity <Void> deletePayment(@PathVariable int paymentId) {
        paymentRepository.deleteById(paymentId);
        return ResponseEntity.noContent().build();
    }
}