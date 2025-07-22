package com.tastybites.FoodDeliveryApp.repository;

import com.tastybites.FoodDeliveryApp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
