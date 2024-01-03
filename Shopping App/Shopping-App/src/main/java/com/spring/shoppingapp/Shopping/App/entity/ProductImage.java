package com.spring.shoppingapp.Shopping.App.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int imageId;

    @Column(name = "image_url")
    private String imageUrl;

    public ProductImage() {
    }

    public ProductImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
