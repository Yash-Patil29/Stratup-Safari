package com.example.StartupSafari.controller;

import com.example.StartupSafari.model.User;
import com.example.StartupSafari.model.UserLoginRequest;
import com.example.StartupSafari.repository.UserRepository;
import com.example.StartupSafari.service.UserService;
import com.example.StartupSafari.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder,UserRepository userRepository) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginRequest loginRequest) {
        Optional<User> optionalUser = userService.getUserByEmail(loginRequest.getEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("role", user.getRole().name());

                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid credentials"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
