package com.tastybites.FoodDeliveryApp.service;

import com.tastybites.FoodDeliveryApp.dto.RestaurantDTO;
import com.tastybites.FoodDeliveryApp.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant addRestaurant(RestaurantDTO dto);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(Long id);
}
