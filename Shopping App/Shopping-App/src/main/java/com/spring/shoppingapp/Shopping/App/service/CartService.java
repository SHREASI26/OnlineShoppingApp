package com.spring.shoppingapp.Shopping.App.service;

import com.spring.shoppingapp.Shopping.App.entity.Cart;
import com.spring.shoppingapp.Shopping.App.entity.Product;
import com.spring.shoppingapp.Shopping.App.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getCartDetails(String email){
        return cartRepository.getCartDetails(email);
    }

}
