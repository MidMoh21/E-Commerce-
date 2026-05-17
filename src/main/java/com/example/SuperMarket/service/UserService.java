package com.example.SuperMarket.service;


import com.example.SuperMarket.dto.LoginRequestDTO;
import com.example.SuperMarket.dto.UserRequestDTO;
import com.example.SuperMarket.dto.UserResponseDTO;
import com.example.SuperMarket.mapper.UserMapper;
import com.example.SuperMarket.model.User;
import com.example.SuperMarket.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public void addUser(UserRequestDTO userRequestDTO) {

        if(userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if(userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = userMapper.toUser(userRequestDTO);
        userRepository.save(user);
    }

    public User loginUser(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail());
        if (user == null || !user.getPassword().equals(loginRequestDTO.getPassword())) {
            return null; // Invalid email or password
        }
        return user; // Login successful
    }
    
    public List<UserResponseDTO> getAllUsers() {

        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserResponseDTO)
                .toList();
    }

    public void updateUser(Long id, UserRequestDTO requestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        if(!user.getEmail().equals(requestDTO.getEmail()) && userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Update fields

        user.setUsername(requestDTO.getUsername());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());
        user.setAddress(requestDTO.getAddress());
        user.setPhoneNumber(requestDTO.getPhoneNumber());
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        return userMapper.toUserResponseDTO(user);
    }
}
