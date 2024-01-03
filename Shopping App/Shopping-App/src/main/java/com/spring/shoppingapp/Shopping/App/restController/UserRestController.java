package com.spring.shoppingapp.Shopping.App.restController;

import com.spring.shoppingapp.Shopping.App.entity.User;
import com.spring.shoppingapp.Shopping.App.exception.UserAgeNotValidException;
import com.spring.shoppingapp.Shopping.App.exception.UserNotFoundException;
import com.spring.shoppingapp.Shopping.App.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/{email}")
    public User getUser(@PathVariable("email") String email){
        return userService.userDetails(email);
    }

    @GetMapping("/login-status/{email}/{pass}")
    public int userLoginStatus(@PathVariable String email,@PathVariable String pass){
        return userService.userLoginStatus(email,pass);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> allUser(){
        List<User> users = userService.allUser();
        if(users.size() < 1)
            throw new UserNotFoundException("No user found");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user){
        int age = user.getDOB().getYear() - LocalDate.now().getYear();
        if(age < 18)
            throw new UserAgeNotValidException("Age must be greater than 17.");
        userService.addUser(user);
        return new ResponseEntity<>("User created",HttpStatus.OK);
    }


}
