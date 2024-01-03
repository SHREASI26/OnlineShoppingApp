package com.spring.shoppingapp.Shopping.App.repository;

import com.spring.shoppingapp.Shopping.App.entity.ProductImage;
import com.spring.shoppingapp.Shopping.App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {
}
