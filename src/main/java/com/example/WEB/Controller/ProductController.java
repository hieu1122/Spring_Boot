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
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/{productId}")
    public Product getOneProduct(@PathVariable int productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @PostMapping()
    public Product addproduct(@RequestBody Product theProduct) {
        return productRepository.save(theProduct);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable int productId,@RequestBody Product theProduct) {
        return productService.updateProduct(productId,theProduct);
    }

    @PutMapping("/product/updateReview/{productId}")
    public Product updateReview(@PathVariable int productId, @RequestBody List<Review> reviews) {
        return productService.Reviews(productId, reviews).getBody();
    }

    @PutMapping("/{productId}/updateReview/{reviewId}")
    public ResponseEntity<Review> updateReviews(@PathVariable int productId, @PathVariable int reviewId, @RequestBody Review reviews) {
        return productService.updateReview(productId,reviewId,reviews);
    }

    @PutMapping("/updateSupplier/{productId}")
    public Product updateSupplier(@PathVariable int productId, @RequestBody List<Supplier> suppliers) {
        return productService.Suppliers(productId, suppliers).getBody();
    }

    @PutMapping("/updateOrderItem/{productId}")
    public Product updateOrderItem(@PathVariable int productId, @RequestBody List<OrderItem> orderItems) {
        return productService.OrderItems(productId, orderItems).getBody();
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable int productId) {
        productRepository.deleteById(productId);
    }

    @DeleteMapping("/{productId}/deleteReview/{reviewId}")
    public void deleteReview(@PathVariable int productId,@PathVariable int reviewId) {
        productService.deleteReview(productId, reviewId);
    }

    @DeleteMapping("/{productId}/deleteSupplier/{supplierId}")
    public void deleteSupplier(@PathVariable int productId,@PathVariable int supplierId) {
        productService.deleteSupplier(productId, supplierId);
    }
    @DeleteMapping("/{productId}/deleteOrderItem/{orderitemId}")
    public void deleteOrderItem(@PathVariable int productId,@PathVariable int orderitemId) {
        productService.deleteOrderItem(productId, orderitemId);
    }
    //    @PutMapping("/product/updateProduct/{productId}")
//    public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product theProduct) {
//        Optional<Product> result = productRepository.findById(productId);
//        if (result.isPresent()) {
//            Product product = result.get();
//            productService.addProduct(product,theProduct);
//            Product updateProduct = productRepository.save(product);
//            return ResponseEntity.ok(updateProduct);
//        }
//        return ResponseEntity.notFound().build();
//    }

//    @PostMapping("/product")
//    public ResponseEntity<Product> addProduct(@RequestBody Product theProduct) {
//        Product product=new Product();
//        productService.addProduct(product,theProduct);
//
//        if(theProduct.getReviews()!=null){
//            productService.addReviews(product,theProduct.getReviews());
//        }
//
//        if(theProduct.getSuppliers()!=null){
//            productService.addSuppliers(product,theProduct.getSuppliers());
//        }
//
//
//       // Orderitem
//        if(theProduct.getOrderItems()!=null){
//            productService.addOrderItems(product,theProduct.getOrderItems());
//        }
//
//        Product addproduct = productRepository.save(product);
//        return ResponseEntity.ok(addproduct);
//    }
}
