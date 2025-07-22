package com.tastybites.FoodDeliveryApp.service;

import com.tastybites.FoodDeliveryApp.dto.MenuItemDTO;
import com.tastybites.FoodDeliveryApp.entity.MenuItem;
import com.tastybites.FoodDeliveryApp.entity.Restaurant;
import com.tastybites.FoodDeliveryApp.repository.MenuItemRepository;
import com.tastybites.FoodDeliveryApp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MenuItemServiceImpl implements MenuItemService{

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public MenuItemDTO createMenuItem(MenuItemDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        MenuItem item = MenuItem.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .available(dto.isAvailable())
                .restaurant(restaurant)
                .build();

        MenuItem saved = menuItemRepository.save(item);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<MenuItemDTO> getMenuItemsByRestaurant(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId).stream()
                .map(item -> MenuItemDTO.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .description(item.getDescription())
                        .price(item.getPrice())
                        .available(item.isAvailable())
                        .restaurantId(item.getRestaurant().getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public MenuItemDTO updateMenuItem(Long id, MenuItemDTO dto) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu Item not found"));

        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setAvailable(dto.isAvailable());

        menuItemRepository.save(item);

        dto.setId(id);
        return dto;
    }

    @Override
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}
