package com.spring.shoppingapp.Shopping.App.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_image")
    private String categoryImage;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;
    public Category() {
    }

    public Category(String categoryName,String categoryImage) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    private void add(Product product){
        if(productList == null)
            productList = new ArrayList<>();
        productList.add(product);
        product.setCategory(this);
    }
}
