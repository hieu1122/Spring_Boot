package com.example.WEB.Controller;

import com.example.WEB.Entity.Shipment;
import com.example.WEB.Entity.Supplier;
import com.example.WEB.Repository.SupplieRepository;
import com.example.WEB.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private SupplieRepository supplieRepository;
    @Autowired
    private SupplierService supplierService;

    @GetMapping()
    public ResponseEntity<List<Supplier>> getSupplier() {
        List<Supplier> suppliers=supplieRepository.findAll();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<Supplier> getOneSupplier(@PathVariable int supplierId){
        Supplier supplier=supplieRepository.findById(supplierId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"SupplierId not found"));
        return ResponseEntity.ok(supplier);
    }

    @PostMapping()
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) {
        Supplier savedSupplier = supplieRepository.save(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable int supplierId, @RequestBody Supplier theSupplier) {
        return supplierService.updateSupplier(supplierId, theSupplier);
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable int supplierId) {
        if (!supplieRepository.existsById(supplierId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found");
        }
        supplieRepository.deleteById(supplierId);
        return ResponseEntity.noContent().build();
    }
}
