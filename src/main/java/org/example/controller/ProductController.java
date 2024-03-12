package org.example.controller;

import org.example.model.ProductType;
import org.example.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepo;

    @GetMapping("/cat")
    public String catPage(Model model){
        model.addAttribute("products",productRepo.findByType(ProductType.CAT));
        return "cat.html";
    }
    @GetMapping("/dog")
    public String dogPage(Model model){
        model.addAttribute("products",productRepo.findByType(ProductType.DOG));
        return "cat.html";
    }
    @GetMapping("/rodent")
    public String rodentPage(Model model){
        model.addAttribute("products",productRepo.findByType(ProductType.RODENT));
        return "cat.html";
    }
    @GetMapping("/parrot")
    public String parrotPage(Model model){
        model.addAttribute("products",productRepo.findByType(ProductType.PARROT));
        return "cat.html";
    }
}
