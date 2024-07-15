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
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("/category")
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    @GetMapping("/category/{id}")
    public Category getOneCategory(@PathVariable int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @PostMapping("/category")
    public Category addCategory(@RequestBody Category theCategory) {
        return categoryRepository.save(theCategory);
    }

    @PutMapping("/category/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category theCategory) {
        return categoryService.updateCategory(id, theCategory);
    }

    @PutMapping("/category/updateProduct/{id}")
    public ResponseEntity<Category> addProduct(@PathVariable int id, @RequestBody List<Product> products) {
        return categoryService.setProduct(id, products);
    }

    @PutMapping("/category/updateReview/{categoryId}/{productId}")
    public Category addReview(@PathVariable int categoryId, @PathVariable int productId, @RequestBody List<Review> reviews) {
        return categoryService.Review(categoryId, productId, reviews).getBody();
    }

    @PutMapping("/category/updateSupplier/{categoryId}/{productId}")
    public Category addSupplier(@PathVariable int categoryId, @PathVariable int productId, @RequestBody List<Supplier> suppliers) {
        return categoryService.Supplier(categoryId, productId, suppliers).getBody();
    }

    @PutMapping("/category/updateOrderItem/{categoryId}/{productId}")
    public Category addOrderItem(@PathVariable int categoryId, @PathVariable int productId, @RequestBody List<OrderItem> orderItems) {
        return categoryService.OrderItem(categoryId, productId, orderItems).getBody();
    }

    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryRepository.deleteById(id);
    }

    @DeleteMapping("/category/{id}/deleteProduct/{productId}")
    public void deleteCategory(@PathVariable int id, @PathVariable int productId) {
        categoryService.deleteProduct(id, productId);
    }
}
