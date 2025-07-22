package com.tastybites.FoodDeliveryApp.dto;

import com.tastybites.FoodDeliveryApp.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Long id;

    private LocalDateTime orderTime;

    private OrderStatus status;

    private Long userId;

    private Long restaurantId;

    private List<Long> foodItemIds;

    private double totalAmount;
}