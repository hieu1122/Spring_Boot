package com.example.WEB.Controller;

import com.example.WEB.Entity.Shipment;
import com.example.WEB.Repository.ShipmentRepository;
import com.example.WEB.Service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ShipmentController {
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/shipment")
    public List<Shipment> getShipment() {
        return shipmentRepository.findAll();
    }

    @GetMapping("/shipment/{id}")
    public Shipment getOneShipment(@PathVariable int id) {
        return shipmentRepository.findById(id).orElse(null);
    }

    @PostMapping("/shipment")
    public Shipment addShipment(@RequestBody Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    @PutMapping("/shipment/{id}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable int id, @RequestBody Shipment theShipment) {
        return shipmentService.updateShipment(id, theShipment);
    }

    @DeleteMapping("/shipment/{id}")
    public void deleteShipment(@PathVariable int id) {
        shipmentRepository.deleteById(id);
    }
}
