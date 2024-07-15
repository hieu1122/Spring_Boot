package com.example.WEB.Controller;

import com.example.WEB.Entity.Shipment;
import com.example.WEB.Entity.Supplier;
import com.example.WEB.Repository.SupplieRepository;
import com.example.WEB.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SupplierController {
    @Autowired
    private SupplieRepository supplieRepository;
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/supplier")
    public List<Supplier> getSupplier() {
        return supplieRepository.findAll();
    }

    @GetMapping("/supplier/{id}")
    public Supplier getOneSupplier(@PathVariable int id){
        return supplieRepository.findById(id).orElse(null);
    }

    @PostMapping("/supplier")
    public Supplier addSupplier(@RequestBody Supplier supplier) {
        return supplieRepository.save(supplier);
    }

    @PutMapping("/supplier/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable int id, @RequestBody Supplier theSupplier) {
        return supplierService.updateSupplier(id, theSupplier);
    }

    @DeleteMapping("/supplier/{id}")
    public void deleteSupplier(@PathVariable int id) {
        supplieRepository.deleteById(id);
    }
}
