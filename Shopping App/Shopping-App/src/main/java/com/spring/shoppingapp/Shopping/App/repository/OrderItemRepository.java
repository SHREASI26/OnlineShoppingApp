package com.spring.shoppingapp.Shopping.App.repository;

import com.spring.shoppingapp.Shopping.App.entity.OrderItem;
import com.spring.shoppingapp.Shopping.App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
