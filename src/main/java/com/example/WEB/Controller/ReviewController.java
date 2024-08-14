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
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewService reviewService;

    @GetMapping()
    public List<Review> getReview() {
        return reviewRepository.findAll();
    }

    @GetMapping("/{reviewId}")
    public Review getOneReview(@PathVariable int reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @PostMapping()
    public Review addReview(@RequestBody Review theReview) {
        return reviewRepository.save(theReview);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable int reviewId, @RequestBody Review theReview) {
        return reviewService.updateReview(reviewId, theReview);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable int reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
