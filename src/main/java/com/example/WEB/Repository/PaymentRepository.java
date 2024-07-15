package com.example.WEB.Repository;

import com.example.WEB.Entity.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.regex.Pattern;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    List<Payment> findByOrderOrderId(Integer id);

    @Modifying
    @Query("DELETE FROM Payment p WHERE p.paymentId= :paymentId AND p.order.orderId= :orderId")
    @Transactional
    void delete(@Param("paymentId") int paymentId,@Param("orderId") int orderId);


    @Modifying
    @Query("DELETE FROM Payment p WHERE p.paymentId= :paymentId")
    void remove(@Param("paymentId") int paymentId);
}
