package com.spring.shoppingapp.Shopping.App.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;

    @Column(name = "house_number")
    @NotBlank(message = "House Number is mandatory")
    private String houseNumber;

    @Column(name = "street_name")
    @NotBlank(message = "Street Name is mandatory")
    private String streetName;

    @Column(name = "city")
    @NotBlank(message = "City is mandatory")
    @Pattern(regexp = "^[A-Za-z\s]+$",message = "City should contain only character.")
    private String city;

    @Column(name = "state")
    @NotBlank(message = "State is mandatory")
    @Pattern(regexp = "^[A-Za-z\s]+$",message = "State should contain only character.")
    private String state;

    @Column(name = "country")
    @NotBlank(message = "Country is mandatory")
    @Pattern(regexp = "^[A-Za-z\s]+$",message = "Country should contain only character.")
    private String country;

    @Column(name = "pincode")
    @NotBlank(message = "Pincode is mandatory")
    @Pattern(regexp = "^\\d{6}$",
            message = " Pincode should have only 6 digits")
    private String pincode;

    public Address() {
    }

    public Address(String houseNumber, String streetName, String city, String state, String country, String pincode) {
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
