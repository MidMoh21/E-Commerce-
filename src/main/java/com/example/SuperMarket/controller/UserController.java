package com.example.SuperMarket.controller;


import com.example.SuperMarket.dto.LoginRequestDTO;
import com.example.SuperMarket.dto.UserRequestDTO;
import com.example.SuperMarket.dto.UserResponseDTO;
import com.example.SuperMarket.mapper.UserMapper;
import com.example.SuperMarket.model.User;
import com.example.SuperMarket.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserRequestDTO userRequestDTO) {
        
        userService.addUser(userRequestDTO);
        return ResponseEntity.ok("User added successfully");

    }
    
    @PostMapping("/Login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
            User user = userService.loginUser(loginRequestDTO);
            if (user == null) {
                return ResponseEntity.status(401).body("Invalid email or password");
            }
        return ResponseEntity.ok(userMapper.toUserResponseDTO(user));
    }
    
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
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
