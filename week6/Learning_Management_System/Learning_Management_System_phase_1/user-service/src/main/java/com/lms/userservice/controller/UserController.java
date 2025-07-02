package com.lms.userservice.controller;

import com.lms.userservice.dto.UserDto;
import com.lms.userservice.model.User;
import com.lms.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public Mono<ResponseEntity<UserDto>> register(@RequestBody UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .passwordHash(userDto.getUsername() + "_hash") // Dummy hash
                .role(userDto.getRole())
                .build();
        User saved = userService.registerUser(user);
        UserDto dto = new UserDto();
        dto.setId(saved.getId());
        dto.setUsername(saved.getUsername());
        dto.setEmail(saved.getEmail());
        dto.setRole(saved.getRole());
        return Mono.just(ResponseEntity.ok(dto));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserDto>> getUser(@PathVariable Long id) {
        Optional<User> userOpt = userService.getUserById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole());
            return Mono.just(ResponseEntity.ok(dto));
        } else {
            return Mono.just(ResponseEntity.notFound().build());
        }
    }

    @GetMapping("/{id}/roles")
    public Mono<ResponseEntity<String>> getUserRole(@PathVariable Long id) {
        Optional<User> userOpt = userService.getUserById(id);
        if (userOpt.isPresent()) {
            return Mono.just(ResponseEntity.ok(userOpt.get().getRole()));
        } else {
            return Mono.just(ResponseEntity.notFound().build());
        }
    }

    // Dummy login endpoint
    @PostMapping("/auth/login")
    public Mono<ResponseEntity<String>> login(@RequestBody UserDto userDto) {
        // In real app, validate password, issue JWT
        return Mono.just(ResponseEntity.ok("dummy-jwt-token"));
    }
} 