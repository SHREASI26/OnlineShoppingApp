package com.spring.shoppingapp.Shopping.App.service;

import com.spring.shoppingapp.Shopping.App.entity.OrderItem;
import com.spring.shoppingapp.Shopping.App.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderItemService {
    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void save(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }
}
