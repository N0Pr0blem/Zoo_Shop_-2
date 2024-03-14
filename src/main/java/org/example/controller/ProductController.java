package org.example.controller;

import org.example.model.Product;
import org.example.model.ProductType;
import org.example.repos.ProductRepository;
import org.example.service.CompanyService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CompanyService companyService;

    @GetMapping()
    public String allProduct(Model model){
        model.addAttribute("products",productService.getAll());
        return "product";
    }
    @GetMapping("/cat")
    public String catPage(Model model) {
        model.addAttribute("products", productService.getCatProducts());
        return "product";
    }

    @GetMapping("/dog")
    public String dogPage(Model model) {
        model.addAttribute("products", productService.getDogProducts());
        return "product";
    }

    @GetMapping("/rodent")
    public String rodentPage(Model model) {
        model.addAttribute("products", productService.getRodentProducts());
        return "product";
    }

    @GetMapping("/parrot")
    public String parrotPage(Model model) {
        model.addAttribute("products", productService.getParrotProducts());
        return "product";
    }

    @GetMapping("/add")
    public String addProductPage(Model model) {
        model.addAttribute("companies",companyService.getAll());
        return "product_add";
    }

    @PostMapping("/add")
    public String addProduct(Product product, Model model) {
        try{
            productService.addProduct(product);
            model.addAttribute("message","Success");

        }
        catch(Exception ex){
            model.addAttribute("message",ex.toString());
        }
        finally {
            return "product_add";
        }
    }
    @PostMapping("/{product}/delete")
    public String deleteProduct(@PathVariable Product product){
        productService.delete(product);
        return "redirect: /product";
    }
}
