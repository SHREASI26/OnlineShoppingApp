package com.spring.shoppingapp.Shopping.App.repository;

import com.spring.shoppingapp.Shopping.App.entity.Cart;
import com.spring.shoppingapp.Shopping.App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query(nativeQuery = true,value = "CALL sp_user_cart(:mail)")
    public Cart getCartDetails(@Param("mail") String email);
}
