package com.example.WEB.Repository;

import com.example.WEB.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
//    @Modifying
//    @Query("DELETE FROM ")
//    void deleteProductId(@Param("productId") Integer productId);
}
