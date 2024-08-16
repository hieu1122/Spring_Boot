package com.example.WEB.Service;

import com.example.WEB.Entity.Product;
import com.example.WEB.Entity.Supplier;
import com.example.WEB.Repository.SupplieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    @Autowired
    private SupplieRepository supplieRepository;

    public ResponseEntity<Supplier> updateSupplier( int supplierId, Supplier theSupplier) {
        Supplier updateSupplier=supplieRepository.findById(supplierId)
                .map(supplier ->{
                    supplier.setContactInfo(theSupplier.getContactInfo());
                    supplier.setName(theSupplier.getName());
                    return supplieRepository.save(supplier);
                } )
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"SupplierId not found"));
        return ResponseEntity.ok(updateSupplier);
    }
}
