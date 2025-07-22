package com.tastybites.FoodDeliveryApp.entity;

import com.tastybites.FoodDeliveryApp.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private LocalDateTime paymentTime;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String paymentMethod;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
