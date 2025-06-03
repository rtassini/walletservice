package com.recargapay.walletservice.service;

import com.recargapay.walletservice.dto.UserDTO;
import com.recargapay.walletservice.entities.User;
import com.recargapay.walletservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Service
@Validated
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Add methods to handle user-related operations, such as creating, updating, and retrieving users.
    // Example method to create a user
    public UserDTO createUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .createdAt(LocalDateTime.now())
                .build();

        // Validate user data if necessary
        // Save the user to the repository
        User savedUser = userRepository.save(user);

        // Convert saved User entity to UserDTO
        UserDTO savedUserDTO = new UserDTO();
        savedUserDTO.setUserId(savedUser.getUserId());
        savedUserDTO.setName(savedUser.getName());
        savedUserDTO.setEmail(savedUser.getEmail());
        savedUserDTO.setCreatedAt(savedUser.getCreatedAt());
        return savedUserDTO;

    }



}
