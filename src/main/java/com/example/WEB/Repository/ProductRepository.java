package com.example.WEB.Repository;

import com.example.WEB.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByCategoryCategoryId(Integer id);
    List<Product> getByProductId(Integer id);
}
