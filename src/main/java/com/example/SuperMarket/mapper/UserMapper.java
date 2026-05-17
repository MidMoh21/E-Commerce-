package com.example.SuperMarket.mapper;


import com.example.SuperMarket.dto.UserRequestDTO;
import com.example.SuperMarket.dto.UserResponseDTO;
import com.example.SuperMarket.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // From User to UserResponseDTO
    public UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole());
        return dto;
    }

    public User toUser(UserRequestDTO userRequestDTO) {
        User user = new User();

        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setAddress(userRequestDTO.getAddress());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        // Role is not set here as it's not part of the UserRequestDTO
        return user;
    }
}
