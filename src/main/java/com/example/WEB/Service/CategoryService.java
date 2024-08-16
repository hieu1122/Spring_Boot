package com.example.WEB.Service;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.*;
import org.aspectj.weaver.reflect.ReflectionWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    public Category Condition(int categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CategoryId not found"));
        return category;
    }

    public Category updateCategory(int id, Category theCategory) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(theCategory.getName());
            category.setDescription(theCategory.getDescription());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CategoryId not found with id " + id));
    }

    public ResponseEntity<Category> updateProduct(int categoryId, int productId, Product products) {
        Condition(categoryId);

        Product existingProduct = productService.updateProduct(productId, products).getBody();
        existingProduct.setCategory(Condition(categoryId));
        productRepository.save(existingProduct);
        return ResponseEntity.ok(Condition(categoryId));
    }

    public ResponseEntity<Category> updateReview(int categoryId, int productId, int reviewId, Review theReview) {
        Condition(categoryId);

        Product product=productService.updateReview(productId,reviewId,theReview).getBody();
        product.setCategory(Condition(categoryId));
        productRepository.save(product);
        return ResponseEntity.ok(Condition(categoryId));
    }

    public ResponseEntity<Category> updateSupplier(int categoryId, int productId,int supplierId, Supplier theSupplier) {
        Condition(categoryId);

        Product product=productService.updateSupplier(productId,supplierId,theSupplier).getBody();
        product.setCategory(Condition(categoryId));
        productRepository.save(product);
        return ResponseEntity.ok(Condition(categoryId));
    }

    public ResponseEntity<Category> updateOrderItem(int categoryId, int productId,int orderId, OrderItem orderItem) {
        Condition(categoryId);

        Product product=productService.updateOrderItem(productId,orderId,orderItem).getBody();
        product.setCategory(Condition(categoryId));
        productRepository.save(product);
        return ResponseEntity.ok(Condition(categoryId));
    }

    public ResponseEntity<Void> deleteProduct(int categoryId, int productId) {
        Condition(categoryId);

        productRepository.findById(productId)
                .map(product -> {
                    product.setCategory(null);
                    return productRepository.save(product);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProductId not found"));
        return ResponseEntity.noContent().build();
    }


}
