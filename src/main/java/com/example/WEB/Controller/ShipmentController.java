package com.example.WEB.Controller;

import com.example.WEB.Entity.Shipment;
import com.example.WEB.Repository.ShipmentRepository;
import com.example.WEB.Service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private ShipmentService shipmentService;

    @GetMapping()
    public ResponseEntity<List<Shipment>> getShipment() {
        List<Shipment> shipments= shipmentRepository.findAll();
        return ResponseEntity.ok(shipments);
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<Shipment> getOneShipment(@PathVariable int shipmentId) {
        Shipment shipment=shipmentRepository.findById(shipmentId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ShipmentId not found"));
        return ResponseEntity.ok(shipment);
    }

    @PostMapping()
    public ResponseEntity<Shipment> addShipment(@RequestBody Shipment theShipment) {
        Shipment shipment= shipmentRepository.save(theShipment);
        return ResponseEntity.ok(shipment);
    }

    @PutMapping("/{shipmentId}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable int shipmentId, @RequestBody Shipment theShipment) {
        return shipmentService.updateShipment(shipmentId, theShipment);
    }

    @DeleteMapping("/{shipmentId}")
    public ResponseEntity <Void> deleteShipment(@PathVariable int shipmentId) {
        shipmentRepository.deleteById(shipmentId);
        return ResponseEntity.noContent().build();
    }
}
