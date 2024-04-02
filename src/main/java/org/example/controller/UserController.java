package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/add")
    public String addUserPage(){
        return"user_add";
    }
    @PostMapping("/add")
    public String addDefaultUser(){
        return"redirect:/";
    }

    @GetMapping("/profile")
    public String userProfilePage(Principal principal, Model model){
        User user = userService.getUser(principal.getName());
        model.addAttribute("user",user);
        return "user_profile";
    }
}
