package com.tastybites.FoodDeliveryApp.dto;

import com.tastybites.FoodDeliveryApp.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Long id;
    private double amount;
    private LocalDateTime paymentTime;
    private PaymentStatus status;
    private String paymentMethod;
    private Long orderId;
    }