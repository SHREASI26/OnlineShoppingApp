package com.spring.shoppingapp.Shopping.App.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "full_name")
    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[A-Za-z\s]+$",message = "Name should contain only character.")
    private String fullName;

    @Column(name = "email")
//    @NotBlank(message = "Email is mandatory")
    @Email(message = "email should be in proper format")
    private String email;

    @Column(name = "password")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Minimum length of 8 characters ,at least 1 uppercase letter , 1 lowercase letter , 1 digit ")
    private String password;

    @Column(name = "phone_number")
    @Pattern(regexp = "^\\d{10}$",
            message = "Phone number should have 10 digits and contains only number")
    private String phoneNumber;

    @Column(name = "dob")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Birth date must be in past.")
    private Date DOB;

    @Column(name = "wallet_balance")
    @Positive(message = "Balance must be positive.")
    private double walletBalance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;


    @OneToMany(mappedBy = "user")
    private List<Order> orderList;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    public User() {
    }

    public User(String fullName, String email, String password, String phoneNumber, Date DOB, double walletBalance) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.DOB = DOB;
        this.walletBalance = walletBalance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    private void add(Order order){
        if(orderList == null)
            orderList = new ArrayList<>();
        orderList.add(order);
        order.setUser(this);
    }
}
