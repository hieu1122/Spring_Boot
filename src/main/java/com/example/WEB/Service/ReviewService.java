package com.example.WEB.Service;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.CustomerRepository;
import com.example.WEB.Repository.ProductRepository;
import com.example.WEB.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public ResponseEntity<Review> updateReview(@PathVariable int reviewId,@RequestBody Review theReview){
        Review review=reviewRepository.findById(reviewId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ReviewId not found"));
        review.setRating(theReview.getRating());
        review.setComment(theReview.getComment());
        review.setReviewDate(theReview.getReviewDate());
        return ResponseEntity.ok(review);
    }
}
