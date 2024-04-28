package org.example.controller;

import org.example.model.Address;
import org.example.model.Cheque;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ChequeService chequeService;

    @GetMapping("/add")
    public String addUserPage() {
        return "user_add";
    }

    @PostMapping("/add")
    public String addDefaultUser() {
        return "redirect:/";
    }

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
        List<Cheque> сheques = chequeService.getAllChequesUser(user);
        model.addAttribute("сheques", сheques);
        return "user_сheque";
    }

    @GetMapping("/demo")
    public String userDemoPage() {
        return "registration_demo";
    }

    @GetMapping("/cart")
    public String userCartPage(Principal principal, Model model) {
        model.addAttribute("cartProducts", userService.getUser(principal.getName()).getProducts());
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
        userService.deleteAddress(principal,address);
        return "redirect:/user/profile";
    }
}
