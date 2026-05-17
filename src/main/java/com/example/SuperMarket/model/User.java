package com.example.SuperMarket.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users") // name of the table in the database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String address;

    private String phoneNumber;

    private String role = "CUSTOMER"; // e.g., "USER", "ADMIN"


}
