package com.spring.shoppingapp.Shopping.App.controller;

import com.spring.shoppingapp.Shopping.App.entity.*;
import com.spring.shoppingapp.Shopping.App.service.*;
import jakarta.validation.Valid;
import org.apache.juli.logging.Log;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private OrderService orderService;
    private CartController cartController;
    private ProductService productService;
    private CartService cartService;
    private CartItemService cartItemService;
    private List<CartItem> cartItemList = null;
    private double totalCost = 0;
    private String email = null;

    @Autowired
    public UserController(UserService userService, OrderService orderService,
                          CartController cartController,ProductService productService,
                          CartService cartService,CartItemService cartItemService) {
        this.orderService = orderService;
        this.userService = userService;
        this.cartController = cartController;
        this.productService = productService;
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    // USER - REGISTER
    @GetMapping("/register")
    public String userRegister(Model model){
        model.addAttribute("user",new User());
        return "user/user-register";
    }

    @PostMapping("/register")
    public String userRegister(@Valid @ModelAttribute("user")User user, BindingResult bind,Model model){
        if(bind.hasErrors())
            return "user/user-register";
        userService.addUser(user);
        model.addAttribute("msg","Registered successfully login now.");
        model.addAttribute("login",new Login());
        return "user/user-login";
    }

    // USER - LOGIN
    @GetMapping("/login")
    public String userLogin(Model model){
        model.addAttribute("login",new Login());
        return "user/user-login";
    }

    @PostMapping("/login")
    public String userLoginCheck(@Valid @ModelAttribute("login")Login login,BindingResult bind,Model model){
        if(bind.hasErrors())
            return "user/user-login";
        int loginStatus = userService.userLoginStatus(login.getEmail(),login.getPassword());
        if(loginStatus == 1){
            email = login.getEmail();
            cartController.setEmail(email);
            // model.addAttribute("email",email);
            return "redirect:/users/home";
        }
        else{
            model.addAttribute("login",new Login());
            model.addAttribute("msg","Incorrect username or password.");
            return "user/user-login";
        }
    }

    // USERS HOME
    @GetMapping("/home")
    public String userHome(Model model) {
        if (email == null){
            model.addAttribute("login",new Login());
            model.addAttribute("msg","Login / register first.");
            return "user/user-login";
        }
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products",products);
        model.addAttribute("email",email);
        return "user/user-home";
    }

    // USER PROFILE
    @GetMapping("/profile")
    public String userProfile(Model model){
        if (email == null){
            model.addAttribute("login",new Login());
            model.addAttribute("msg","Login / register first.");
            return "user/user-login";
        }
        User user = userService.userDetails(email);
        model.addAttribute("email",email);
        model.addAttribute("user",user);
        return "user/user-profile";
    }

    // USER PROFILE EDIT
    @GetMapping("/edit-profile")
    public String editProfile(Model model){
        if (email == null){
            model.addAttribute("login",new Login());
            model.addAttribute("msg","Login / register first.");
            return "user/user-login";
        }
        User user = userService.userDetails(email);
        model.addAttribute("email",email);
        model.addAttribute("user",user);

        return "user/user-profile-edit123";
    }

//    @PostMapping("/edit-profile")
//    public String editProfilePost(@Valid @ModelAttribute("user") User user, Model model, BindingResult result){
//        if(result.hasErrors()){
//            model.addAttribute("email",email);
//            model.addAttribute("user",user);
//            List<FieldError> fieldErrors = result.getFieldErrors();
//            List<String> errors = new ArrayList<>();
//            for (FieldError fieldError : fieldErrors) {
//                String fieldName = fieldError.getField();
//                String errorMessage = fieldError.getDefaultMessage();
//                errors.add(fieldName+" - "+errorMessage);
//            }
//            model.addAttribute("errors",errors);
//            return "user/user-profile-edit";
//        }
//        userService.updateUser(user);
//        return "redirect:/users/profile";
//    }
    @PostMapping("/edit-profile")
    public String editProfilePost(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:/users/profile";
    }

    @GetMapping("/orders")
    public String getAllOrders(Model model){
        if (email == null){
            model.addAttribute("login",new Login());
            model.addAttribute("msg","Login / register first.");
            return "user/user-login";
        }
        List<Order> orderList = orderService.getOrders(email);
        model.addAttribute("email",email);
        model.addAttribute("user",userService.userDetails(email));
        model.addAttribute("orders",orderList);
        return "user/user-order";
    }

    @GetMapping("/logout")
    public String logOut(Model model){
        email = null;
        cartController.setEmail(null);
        model.addAttribute("login",new Login());
        return "user/user-login";
    }

    @GetMapping("/address")
    public String manageAddress(Model model){
        if (email == null){
            model.addAttribute("login",new Login());
            model.addAttribute("msg","Login / register first.");
            return "user/user-login";
        }
        User user  = userService.userDetails(email);
        Address address = user.getAddress();
        model.addAttribute("email",email);
        model.addAttribute("user",user);
        model.addAttribute("address",address);
        return "user/user-address";
    }

    @GetMapping("/update-address")
    public String addAddress(Model model){
        if (email == null){
            model.addAttribute("login",new Login());
            model.addAttribute("msg","Login / register first.");
            return "user/user-login";
        }
        User user  = userService.userDetails(email);
        Address address = user.getAddress();
        if(address == null)
            address = new Address();
        model.addAttribute("email",email);
        model.addAttribute("user",user);
        model.addAttribute("address",address);
        return "user/user-update-address";
    }

    @PostMapping("/update-address")
    public String addAddress(@ModelAttribute("address")Address address,Model model){
        if (email == null){
            model.addAttribute("login",new Login());
            model.addAttribute("msg","Login / register first.");
            return "user/user-login";
        }
//        if(result.hasErrors()){
//            User user  = userService.userDetails(email);
//            model.addAttribute("email",email);
//            model.addAttribute("address",address);
//            model.addAttribute("user",user);
//            List<FieldError> fieldErrors = result.getFieldErrors();
//            List<String> errors = new ArrayList<>();
//            for (FieldError fieldError : fieldErrors) {
//                String fieldName = fieldError.getField();
//                String errorMessage = fieldError.getDefaultMessage();
//                errors.add(fieldName+" - "+errorMessage);
//            }
//            model.addAttribute("errors",errors);
//            return "user/user-edit-address";
//        }
        User user  = userService.userDetails(email);
        user.setAddress(address);
        userService.save(user);

        model.addAttribute("email",email);
        model.addAttribute("user",user);
        model.addAttribute("address",address);
        return "user/user-address";
    }

    @GetMapping("/cart")
    public String getCart(RedirectAttributes redirectAttributes,Model model){
        if(email == null){
            model.addAttribute("login",new Login());
            model.addAttribute("msg","Login / register first.");
            return "user/user-login";
        }
        redirectAttributes.addFlashAttribute("email",email);
        return "redirect:/cart/display";
    }


    @GetMapping("/cart/remove")
    public String removeCartItem(@RequestParam("id") int id,Model model){
        if(email == null){
            model.addAttribute("login",new Login());
            model.addAttribute("msg","Login / register first.");
            return "user/user-login";
        }
        userService.removeCartItem(id);
        return "redirect:/users/cart";
    }

    @GetMapping("/add-to-cart")
    public String addItemToCart(@RequestParam("prodId")int prodId,Model model){
        if(email == null){
            model.addAttribute("login",new Login());
            model.addAttribute("msg","Login / register first.");
            return "user/user-login";
        }
        // TODO
        userService.addItemToCart(email,prodId,1);
        return "redirect:/users/home";
    }

    @GetMapping("/buy-items")
    public String buyItems(@RequestParam(defaultValue = "false") boolean isCartItem,
                           @RequestParam(defaultValue = "-1") int prodId,
                           Model model){
        if(email == null){
            model.addAttribute("login",new Login());
            model.addAttribute("msg","Login / register first.");
            return "user/user-login";
        }
        List<CartItem> cartItems = new ArrayList<>();
        if(isCartItem){
            CartItem cartItem = new CartItem();
            Product prod = productService.getById(prodId);

            cartItem.setProduct(prod);
            cartItem.setQuantity(1);
            // model.addAttribute("cartItem",cartItem);
            cartItems.add(cartItem);
        }else{
            Cart cart = cartService.getCartDetails(email);
            cartItems = cart.getCartItemList();
        }
        double totalPrice = 0;
        for(CartItem cartItem : cartItems){
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        User user = userService.userDetails(email);
        if(user.getAddress() == null){
            return "redirect:/users/address";
        }
        model.addAttribute("email",email);
        model.addAttribute("cartItems",cartItems);
        model.addAttribute("user",user);
        model.addAttribute("totalPrice",totalPrice);
        cartItemList = cartItems;
        totalCost = totalPrice;
        return "/user/user-checkout";
    }

    @GetMapping("/check-out")
    public String checkOut(Model model){
        if(email == null){
            model.addAttribute("login",new Login());
            model.addAttribute("msg","Login / register first.");
            return "user/user-login";
        }
        User user = userService.userDetails(email);
        if(user.getWalletBalance() < totalCost){
            model.addAttribute("email",email);
            model.addAttribute("cartItems",cartItemList);
            model.addAttribute("user",user);
            model.addAttribute("totalPrice",totalCost);
            model.addAttribute("msg","Your account balance is low.");
            return "/user/user-checkout";
        }
        // update user wallet balance
        user.setWalletBalance(user.getWalletBalance() - totalCost);
        userService.save(user);
        // fetch user_id
        int userId = user.getUserId();
        // insert into orders (user_id , order_date,total_amount) single row will be created
        Order order = new Order(new Date(),totalCost);
        order.setUser(user);

        // fetch the order_id that has been just created
        // int orderId = order.getOrderId();
        // loop through cartItems and save each item in
        // order_item (order_id,product_id,quantity,item_price)
        for(CartItem item : cartItemList){
            OrderItem orderItem = new OrderItem(item.getQuantity(),item.getProduct().getPrice());
            orderItem.setProduct(item.getProduct());
            order.add(orderItem);
        }
        orderService.save(order);
        // after order , all cart items from cartItem table
        // for that particular cart id should be removed
        for(CartItem item : cartItemList){
            cartItemService.removeCartItem(item.getItemId());
            // userService.removeCartItem(item.getItemId());
        }
        return "redirect:/users/cart";
    }
}
