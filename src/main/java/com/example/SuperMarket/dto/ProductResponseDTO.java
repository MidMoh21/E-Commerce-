package com.example.SuperMarket.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;
    private String imageUrl;
    private boolean available;
}

