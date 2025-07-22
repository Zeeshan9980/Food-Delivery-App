package com.tastybites.FoodDeliveryApp.service;

import com.tastybites.FoodDeliveryApp.dto.PaymentDTO;

public interface PaymentService {
    PaymentDTO makePayment(PaymentDTO paymentDTO);
    PaymentDTO getPaymentDetails(Long paymentId);
}
