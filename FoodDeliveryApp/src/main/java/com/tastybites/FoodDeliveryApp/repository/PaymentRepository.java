package com.tastybites.FoodDeliveryApp.repository;

import com.tastybites.FoodDeliveryApp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
