package com.tastybites.FoodDeliveryApp.service;

import com.tastybites.FoodDeliveryApp.dto.UserDTO;
import com.tastybites.FoodDeliveryApp.entity.User;

public interface UserService {
    User registerUser(UserDTO userDTO);
    User getUserById(Long id);
}
