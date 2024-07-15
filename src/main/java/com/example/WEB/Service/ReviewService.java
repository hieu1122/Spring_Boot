package com.example.WEB.Service;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.CustomerRepository;
import com.example.WEB.Repository.ProductRepository;
import com.example.WEB.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
//    public void updateReview(Product product, List<Review> reviews) {
//        Map<Integer,Review> reviewMap = product.getReviews().stream().collect(Collectors.toMap(Review::getReviewId,Function.identity()));
//
//       List<Review> newReview=reviews.stream().map(theReview->
//               reviewMap.merge(theReview.getReviewId(),theReview,(existing,updated)->{
//                   existing.setRating(updated.getRating());
//                   existing.setComment(updated.getComment());
//                   existing.setReviewDate(updated.getReviewDate());
//                   return existing;
//               })).collect(Collectors.toList());
//       product.setReviews(newReview);
//    }
    public ResponseEntity<Review> addReview(@PathVariable int productId, @PathVariable int customerId, @RequestBody Review theReview) {
        Optional<Product> theProduct=productRepository.findById(productId);
        Optional<Customer> theCustomer=customerRepository.findById(customerId);
        if(theCustomer.isPresent() && theProduct.isPresent()){
            Customer customer=theCustomer.get();
            Product product=theProduct.get();

            Review review=new Review();
            review.setProduct(product);
            review.setCustomer(customer);
            review.setRating(theReview.getRating());
            review.setComment(theReview.getComment());
            review.setReviewDate(theReview.getReviewDate());

            Review addReview= reviewRepository.save(review);
            return ResponseEntity.ok(addReview);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Review> updateReview(@PathVariable int id,@PathVariable int productId, @PathVariable int customerId, @RequestBody Review theReview) {
        Optional<Review> result=reviewRepository.findById(id);
        if(result.isPresent()){
            Review review=result.get();
            Optional<Product> theProduct=productRepository.findById(productId);
            Optional<Customer> theCustomer=customerRepository.findById(customerId);
            if(theCustomer.isPresent() && theProduct.isPresent()){
                Customer customer=theCustomer.get();
                Product product=theProduct.get();

                review.setProduct(product);
                review.setCustomer(customer);
                review.setRating(theReview.getRating());
                review.setComment(theReview.getComment());
                review.setReviewDate(theReview.getReviewDate());

                Review addReview= reviewRepository.save(review);
                return ResponseEntity.ok(addReview);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
