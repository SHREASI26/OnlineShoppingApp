package com.spring.shoppingapp.Shopping.App.service;

import com.spring.shoppingapp.Shopping.App.entity.Order;
import com.spring.shoppingapp.Shopping.App.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders(String email){
        return orderRepository.getOrders(email);
    }
    public void save(Order order){
        orderRepository.save(order);
    }
}
