package com.example.WEB.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(mappedBy = "suppliers")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();
}
