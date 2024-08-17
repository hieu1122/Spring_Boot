package com.example.WEB.Controller;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.CategoryRepository;
import com.example.WEB.Repository.ProductRepository;

import com.example.WEB.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getProduct() {
        List<Product> products= productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getOneProduct(@PathVariable int productId) {
        Product product= productService.Condition(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping()
    public ResponseEntity<Product> addProduct(@RequestBody Product theProduct) {
        Product product= productRepository.save(theProduct);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product theProduct) {
        return productService.updateProduct(productId,theProduct);
    }

    @PutMapping("/{productId}/updateReview/{reviewId}")
    public ResponseEntity<Product> updateReviews(@PathVariable int productId, @PathVariable int reviewId, @RequestBody Review reviews) {
        return productService.updateReview(productId,reviewId,reviews);
    }

    @PutMapping("/{productId}/updateSupplier/{supplierId}")
    public ResponseEntity<Product> updateSupplier(@PathVariable int productId,@PathVariable int supplierId, @RequestBody Supplier suppliers) {
        return productService.updateSupplier(productId,supplierId, suppliers);
    }

    @PutMapping("/{productId}/updateOrderItem/{itemId}")
    public ResponseEntity<Product> updateOrderItem(@PathVariable int productId,@PathVariable int itemId, @RequestBody OrderItem orderItem) {
        return productService.updateOrderItem(productId,itemId,orderItem);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        productRepository.deleteById(productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}/deleteReview/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable int productId,@PathVariable int reviewId) {
        productService.deleteReview(productId, reviewId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}/deleteSupplier/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable int productId,@PathVariable int supplierId) {
        productService.deleteSupplier(productId, supplierId);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{productId}/deleteOrderItem/{orderId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable int productId, @PathVariable int orderId) {
        productService.deleteOrderItem(productId, orderId);
        return ResponseEntity.noContent().build();
    }

}
