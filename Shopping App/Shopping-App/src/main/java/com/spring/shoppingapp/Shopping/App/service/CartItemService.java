package com.spring.shoppingapp.Shopping.App.service;

import com.spring.shoppingapp.Shopping.App.repository.CartItemRepository;
import com.spring.shoppingapp.Shopping.App.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
    private CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void removeCartItem(int id){
        cartItemRepository.deleteById(id);
    }
}
