package com.example.WEB.Controller;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.*;
import com.example.WEB.Service.CategoryService;
import com.example.WEB.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("/category")
    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    @GetMapping("/category/{id}")
    public Category getOneCategory(@PathVariable int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @PostMapping("/category")
    public Category addCategory(@RequestBody Category theCategory) {
        return categoryRepository.save(theCategory);
    }

    @PutMapping("/category/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category theCategory) {
        return categoryService.updateCategory(id, theCategory);
    }

    @PutMapping("/category/updateProduct/{id}")
    public ResponseEntity<Category> addProduct(@PathVariable int id, @RequestBody List<Product> products) {
        return categoryService.setProduct(id, products);
    }

    @PutMapping("/category/updateReview/{categoryId}/{productId}")
    public Category addReview(@PathVariable int categoryId, @PathVariable int productId, @RequestBody List<Review> reviews) {
        return categoryService.Review(categoryId, productId, reviews).getBody();
    }

    @PutMapping("/category/updateSupplier/{categoryId}/{productId}")
    public Category addSupplier(@PathVariable int categoryId, @PathVariable int productId, @RequestBody List<Supplier> suppliers) {
        return categoryService.Supplier(categoryId, productId, suppliers).getBody();
    }

    @PutMapping("/category/updateOrderItem/{categoryId}/{productId}")
    public Category addOrderItem(@PathVariable int categoryId, @PathVariable int productId, @RequestBody List<OrderItem> orderItems) {
        return categoryService.OrderItem(categoryId, productId, orderItems).getBody();
    }

    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryRepository.deleteById(id);
    }

    @DeleteMapping("/category/{id}/deleteProduct/{productId}")
    public void deleteCategory(@PathVariable int id, @PathVariable int productId) {
        categoryService.deleteProduct(id, productId);
    }

//    @PostMapping("/category/addproduct")
//    public ResponseEntity<Category> addproduct(@RequestBody Product product){
//
//    }

//    @PostMapping("/category")
//    public ResponseEntity<Category> createCategory(@RequestBody Category categoryDTO) {
//        Category category = new Category();
//        category.setName(categoryDTO.getName());
//        category.setDescription(categoryDTO.getDescription());
//
//        List<Product> products=categoryDTO.getProducts();
//        category.setProducts(products);
//        for(Product product : products){
//            product.setCategory(category);
//
////            if(product.getReviews()!=null){
////                productService.addReviews(product);
////            }
////            if(product.getSuppliers()!=null){
////                productService.addSuppliers(product);
////            }
////
////            if(product.getOrderItems()!=null){
////                productService.addOrderItems(product);
////            }
//        }
//
//        Category addCategory = categoryRepository.save(category);
//        return ResponseEntity.ok(addCategory);
//    }


//    @PutMapping("/category/{id}")
//    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category theCategory) {
//        Optional<Category> result = categoryRepository.findById(id);
//        if (result.isPresent()) {
//            Category category = result.get();
//            category.setName(theCategory.getName());
//            category.setDescription(theCategory.getDescription());
//
//            categoryService.getProduct(category, theCategory.getProducts());
//
//            //Review
//            for (Product product : category.getProducts()) {
//                for (Product updatedProduct : theCategory.getProducts()) {
//                    if (product.getProductId() == updatedProduct.getProductId()) {
//                        productService.updateReview(product, updatedProduct.getReviews());
//                        break;
//                    }
//                }
//            }
//
//            //Supplier
//            for (Product product : category.getProducts()) {
//                for (Product product1 : theCategory.getProducts()) {
//                    if (product.getProductId() == product1.getProductId()) {
//                        productService.updateSupplier(product, product1.getSuppliers());
//                        break;
//                    }
//                }
//            }
//
//            //OrderItem
//            for (Product product : category.getProducts()) {
//                for (Product product1 : theCategory.getProducts()) {
//                    if (product.getProductId() == product1.getProductId()) {
//                        productService.updateOrderItem(product, product1.getOrderItems());
//                        break;
//                    }
//                }
//            }
//            Category updateCategory = categoryRepository.save(category);
//            return ResponseEntity.ok(updateCategory);
//        }
//        return ResponseEntity.notFound().build();
//    }

//    @PostMapping("/category/{id}")
//    public ResponseEntity<Category> updateCategory(@PathVariable int id,@RequestBody Category theCategory){
//        Optional<Category> result=categoryRepository.findById(id);
//        if(result.isPresent()){
//            Category category=result.get();
//
//        }
//    }

//    @DeleteMapping("/category/deleteProduct/{categoryId}/{productId}")
//    public ResponseEntity<Category> deleteProduct(@PathVariable int categoryId,@PathVariable int productId){
//        Optional<Category> reslut=categoryRepository.findById(categoryId);
//        if(reslut.isPresent()){
//            Category category=reslut.get();
//            Optional<Product> resultProduct=productRepository.findById(productId);
//            if(resultProduct.isPresent()){
//                Product product=resultProduct.get();
//                productRepository.delete(product);
//            }
//
//            Category deleteCategory=categoryRepository.save(category);
//            return ResponseEntity.ok(deleteCategory);
//        }
//        return ResponseEntity.notFound().build();
//    }
}
