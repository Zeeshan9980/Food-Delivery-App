package com.tastybites.FoodDeliveryApp.service;

import com.tastybites.FoodDeliveryApp.dto.OrderDTO;
import com.tastybites.FoodDeliveryApp.entity.MenuItem;
import com.tastybites.FoodDeliveryApp.entity.Order;
import com.tastybites.FoodDeliveryApp.entity.Restaurant;
import com.tastybites.FoodDeliveryApp.entity.User;
import com.tastybites.FoodDeliveryApp.enums.OrderStatus;
import com.tastybites.FoodDeliveryApp.repository.MenuItemRepository;
import com.tastybites.FoodDeliveryApp.repository.OrderRepository;
import com.tastybites.FoodDeliveryApp.repository.RestaurantRepository;
import com.tastybites.FoodDeliveryApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository foodItemRepository;

    @Override
    public OrderDTO placeOrder(OrderDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId()).orElseThrow();
        List<MenuItem> items = foodItemRepository.findAllById(dto.getFoodItemIds());

        double totalAmount = items.stream().mapToDouble(MenuItem::getPrice).sum();

        Order order = Order.builder()
                .orderTime(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .user(user)
                .restaurant(restaurant)
                .items(items)
                .totalAmount(totalAmount)
                .build();

        order = orderRepository.save(order);

        dto.setId(order.getId());
        dto.setOrderTime(order.getOrderTime());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());

        return dto;
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return convertToDTO(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .orderTime(order.getOrderTime())
                .status(order.getStatus())
                .userId(order.getUser().getId())
                .restaurantId(order.getRestaurant().getId())
                .foodItemIds(order.getItems().stream().map(MenuItem::getId).collect(Collectors.toList()))
                .totalAmount(order.getTotalAmount())
                .build();

    }
}
