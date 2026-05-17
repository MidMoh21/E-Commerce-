package com.example.SuperMarket.controller;

import com.example.SuperMarket.dto.ProductRequestDTO;
import com.example.SuperMarket.dto.ProductResponseDTO;
import com.example.SuperMarket.model.Product;
import com.example.SuperMarket.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        productService.addProduct(productRequestDTO);
        return ResponseEntity.ok("Product added successfully");
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO requestDTO) {
        productService.updateProduct(id, requestDTO);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
