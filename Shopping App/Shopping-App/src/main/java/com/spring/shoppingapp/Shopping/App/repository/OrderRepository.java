package com.spring.shoppingapp.Shopping.App.repository;

import com.spring.shoppingapp.Shopping.App.entity.Order;
import com.spring.shoppingapp.Shopping.App.entity.OrderItem;
import com.spring.shoppingapp.Shopping.App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query(value = "CALL sp_user_order(:mail);" , nativeQuery = true)
    public List<Order> getOrders(@Param("mail") String email);
}

