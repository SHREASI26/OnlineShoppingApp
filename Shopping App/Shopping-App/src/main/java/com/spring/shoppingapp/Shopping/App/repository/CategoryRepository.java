package com.spring.shoppingapp.Shopping.App.repository;

import com.spring.shoppingapp.Shopping.App.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
