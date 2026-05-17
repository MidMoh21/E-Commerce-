package com.example.SuperMarket.service;


import com.example.SuperMarket.dto.ProductRequestDTO;
import com.example.SuperMarket.dto.ProductResponseDTO;
import com.example.SuperMarket.mapper.ProductMapper;
import com.example.SuperMarket.model.Product;
import com.example.SuperMarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    
    public void addProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toProduct(productRequestDTO);
        productRepository.save(product);
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponseDTO)
                .toList();
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
        return productMapper.toProductResponseDTO(product);
    }

    public void updateProduct(Long id, ProductRequestDTO requestDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));

        // Update fields
        product.setName(requestDTO.getName());
        product.setDescription(requestDTO.getDescription());
        product.setPrice(requestDTO.getPrice());
        product.setStock(requestDTO.getStock());
        product.setCategory(requestDTO.getCategory());
        product.setImageUrl(requestDTO.getImageUrl());
        product.setAvailable(requestDTO.isAvailable());
        
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if(!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
