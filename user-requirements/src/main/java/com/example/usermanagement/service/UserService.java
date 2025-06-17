package com.example.usermanagement.service;

import com.example.usermanagement.dto.UserRegistrationRequest;
import com.example.usermanagement.dto.UserResponse;
import com.example.usermanagement.model.User;

import java.util.List;

public interface UserService {
    UserResponse registerUser(UserRegistrationRequest request);
    UserResponse getUserById(Long id);
    UserResponse getUserByEmail(String email);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(Long id, UserRegistrationRequest request);
    void deleteUser(Long id);
    User getAuthenticatedUser();
} 