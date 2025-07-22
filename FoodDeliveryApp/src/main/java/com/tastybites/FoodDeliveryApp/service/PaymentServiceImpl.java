package com.tastybites.FoodDeliveryApp.service;

import com.tastybites.FoodDeliveryApp.dto.PaymentDTO;
import com.tastybites.FoodDeliveryApp.entity.Order;
import com.tastybites.FoodDeliveryApp.entity.Payment;
import com.tastybites.FoodDeliveryApp.enums.PaymentStatus;
import com.tastybites.FoodDeliveryApp.exception.ResourceNotFoundException;
import com.tastybites.FoodDeliveryApp.repository.OrderRepository;
import com.tastybites.FoodDeliveryApp.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public PaymentDTO makePayment(PaymentDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        Payment payment = Payment.builder()
                .amount(dto.getAmount())
                .paymentMethod(dto.getPaymentMethod())
                .status(PaymentStatus.COMPLETED)
                .paymentTime(LocalDateTime.now())
                .order(order)
                .build();

        Payment saved = paymentRepository.save(payment);

        return PaymentDTO.builder()
                .id(saved.getId())
                .amount(saved.getAmount())
                .paymentMethod(saved.getPaymentMethod())
                .paymentTime(saved.getPaymentTime())
                .status(saved.getStatus())
                .orderId(saved.getOrder().getId())
                .build();
    }

    @Override
    public PaymentDTO getPaymentDetails(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        return PaymentDTO.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .paymentTime(payment.getPaymentTime())
                .status(payment.getStatus())
                .orderId(payment.getOrder().getId())
                .build();
    }
}
