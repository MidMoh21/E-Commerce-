package com.example.SuperMarket.controller;


import com.example.SuperMarket.dto.UserRequestDTO;
import com.example.SuperMarket.dto.UserResponseDTO;
import com.example.SuperMarket.model.User;
import com.example.SuperMarket.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserRequestDTO userRequestDTO) {
        // Here you would typically call a method in the UserService to save the user
        userService.addUser(userRequestDTO);
        return ResponseEntity.ok("User added successfully");

    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO requestDTO)
    {
        userService.updateUser(id, requestDTO);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}
