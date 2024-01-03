package com.spring.shoppingapp.Shopping.App.repository;

import com.spring.shoppingapp.Shopping.App.entity.CartItem;
import com.spring.shoppingapp.Shopping.App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
}
