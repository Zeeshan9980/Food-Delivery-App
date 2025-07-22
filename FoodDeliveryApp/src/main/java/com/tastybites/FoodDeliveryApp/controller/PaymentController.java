package com.tastybites.FoodDeliveryApp.controller;

import com.tastybites.FoodDeliveryApp.dto.PaymentDTO;
import com.tastybites.FoodDeliveryApp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDTO> makePayment(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.makePayment(paymentDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentDetails(id));
    }
}