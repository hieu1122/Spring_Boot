package com.example.WEB.Repository;

import com.example.WEB.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplieRepository extends JpaRepository<Supplier,Integer> {
}
