package com.spring.shoppingapp.Shopping.App.controller;

import com.spring.shoppingapp.Shopping.App.entity.Cart;
import com.spring.shoppingapp.Shopping.App.entity.Login;
import com.spring.shoppingapp.Shopping.App.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    private String email = null;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/display")
    public String viewCart(Model model){
        if(email == null){
            model.addAttribute("login",new Login());
            model.addAttribute("msg","Login / register first.");
            return "user/user-login";
        }
        Cart cart = cartService.getCartDetails(email);
        model.addAttribute("cart",cart);
        model.addAttribute("email",email);
        model.addAttribute("user",cart.getUser());
        return "user/user-cart";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
