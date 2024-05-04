package org.example.controller;

import org.example.model.Address;
import org.example.model.Cheque;
import org.example.model.User;
import org.example.service.ChequeService;
import org.example.service.ShopService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ChequeService chequeService;
    @Autowired
    ShopService shopService;



    @GetMapping("/profile")
    public String userProfilePage(Principal principal, Model model) {
        User user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("addresses", user.getAddresses());
        return "user_profile";
    }

    @GetMapping("/history")
    public String userHistoryPage(Principal principal, Model model) {
        User user = userService.getUser(principal.getName());
        ArrayList<Cheque> cheques = chequeService.getAllChequesUser(user);
        model.addAttribute("cheques", cheques);
        return "user_cheque";
    }

    @GetMapping("/demo")
    public String userDemoPage() {
        return "registration_demo";
    }

    @GetMapping("/cart")
    public String userCartPage(Principal principal, Model model) {
        model.addAttribute("cartProducts", userService.getUser(principal.getName()).getProducts());
        model.addAttribute("sum",userService.getUser(principal.getName()).getSum());
        return "user_cart";
    }

    @GetMapping("/address/add")
    public String addressAddPage(Model model) {
        return "address_add";
    }

    @PostMapping("/address/add")
    public String addressAdd(
            String city,
            String street,
            String corps,
            String houseNumber,
            String flatNumber,
            Principal principal,
            Model model
    ) {
        userService.addAddress(principal, city, street, corps, houseNumber, flatNumber);
        return "redirect:/user/profile";
    }

    @PostMapping("/address/{address}/delete")
    public String deleteAddress(@PathVariable Address address, Principal principal) {
        userService.deleteAddress(principal, address);
        return "redirect:/user/profile";
    }

    @GetMapping("/password/edit")
    public String passwordEditPage(Model model) {
        return "user_edit_password";
    }

    @PostMapping("/password/edit")
    public String passwordEdit(
            String oldPassword,
            String newPassword,
            String repeatNewPassword,
            Principal principal,
            Model model
    ) {
        userService.changePassword(principal, oldPassword, newPassword, repeatNewPassword);
        return "redirect:/user/profile";
    }

    @GetMapping("/buy")
    public String buyPage(Model model, Principal principal){
        model.addAttribute("sum",userService.getUser(principal.getName()).getSum());
        model.addAttribute("user",userService.getUser(principal.getName()));
        return "user_buy";
    }
    @PostMapping("/buy")
    public String buyPage(Principal principal){
        User user = userService.getUser(principal.getName());
        chequeService.saveOrder(user);
        userService.clearCart(user);
        return"redirect:/user/profile";
    }
}
