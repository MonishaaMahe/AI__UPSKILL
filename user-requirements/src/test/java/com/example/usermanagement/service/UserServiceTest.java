package com.example.usermanagement.service;

import com.example.usermanagement.dto.AuthenticationRequest;
import com.example.usermanagement.dto.UserRegistrationRequest;
import com.example.usermanagement.dto.UserResponse;
import com.example.usermanagement.exception.ResourceNotFoundException;
import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_shouldCreateNewUser() {
        UserRegistrationRequest request = new UserRegistrationRequest();
        // set fields for request
        // ...
        when(userRepository.save(any(User.class))).thenReturn(new User());
        UserResponse response = userService.registerUser(request);
        assertNotNull(response);
    }

    @Test
    void getUserById_shouldReturnUserResponse() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserResponse response = userService.getUserById(1L);
        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void getUserById_shouldThrowResourceNotFoundException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(99L));
    }
} 