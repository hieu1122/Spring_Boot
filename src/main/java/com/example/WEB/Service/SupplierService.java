package com.example.WEB.Service;

import com.example.WEB.Entity.Product;
import com.example.WEB.Entity.Supplier;
import com.example.WEB.Repository.SupplieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    @Autowired
    private SupplieRepository supplieRepository;
    public ResponseEntity<Supplier> updateSupplier(@PathVariable int id, @RequestBody Supplier theSupplier) {
        Optional<Supplier> result=supplieRepository.findById(id);
        if(result.isPresent()){
            Supplier supplier=result.get();

            supplier.setContactInfo(theSupplier.getContactInfo());
            supplier.setName(theSupplier.getName());
            Supplier updateSupplier=supplieRepository.save(supplier);
            return ResponseEntity.ok(supplier);
        }
        return ResponseEntity.notFound().build();
    }
}
