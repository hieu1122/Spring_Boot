package com.example.WEB.Service;

import com.example.WEB.Entity.Orders;
import com.example.WEB.Entity.Shipment;
import com.example.WEB.Repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    public ResponseEntity<Shipment> updateShipment(int shipmentId, Shipment theShipment) {
       Shipment result=shipmentRepository.findById(shipmentId)
               .map(shipment -> {
                   shipment.setShipmentDate(theShipment.getShipmentDate());
                   shipment.setStatus(theShipment.getStatus());
                   shipment.setTrackingNumber(theShipment.getTrackingNumber());
                   shipment.setDeliveryDate(theShipment.getDeliveryDate());
                   return shipmentRepository.save(shipment);
               }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ShipmentId not found"));
       return ResponseEntity.ok(result);
    }

}
