package org.example.controller;

import org.example.model.ProductType;
import org.example.repos.CompanyRepository;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    CompanyRepository companyRepo;
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public String initPage(Model model){
        model.addAttribute("companies",companyRepo.findAll());
        model.addAttribute("catCategories",categoryService.findByType(ProductType.CAT));
        model.addAttribute("dogCategories",categoryService.findByType(ProductType.DOG));
        model.addAttribute("parrotCategories",categoryService.findByType(ProductType.PARROT));
        model.addAttribute("rodentCategories",categoryService.findByType(ProductType.RODENT));
        return "main";
    }
}
