package org.example.controller;

import org.example.model.Product;
import org.example.model.PurchasedProduct;
import org.example.model.User;
import org.example.service.ShopService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ShopController {
    @Autowired
    ShopService shopService;

    @PostMapping("/user/product/{product}/to_cart")
    public String productToCart(
            @PathVariable Product product,
            Principal principal
    ){
        shopService.addProductToCart(principal,product);
        return "redirect:/product";
    }
    @PostMapping("/user/cart/product/{product}/delete")
    public String productDelete(@PathVariable PurchasedProduct product, Principal principal){
        shopService.delete(principal,product);
        return "redirect:/user/cart";
    }
    @PostMapping("/user/product/{product}/decrement")
    public String decrementProduct(@PathVariable PurchasedProduct product){
        shopService.decrement(product);
        return"redirect:/user/cart";
    }
    @PostMapping("/user/product/{product}/increment")
    public String incrementProduct(@PathVariable PurchasedProduct product){
        shopService.increment(product);
        return"redirect:/user/cart";
    }

}
