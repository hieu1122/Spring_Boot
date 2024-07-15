package com.example.WEB.Repository;

import com.example.WEB.Entity.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    List<Orders> findByPaymentsPaymentId(int id);
    void deleteByPaymentsPaymentId(int id);

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Orders o WHERE o.orderId= :id")
//    void deleteOrders(@Param("id") Integer id);
}
