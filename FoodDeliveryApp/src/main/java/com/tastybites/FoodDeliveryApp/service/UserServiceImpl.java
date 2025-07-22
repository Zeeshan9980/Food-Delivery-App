package com.tastybites.FoodDeliveryApp.service;

import com.tastybites.FoodDeliveryApp.dto.UserDTO;
import com.tastybites.FoodDeliveryApp.entity.User;
import com.tastybites.FoodDeliveryApp.exception.ResourceNotFoundException;
import com.tastybites.FoodDeliveryApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo;
    @Override
    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAddress(userDTO.getAddress());
        user.setContactNumber(userDTO.getContactNumber());
        return userRepo.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }
}
