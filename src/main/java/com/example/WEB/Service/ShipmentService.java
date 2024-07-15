package com.example.WEB.Service;

import com.example.WEB.Entity.Orders;
import com.example.WEB.Entity.Shipment;
import com.example.WEB.Repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    public ResponseEntity<Shipment> updateShipment(@PathVariable int id, @RequestBody Shipment theShipment) {
        Optional<Shipment> result=shipmentRepository.findById(id);
        if(result.isPresent()){
            Shipment shipment=result.get();

            shipment.setShipmentDate(theShipment.getShipmentDate());
            shipment.setStatus(theShipment.getStatus());
            shipment.setTrackingNumber(theShipment.getTrackingNumber());
            shipment.setDeliveryDate(theShipment.getDeliveryDate());

            Shipment updateShipment=shipmentRepository.save(shipment);
            return ResponseEntity.ok(updateShipment);
        }
        return ResponseEntity.notFound().build();
    }

}
