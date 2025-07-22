package com.tastybites.FoodDeliveryApp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MenuItemDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean available;
    private Long restaurantId;
}
