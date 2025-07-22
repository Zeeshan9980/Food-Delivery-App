package com.tastybites.FoodDeliveryApp.service;

import com.tastybites.FoodDeliveryApp.dto.MenuItemDTO;

import java.util.List;

public interface MenuItemService {
    MenuItemDTO createMenuItem(MenuItemDTO menuItemDTO);
    List<MenuItemDTO> getMenuItemsByRestaurant(Long restaurantId);
    MenuItemDTO updateMenuItem(Long id, MenuItemDTO menuItemDTO);
    void deleteMenuItem(Long id);
}
