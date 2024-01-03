package com.spring.shoppingapp.Shopping.App.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    @Column(name = "total_amount")
    private double totalAmount;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

    public Order() {
    }

    public Order(Date orderDate, double totalAmount) {
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public void add(OrderItem orderItem){
        if(orderItemList == null)
            orderItemList = new ArrayList<>();
        orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }
}
