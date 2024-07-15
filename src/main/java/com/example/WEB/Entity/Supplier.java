package com.example.WEB.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Supplier")
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private int supplierId;
    @Column(name = "name")
    private String name;
    @Column(name = "contact_info")
    private String contactInfo;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "suppliers")
//    @JsonBackReference
    @JsonIgnore
    private List<Product> products;
}
