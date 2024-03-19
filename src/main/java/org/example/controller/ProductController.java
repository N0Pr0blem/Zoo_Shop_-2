package org.example.controller;

import io.minio.errors.*;
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

    @GetMapping("/get")
    public String getImage(Model model) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        model.addAttribute("img", minioService.getImage("adultcss.jpg"));
        return "test";
    }
}
