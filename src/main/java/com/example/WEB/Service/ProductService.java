package com.example.WEB.Service;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.OrderItemRepository;
import com.example.WEB.Repository.ProductRepository;
import com.example.WEB.Repository.ReviewRepository;
import com.example.WEB.Repository.SupplieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private SupplieRepository supplieRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private OrderItemService orderItemService;

    public Product Condition(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CategoryId not found"));
        return product;
    }

    public ResponseEntity<Product> updateProduct(int productId, Product theProduct) {
        Product product=Condition(productId);

        product.setName(theProduct.getName());
        product.setDescription(theProduct.getDescription());
        product.setStockQuantity(theProduct.getStockQuantity());
        product.setPrice(theProduct.getPrice());
        Product updateProduct = productRepository.save(product);
        return ResponseEntity.ok(updateProduct);
    }

    public ResponseEntity<Product> updateReview(int productId, int reviewId, Review theReview) {
        Condition(productId);

        Review review = reviewService.updateReview(reviewId, theReview).getBody();
        review.setProduct(Condition(productId));
        reviewRepository.save(review);
        return ResponseEntity.ok(Condition(productId));
    }

    public ResponseEntity<Product> updateSupplier(int productId, int supplierId, Supplier theSupplier) {
        Condition(productId);

        Supplier supplier = supplierService.updateSupplier(supplierId, theSupplier).getBody();
        Set<Product> products = new HashSet<>(supplier.getProducts());
        products.add(Condition(productId));
        supplier.setProducts(products);
        supplieRepository.save(supplier);
        return ResponseEntity.ok(Condition(productId));
    }

    public ResponseEntity<Product> updateOrderItem(int productId, int itemId, OrderItem orderItem) {
        Condition(productId);

        OrderItem orderItem1 = orderItemService.updateOrderItem(itemId, orderItem).getBody();
        orderItem1.setProduct(Condition(productId));
        orderItemRepository.save(orderItem1);
        return ResponseEntity.ok(Condition(productId));

    }

    public ResponseEntity<Void> deleteReview(int productId, int reviewId) {
        Condition(productId);

        reviewRepository.findById(reviewId)
                .map(review -> {
                    review.setProduct(null);
                    return reviewRepository.save(review);
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ReviewId not found"));
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> deleteOrderItem(int productId, int orderId) {
        Condition(productId);

        orderItemRepository.findById(orderId)
                .map(orderItem -> {
                    orderItem.setProduct(null);
                    return orderItemRepository.save(orderItem);
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"OrderId not found"));
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> deleteSupplier(int productId, int supplierId) {
        Condition(productId);

        supplieRepository.findById(supplierId)
                .map(supplier -> {
                    supplier.setProducts(null);
                    return supplieRepository.save(supplier);
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ReviewId not found"));
        return ResponseEntity.noContent().build();
    }
}
