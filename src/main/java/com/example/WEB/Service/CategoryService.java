package com.example.WEB.Service;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private SupplieRepository supplieRepository;
    @Autowired
    private Order_ItemRepository orderItemRepository;
    public void getProduct(Category category, List<Product> theProduct){
        Map<Integer, Product> productMap=category.getProducts().stream()
                .collect(Collectors.toMap(Product::getProductId, Function.identity()));
        List<Product> newProduct=theProduct.stream()
                .map(product -> productMap.merge(product.getProductId(),product,(existing,update)->{
            existing.setName(update.getName());
            existing.setDescription(update.getDescription());
            existing.setPrice(update.getPrice());
            existing.setStockQuantity(update.getStockQuantity());

            return existing;
        })).collect(Collectors.toList());

       category.setProducts(newProduct);
    }
    public Category updateCategory(int id,Category theCategory){
        Optional<Category> result=categoryRepository.findById(id);
        Category updateCategory = null;
        if(result.isPresent()){
            Category category=result.get();
            category.setName(theCategory.getName());
            category.setDescription(theCategory.getDescription());
            updateCategory= categoryRepository.save(category);
        }
        return updateCategory;
    }
    public void setCategory(Category category,Category theCategory){

        category.setName(theCategory.getName());
        category.setDescription(theCategory.getDescription());
    }
    public ResponseEntity< Category> setProduct(int id, List<Product> products) {
        Optional<Category> result=categoryRepository.findById(id);
        if(result.isPresent()){
            Category category=result.get();
            category.setProducts(products);
            for(Product product : products){
                product.setCategory(category);
            }
            Category addProduct=categoryRepository.save(category);
            return ResponseEntity.ok(addProduct);
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<Category> Review(int categoryId,int productId,List<Review> reviews){
        Optional<Category> result=categoryRepository.findById(categoryId);
        if(result.isPresent()){
            Category category=result.get();
            Optional<Product> result1=productRepository.findById(productId);
            if (result1.isPresent()) {
                productService.Reviews(productId, reviews);
            }
            Category addProduct=categoryRepository.save(category);
            return ResponseEntity.ok(addProduct);
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<Category> Supplier(int categoryId, int productId, List<Supplier> suppliers){
        Optional<Category> result=categoryRepository.findById(categoryId);
        if(result.isPresent()){
            Category category=result.get();
            Optional<Product> result1=productRepository.findById(productId);
            if (result1.isPresent()) {
                productService.Suppliers(productId, suppliers);
            }
            Category addProduct=categoryRepository.save(category);
            return ResponseEntity.ok(addProduct);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Category> OrderItem(int categoryId, int productId, List<OrderItem> OrderItem){
        Optional<Category> result=categoryRepository.findById(categoryId);
        if(result.isPresent()){
            Category category=result.get();
            Optional<Product> result1=productRepository.findById(productId);
            if (result1.isPresent()) {
                productService.OrderItems(productId, OrderItem);
            }
            Category addProduct=categoryRepository.save(category);
            return ResponseEntity.ok(addProduct);
        }
        return ResponseEntity.notFound().build();
    }
    public void deleteProduct(int id,int productId){
        Optional<Category> result=categoryRepository.findById(id);
        if(result.isPresent()){
            Optional<Product> productOptional=productRepository.findById(productId);
            if(productOptional.isPresent()){
                Product product=productOptional.get();
                product.setCategory(null);
                productRepository.save(product);
            }
        }
    }


}
