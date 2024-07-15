package com.example.WEB.Repository;

import com.example.WEB.Entity.ProductSupplier;
import com.example.WEB.Entity.ProductSupplierId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, ProductSupplierId> {
    ProductSupplier findByProductIdAndSupplierId(int productId,int supplierId);
}
