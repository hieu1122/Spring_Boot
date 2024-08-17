package com.example.WEB.Controller;

import com.example.WEB.Entity.Customer;
import com.example.WEB.Entity.Product;
import com.example.WEB.Entity.Review;
import com.example.WEB.Repository.CustomerRepository;
import com.example.WEB.Repository.ProductRepository;
import com.example.WEB.Repository.ReviewRepository;
import com.example.WEB.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<List<Review>> getReview() {
        List<Review> review=reviewRepository.findAll();
        return ResponseEntity.ok(review);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getOneReview(@PathVariable int reviewId) {
        Review review=reviewRepository.findById(reviewId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(review);
    }

    @PostMapping()
    public ResponseEntity <Review> addReview(@RequestBody Review theReview) {
        Review review=reviewRepository.save(theReview);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable int reviewId, @RequestBody Review theReview) {
        return reviewService.updateReview(reviewId, theReview);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable int reviewId) {
        reviewRepository.deleteById(reviewId);
        return ResponseEntity.noContent().build();
    }
}
