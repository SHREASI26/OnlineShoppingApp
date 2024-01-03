package com.spring.shoppingapp.Shopping.App.service;

import com.spring.shoppingapp.Shopping.App.entity.Product;
import com.spring.shoppingapp.Shopping.App.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product getById(int id){
        return productRepository.findById(id).get();
    }
}
