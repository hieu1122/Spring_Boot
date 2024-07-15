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
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Product getOneProduct(@PathVariable int id) {
        return productRepository.findById(id).orElse(null);
    }

    @GetMapping("/product/getCategoryId/{id}")
    public List<Product> getCategoryId(@PathVariable int id) {
        return productRepository.findByCategoryCategoryId(id);
    }

    @PostMapping("/product")
    public Product addproduct(@RequestBody Product theProduct) {
        return productRepository.save(theProduct);
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/product/updateReview/{productId}")
    public Product updateReview(@PathVariable int productId, @RequestBody List<Review> reviews) {
        return productService.Reviews(productId, reviews).getBody();
    }

    @PutMapping("/product/{productId}/updateReview/{id}")
    public Product updateReviews(@PathVariable int productId, @PathVariable int id, @RequestBody Review reviews) {
        return productService.updateReviews(productId, id, reviews).getBody();
    }

    @PutMapping("/product/updateSupplier/{productId}")
    public Product updateSupplier(@PathVariable int productId, @RequestBody List<Supplier> suppliers) {
        return productService.Suppliers(productId, suppliers).getBody();
    }

    @PutMapping("/product/updateOrderItem/{productId}")
    public Product updateOrderItem(@PathVariable int productId, @RequestBody List<OrderItem> orderItems) {
        return productService.OrderItems(productId, orderItems).getBody();
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
    }

    @DeleteMapping("/product/{id}/deleteReview/{reviewId}")
    public void deleteReview(@PathVariable int id,@PathVariable int reviewId) {
        productService.deleteReview(id, reviewId);
    }

    @DeleteMapping("/product/{id}/deleteSupplier/{supplierId}")
    public void deleteSupplier(@PathVariable int id,@PathVariable int supplierId) {
        productService.deleteSupplier(id, supplierId);
    }
    @DeleteMapping("/product/{id}/deleteOrderItem/{orderitemId}")
    public void deleteOrderItem(@PathVariable int id,@PathVariable int orderitemId) {
        productService.deleteOrderItem(id, orderitemId);
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
