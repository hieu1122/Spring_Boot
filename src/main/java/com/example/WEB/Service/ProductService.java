package com.example.WEB.Service;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.Order_ItemRepository;
import com.example.WEB.Repository.ProductRepository;
import com.example.WEB.Repository.ReviewRepository;
import com.example.WEB.Repository.SupplieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private SupplieRepository supplieRepository;
    @Autowired
    private Order_ItemRepository orderItemRepository;
    @Autowired
    private ReviewService reviewService;


    public Product updateProduct(int id, Product theProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProductId not found"));
        product.setName(theProduct.getName());
        product.setDescription(theProduct.getDescription());
        product.setStockQuantity(theProduct.getStockQuantity());
        product.setPrice(theProduct.getPrice());
        return productRepository.save(product);
    }

//    public void updateReview(Product product, List<Review> reviews) {
//        Map<Integer, Review> reviewMap = product.getReviews().stream().collect(Collectors.toMap(Review::getReviewId, Function.identity()));
//
//        List<Review> newReview = reviews.stream().map(theReview ->
//                reviewMap.merge(theReview.getReviewId(), theReview, (existing, updated) -> {
//                    existing.setReviewId(updated.getReviewId());
//                    existing.setRating(updated.getRating());
//                    existing.setComment(updated.getComment());
//                    existing.setReviewDate(updated.getReviewDate());
//                    return existing;
//                })).collect(Collectors.toList());
//        product.setReviews(newReview);
//
//    }
//
//    public void updateSupplier(Product product, List<Supplier> theSupllier) {
//        Map<Integer, Supplier> result = product.getSuppliers().stream()
//                .collect(Collectors.toMap(Supplier::getSupplierId, Function.identity()));
//
//        List<Supplier> newSupplier = theSupllier.stream()
//                .map(supplier -> result.merge(supplier.getSupplierId(), supplier, (existing, update) -> {
//                    existing.setSupplierId(update.getSupplierId());
//                    existing.setName(update.getName());
//                    existing.setContactInfo(update.getContactInfo());
//                    return existing;
//                })).collect(Collectors.toList());
//
//        product.setSuppliers(newSupplier);
//    }

//    public void updateOrderItem(Product product, List<OrderItem> orderItems) {
//        Map<Integer, OrderItem> result = product.getOrderItems().stream()
//                .collect(Collectors.toMap(OrderItem::getOrderItemId, Function.identity()));
//        List<OrderItem> newOrderItem = orderItems.stream()
//                .map(orderItem -> result.merge(orderItem.getOrderItemId(), orderItem, (existing, update) -> {
//                    existing.setOrderItemId(update.getOrderItemId());
//                    existing.setQuantity(update.getQuantity());
//                    existing.setPrice(update.getPrice());
//                    return existing;
//                })).collect(Collectors.toList());
//        product.setOrderItems(newOrderItem);
//    }

    public ResponseEntity<Product> Reviews(int id, List<Review> reviews) {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            Product product = result.get();
            if (product.getReviews() != null) {
                product.setReviews(reviews);
                for (Review review : reviews) {
                    review.setProduct(product);
                }
                Product addReview = productRepository.save(product);
                return ResponseEntity.ok(addReview);
            }
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Review> updateReview(int productId, int reviewId, Review theReview ){
        Product product=productRepository.findById(productId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));

        Review review= reviewService.updateReview(reviewId,theReview).getBody();
        review.setProduct(product);
        Review updateReview=reviewRepository.save(review);
        return ResponseEntity.ok(updateReview);
    }

    public ResponseEntity<Product> updateReviews(int productId, int id, Review theReviews) {
        Optional<Product> productResult = productRepository.findById(productId);
        if (productResult.isPresent()) {
            Product product = productResult.get();
            List<Review> reviews = product.getReviews();
            if (reviews == null) {
                reviews = new ArrayList<>();
                product.setReviews(reviews);
            }
            reviews.add(theReviews);
            theReviews.setProduct(product);
            Product updatedProduct = productRepository.save(product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Product> Suppliers(int id, List<Supplier> suppliers) {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            Product product = result.get();
            product.setSuppliers(suppliers);
            for (Supplier supplierDTO : suppliers) {
                List<Product> products = new ArrayList<>();
                supplierDTO.setProducts(products);
                products.add(product);
            }
            Product addSupplier = productRepository.save(product);
            return ResponseEntity.ok(addSupplier);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Product> addSuppliers(int id, List<Supplier> suppliers) {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            Product product = result.get();
            List<Supplier> existingSuppliers = product.getSuppliers();
            if (existingSuppliers == null) {
                existingSuppliers = new ArrayList<>();
                product.setSuppliers(existingSuppliers);
            }

            for (Supplier supplier : suppliers) {
                if (!existingSuppliers.contains(supplier)) {
                    existingSuppliers.add(supplier);
                    List<Product> products = supplier.getProducts();
                    if (products == null) {
                        products = new ArrayList<>();
                        supplier.setProducts(products);
                    }
                    products.add(product);
                }
            }

            Product updatedProduct = productRepository.save(product);
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Product> OrderItems(int id, List<OrderItem> orderItems) {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            Product product = result.get();
            if (product.getOrderItems() != null) {
                product.setOrderItems(orderItems);
                for (OrderItem orderItem : orderItems) {
                    orderItem.setProduct(product);
                }
                Product addSupplier = productRepository.save(product);
                return ResponseEntity.ok(addSupplier);
            }

        }
        return ResponseEntity.notFound().build();
    }

    public void deleteReview(int id, int reviewId) {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
            if (reviewOptional.isPresent()) {
                Review review = reviewOptional.get();
                review.setProduct(null);
                reviewRepository.save(review);
            }
        }
    }

    public void deleteOrderItem(int id, int orderitemId) {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            Optional<OrderItem> orderItemOptional = orderItemRepository.findById(orderitemId);
            if (orderItemOptional.isPresent()) {
                OrderItem orderItem = orderItemOptional.get();
                orderItem.setProduct(null);
                orderItemRepository.save(orderItem);
            }
        }
    }

    public void deleteSupplier(int id, int supplierId) {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            Product product = result.get();
            Optional<Supplier> supplierOptional = supplieRepository.findById(supplierId);
            if (supplierOptional.isPresent()) {
                Supplier supplier = supplierOptional.get();
                supplier.setProducts(null);
                product.setSuppliers(null);
                supplieRepository.save(supplier);
                productRepository.save(product);
            }
        }
    }
}
