package com.example.WEB.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_supplier")
@Data
@IdClass(ProductSupplierId.class)
public class ProductSupplier {
    @Id
    @Column(name = "product_id")
    private int productId;
    @Id
    @Column(name = "supplier_id")
    private int supplierId;

}
