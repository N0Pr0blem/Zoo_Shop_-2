package org.example.controller;

import org.example.model.Role;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String username,
                          @RequestParam String email,
                          @RequestParam String phoneNumber,
                          @RequestParam String password,
                          Model model) {
        userService.add(new User().
                setUsername(username).
                setEmail(email).
                setPhoneNumber(phoneNumber).
                setPassword(password));
        return "redirect:/login";
    }
}
