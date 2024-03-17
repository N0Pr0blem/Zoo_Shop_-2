package org.example.controller;

import org.example.model.Category;
import org.example.model.Product;
import org.example.model.ProductType;
import org.example.repos.ProductRepository;
import org.example.service.CategoryService;
import org.example.service.CompanyService;
import org.example.service.MinioService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CompanyService companyService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    MinioService minioService;

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
        model.addAttribute("categories",categoryService.getAll());
        return "product_add";
    }

    @PostMapping("/add")
    public String addProduct(Product product, @RequestParam File image, Model model) {
        try{
            product.setImage(image.getPath());
            minioService.uploadFileToMinIO(image);
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
