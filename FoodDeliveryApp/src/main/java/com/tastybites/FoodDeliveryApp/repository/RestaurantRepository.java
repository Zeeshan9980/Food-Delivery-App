package com.tastybites.FoodDeliveryApp.repository;

import com.tastybites.FoodDeliveryApp.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
}
