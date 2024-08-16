package com.example.WEB.Controller;

import com.example.WEB.Entity.ProductSupplier;
import com.example.WEB.Entity.ProductSupplierId;
import com.example.WEB.Repository.ProductSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductSupplierController {
    @Autowired
    private ProductSupplierRepository productSupplierRepository;

    @GetMapping("/productsupplier")
    public List<ProductSupplier> getProductSupplier() {
        return productSupplierRepository.findAll();
    }

//    @GetMapping("/productsupplier/{productId}/{supploerId}")
//    public ProductSupplier getOneProductSupplier(@PathVariable int productId, @PathVariable int supploerId) {
//        return productSupplierRepository.findByProductIdAndSupplierId(productId, supploerId);
//    }

    @PostMapping("/productsupplier")
    public ProductSupplier addProductSupplier(@RequestBody ProductSupplier productSupplier) {
        return productSupplierRepository.save(productSupplier);
    }

    @DeleteMapping("/productsupplier/{productId}/{supplierId}")
    public void deleteOneProductSupplier(@PathVariable int productId, @PathVariable int supplierId) {
        ProductSupplierId id = new ProductSupplierId();
        id.setSupplierId(supplierId);
        id.setProductId(productId);
        productSupplierRepository.deleteById(id);
    }
    @PutMapping("/productsupplier/{oldProductId}/{oldSupplierId}")
    public ResponseEntity<ProductSupplier> updateProductSupplier(
            @PathVariable("oldProductId") int oldProductId,
            @PathVariable("oldSupplierId") int oldSupplierId,
            @RequestBody ProductSupplier updatedProductSupplier) {

        // Tạo khóa chính cũ
        ProductSupplierId oldId = new ProductSupplierId();
        oldId.setProductId(oldProductId);
        oldId.setSupplierId(oldSupplierId);

        // Tìm kiếm `ProductSupplier` cũ
        Optional<ProductSupplier> optionalOldProductSupplier = productSupplierRepository.findById(oldId);
        if (optionalOldProductSupplier.isPresent()) {
            ProductSupplier oldProductSupplier = optionalOldProductSupplier.get();

            // Cập nhật `productId` và `supplierId`
            oldProductSupplier.setProductId(updatedProductSupplier.getProductId());
            oldProductSupplier.setSupplierId(updatedProductSupplier.getSupplierId());

            // Lưu thay đổi
            ProductSupplier savedProductSupplier = productSupplierRepository.save(oldProductSupplier);
            return ResponseEntity.ok(savedProductSupplier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
