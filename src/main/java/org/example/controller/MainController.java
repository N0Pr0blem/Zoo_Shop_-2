package org.example.controller;

import org.example.repos.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    CompanyRepository companyRepo;
    @GetMapping
    public String initPage(Model model){
        model.addAttribute("companies",companyRepo.findAll());
        return "main";
    }
}
