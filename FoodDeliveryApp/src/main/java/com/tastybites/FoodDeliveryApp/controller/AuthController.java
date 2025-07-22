package com.tastybites.FoodDeliveryApp.controller;

import com.tastybites.FoodDeliveryApp.entity.User;
import com.tastybites.FoodDeliveryApp.repository.UserRepository;
import com.tastybites.FoodDeliveryApp.security.CustomUserDetails;
import com.tastybites.FoodDeliveryApp.security.JwtAuthenticationFilter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetails customUserDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtHelper;

    // ✅ Register new user
    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "User already exists!";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    // ✅ Login and get token
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
            String token = jwtHelper.generateToken(userDetails);
            return token;
        } else {
            return "Invalid credentials";
        }
    }

    // 🧪 Test secure endpoint
    @GetMapping("/test")
    public String testToken() {
        return "Token is valid. You're authenticated!";
    }

    // 📦 DTO for login
    static class LoginRequest {
        private String email;
        private String password;

        // Getters and Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}