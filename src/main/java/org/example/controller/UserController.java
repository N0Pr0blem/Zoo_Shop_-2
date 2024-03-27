package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/add")
    public String addUserPage(){
        return"user_add";
    }
    @PostMapping("/user/add")
    public String addDefaultUser(){
        return"redirect:/";
    }
}
