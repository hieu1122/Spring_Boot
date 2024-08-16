package com.example.WEB.Controller;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.*;
import com.example.WEB.Service.CategoryService;
import com.example.WEB.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getCategory() {
        List<Category> category=categoryRepository.findAll();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getOneCategory(@PathVariable int categoryId) {
        Category category=categoryService.Condition(categoryId);
        return ResponseEntity.ok(category);
    }

    @PostMapping()
    public ResponseEntity<Category> addCategory(@RequestBody Category theCategory) {
        Category category= categoryRepository.save(theCategory);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable int categoryId, @RequestBody Category theCategory) {
        Category category= categoryService.updateCategory(categoryId, theCategory);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{categoryId}/updateProduct/{productId}")
    public ResponseEntity<Category> updateProduct(@PathVariable int categoryId,@PathVariable int productId, @RequestBody Product product) {
        return categoryService.updateProduct(categoryId,productId, product);
    }

    @PutMapping("/{categoryId}/updateProduct/{productId}/updateReview/{reviewId}")
    public ResponseEntity<Category> updateReview(@PathVariable int categoryId, @PathVariable int productId,@PathVariable int reviewId, @RequestBody Review theReview) {
        return categoryService.updateReview(categoryId, productId, reviewId,theReview);
    }

    @PutMapping("/{categoryId}/updateProduct/{productId}/updateSupplier/{suppllierId}")
    public ResponseEntity<Category> addSupplier(@PathVariable int categoryId, @PathVariable int productId, @PathVariable int suppllierId, @RequestBody Supplier theSupplier) {
        return categoryService.updateSupplier(categoryId, productId,suppllierId, theSupplier);
    }

    @PutMapping("/{categoryId}/updateProduct/{productId}/updateOrderItem/{orderId}")
    public ResponseEntity<Category> addOrderItem(@PathVariable int categoryId, @PathVariable int productId,@PathVariable int orderId, @RequestBody OrderItem orderItem) {
        return categoryService.updateOrderItem(categoryId, productId, orderId,orderItem);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int categoryId) {
        categoryRepository.deleteById(categoryId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{categoryId}/deleteProduct/{productId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int categoryId, @PathVariable int productId) {
        categoryService.deleteProduct(categoryId, productId);
        return ResponseEntity.noContent().build();
    }
}
