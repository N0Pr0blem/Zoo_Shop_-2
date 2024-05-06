package org.example.controller;

import io.minio.errors.*;
import org.example.model.Category;
import org.example.model.Product;
import org.example.model.ProductType;
import org.example.service.CategoryService;
import org.example.service.CompanyService;
import org.example.service.MinioService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
    public String allProduct(Model model) {
        model.addAttribute("products", productService.getAll());
        return "product";
    }

    @GetMapping("/{type}")
    public String typeProductPage(@PathVariable String type, Model model) {
        ProductType productType = ProductType.fromString(type);
        model.addAttribute("categories", categoryService.findByType(productType));
        model.addAttribute("type", type);
        model.addAttribute("products", productService.getProductsByType(productType));
        return "product";
    }

    @GetMapping("/{type}/{categoryPath}")
    public String categoryProductPage(
            @PathVariable String type,
            @PathVariable String categoryPath,
            Model model
    ) {
        ProductType productType = ProductType.fromString(type);
        Category category = categoryService.findByPath(categoryPath);
        model.addAttribute("categories", categoryService.findByType(productType));
        model.addAttribute("type", type);
        model.addAttribute("products", productService.getProductsByCategoryAndType(category,productType));
        return "product";
    }
    @GetMapping("/{product}/info")
    public String productInfoPage(@PathVariable Product product, Model model){
        model.addAttribute("product",product);
        return "product_info";
    }
}
