package com.example.SuperMarket.dto;


import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
    private String role;
}
