package com.example.WEB.Controller;

import com.example.WEB.Entity.Customer;
import com.example.WEB.Entity.Product;
import com.example.WEB.Entity.Review;
import com.example.WEB.Repository.CustomerRepository;
import com.example.WEB.Repository.ProductRepository;
import com.example.WEB.Repository.ReviewRepository;
import com.example.WEB.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/review")
    public List<Review> getReview() {
        return reviewRepository.findAll();
    }

    @GetMapping("/review/{id}")
    public Review getOneReview(@PathVariable int id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @PostMapping("/review/{productId}/{customerId}")
    public ResponseEntity<Review> addReview(@PathVariable int productId, @PathVariable int customerId, @RequestBody Review theReview) {
        return reviewService.addReview(productId,customerId,theReview);
    }

    @PutMapping("/review/{id}/{productId}/{customerId}")
    public ResponseEntity<Review> updateReview(@PathVariable int id,@PathVariable int productId,@PathVariable int customerId, @RequestBody Review theReview) {
        return reviewService.updateReview(id,productId, customerId, theReview);
    }

    @DeleteMapping("/review/{id}")
    public void deleteReview(@PathVariable int id) {
        reviewRepository.deleteById(id);
    }
}
