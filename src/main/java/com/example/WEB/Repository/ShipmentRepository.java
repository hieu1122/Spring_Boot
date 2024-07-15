package com.example.WEB.Repository;

import com.example.WEB.Entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment,Integer> {
}
