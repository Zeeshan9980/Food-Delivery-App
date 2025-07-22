package com.tastybites.FoodDeliveryApp.controller;

import com.tastybites.FoodDeliveryApp.dto.UserDTO;
import com.tastybites.FoodDeliveryApp.entity.User;
import com.tastybites.FoodDeliveryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        User newUser = userService.registerUser(userDTO);
        return ResponseEntity.status(201).body(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}