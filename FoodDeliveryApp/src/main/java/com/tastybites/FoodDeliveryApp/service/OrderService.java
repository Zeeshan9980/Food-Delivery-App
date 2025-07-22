package com.tastybites.FoodDeliveryApp.service;

import com.tastybites.FoodDeliveryApp.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO placeOrder(OrderDTO orderDTO);

    OrderDTO getOrderById(Long id);

    List<OrderDTO> getAllOrders();

    void deleteOrder(Long id);
}
