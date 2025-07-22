package com.tastybites.FoodDeliveryApp.service;

import com.tastybites.FoodDeliveryApp.dto.RestaurantDTO;
import com.tastybites.FoodDeliveryApp.entity.Restaurant;
import com.tastybites.FoodDeliveryApp.exception.ResourceNotFoundException;
import com.tastybites.FoodDeliveryApp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RestaurantServiceImpl implements RestaurantService{
    @Autowired
    private RestaurantRepository restaurantRepo;
    @Override
    public Restaurant addRestaurant(RestaurantDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setContactNumber(dto.getContactNumber());
        return restaurantRepo.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepo.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
    }
}
