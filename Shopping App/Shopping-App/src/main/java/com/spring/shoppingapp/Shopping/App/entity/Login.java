package com.spring.shoppingapp.Shopping.App.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class Login {
    @NotBlank(message = "email is mandatory.")
    @Email(message = "email is not in proper format.")
    private String email;

    @NotBlank(message = "Password is mandatory.")
    private String password;

    public Login() {
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
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
}
