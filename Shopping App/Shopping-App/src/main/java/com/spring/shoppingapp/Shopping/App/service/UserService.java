package com.spring.shoppingapp.Shopping.App.service;

import com.spring.shoppingapp.Shopping.App.entity.User;
import com.spring.shoppingapp.Shopping.App.exception.UserNotFoundException;
import com.spring.shoppingapp.Shopping.App.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int userLoginStatus(String email, String password){
        return userRepository.userLoginStatus(email,password);
    }

    public void addUser(User user){
        userRepository.userCreate(user.getFullName(),user.getEmail(),user.getPassword(),
                                    user.getPhoneNumber(),user.getDOB(),user.getWalletBalance());
    }

    public List<User> allUser(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User userDetails(String email){
        return userRepository.findByEmail(email);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.userUpdate(user.getFullName(),user.getPassword(),
                                  user.getPhoneNumber(),user.getDOB(),
                                  user.getWalletBalance(),user.getUserId());
    }

    public void removeCartItem(int id){
        userRepository.userCartItemRemove(id);
    }

    public void addItemToCart(String email,int productId,int quantity){
        userRepository.userAddItemToCart(email,productId,quantity);
    }

}
