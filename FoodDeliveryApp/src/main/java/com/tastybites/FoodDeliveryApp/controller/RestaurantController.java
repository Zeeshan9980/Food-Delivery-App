package com.tastybites.FoodDeliveryApp.controller;

import com.tastybites.FoodDeliveryApp.dto.RestaurantDTO;
import com.tastybites.FoodDeliveryApp.entity.Restaurant;
import com.tastybites.FoodDeliveryApp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody RestaurantDTO dto) {
        Restaurant saved = restaurantService.addRestaurant(dto);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }
}