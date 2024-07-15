package com.example.WEB.Service;

import com.example.WEB.Entity.Orders;
import com.example.WEB.Entity.Payment;
import com.example.WEB.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    public ResponseEntity<Payment> updatePayment(@PathVariable int id, @RequestBody Payment thePayment) {
        Optional<Payment> result=paymentRepository.findById(id);
        if(result.isPresent()){
            Payment payment=result.get();

            payment.setDate(thePayment.getDate());
            payment.setPaymentMethod(thePayment.getPaymentMethod());
            payment.setAmount(thePayment.getAmount());

            Payment updatePayment=paymentRepository.save(payment);
            return ResponseEntity.ok(updatePayment);
        }
        return ResponseEntity.notFound().build();
    }
}
