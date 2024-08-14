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
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{categoryId}")
    public Category getOneCategory(@PathVariable int categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @PostMapping()
    public Category addCategory(@RequestBody Category theCategory) {
        return categoryRepository.save(theCategory);
    }

    @PutMapping("/{categoryId}")
    public Category updateCategory(@PathVariable int categoryId, @RequestBody Category theCategory) {
        return categoryService.updateCategory(categoryId, theCategory);
    }

    @PutMapping("/{categoryId}/updateProduct/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable int categoryId,@PathVariable int productId, @RequestBody Product product) {
        return categoryService.updateProduct(categoryId,productId, product);
    }

//    @PutMapping("/{categoryId}/updateReview/{productId}")
//    public ResponseEntity<Review> updateReview(@PathVariable int categoryId, @PathVariable int productId, @RequestBody List<Review> reviews) {
//        return categoryService.Review(categoryId, productId, reviews).getBody();
//    }

    @PutMapping("/{categoryId}/updateSupplier/{productId}")
    public Category addSupplier(@PathVariable int categoryId, @PathVariable int productId, @RequestBody List<Supplier> suppliers) {
        return categoryService.Supplier(categoryId, productId, suppliers).getBody();
    }

    @PutMapping("/{categoryId}/updateOrderItem/{productId}")
    public Category addOrderItem(@PathVariable int categoryId, @PathVariable int productId, @RequestBody List<OrderItem> orderItems) {
        return categoryService.OrderItem(categoryId, productId, orderItems).getBody();
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable int categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @DeleteMapping("/{categoryId}/deleteProduct/{productId}")
    public void deleteCategory(@PathVariable int categoryId, @PathVariable int productId) {
        categoryService.deleteProduct(categoryId, productId);
    }
}
