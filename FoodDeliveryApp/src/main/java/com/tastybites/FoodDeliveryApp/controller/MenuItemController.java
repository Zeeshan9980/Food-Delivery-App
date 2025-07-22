package com.tastybites.FoodDeliveryApp.controller;

import com.tastybites.FoodDeliveryApp.dto.MenuItemDTO;
import com.tastybites.FoodDeliveryApp.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping
    public MenuItemDTO createMenuItem(@RequestBody MenuItemDTO dto) {
        return menuItemService.createMenuItem(dto);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<MenuItemDTO> getItemsByRestaurant(@PathVariable Long restaurantId) {
        return menuItemService.getMenuItemsByRestaurant(restaurantId);
    }

    @PutMapping("/{id}")
    public MenuItemDTO update(@PathVariable Long id, @RequestBody MenuItemDTO dto) {
        return menuItemService.updateMenuItem(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
    }
}
