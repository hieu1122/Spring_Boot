package com.example.WEB.Service;

import com.example.WEB.Entity.Orders;
import com.example.WEB.Entity.Payment;
import com.example.WEB.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public ResponseEntity<Payment> updatePayment(int paymentId, Payment thePayment) {
        Payment payment = paymentRepository.findById(paymentId)
                .map(payment1 -> {
                    payment1.setDate(thePayment.getDate());
                    payment1.setAmount(thePayment.getAmount());
                    payment1.setPaymentMethod(thePayment.getPaymentMethod());
                    return paymentRepository.save(payment1);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PaymentId not found"));
        return ResponseEntity.ok(payment);
    }
}
