package org.example.controller;

import io.minio.messages.Item;
import org.example.model.Category;
import org.example.model.Company;
import org.example.model.Product;
import org.example.model.User;
import org.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('USER')")
public class AdminController {
    @Autowired
    ProductService productService;
    @Autowired
    CompanyService companyService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;
    @Autowired
    MinioService minioService;

    @GetMapping()
    public String getAdminPage(Model model) {
        model.addAttribute("minioUrl", minioService.getMinioUrl());
        return "adminka";
    }

    @GetMapping("/product/add")
    public String addProductPage(Model model) {
        model.addAttribute("companies", companyService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("minioUrl", minioService.getMinioUrl());
        return "admin_product_add";
    }
    @GetMapping("/product/add/{path}")
    public String addProductWithPage(@PathVariable String path, Model model) {
        model.addAttribute("companies", companyService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("minioUrl", minioService.getMinioUrl());
        model.addAttribute("path", path);
        return "admin_product_add";
    }

    @PostMapping("/product/add")
    public String addProduct(Product product, @RequestParam File image, @RequestParam String imageName, Model model) {
        try {
            if (imageName.isEmpty()) {
                product.setImage(image.getPath());
                minioService.uploadFileToMinIO(image);
            } else {
                product.setImage(imageName);
            }
            productService.addProduct(product);

        } catch (Exception ex) {
            model.addAttribute("message", ex.getMessage());
        } finally {
            return "redirect:/admin/product/add";
        }
    }

    @PostMapping("/product/{product}/delete")
    public String deleteProduct(@PathVariable Product product) {
        productService.delete(product);
        return "redirect:/admin/product/all";
    }

    @GetMapping("/category/add")
    public String addCategoryPage(Model model) {
        model.addAttribute("minioUrl", minioService.getMinioUrl());
        return "admin_category_add";
    }


    @PostMapping("/category/add")
    public String addCategory(@RequestParam String name, @RequestParam("productTypes") List<String> selectedProductTypes) {
        categoryService.add(name, selectedProductTypes);
        return "redirect:/admin/category/add";
    }

    @PostMapping("/company/add")
    public String addCompany(Company company, Model model) {
        String message = "";
        try {
            companyService.addCompany(company);
        } catch (Exception ex) {
        } finally {
            model.addAttribute("message", message);
        }
        return "redirect:/admin/company/add";
    }

    @GetMapping("/company/add")
    public String addCompanyPage(Model model) {
        model.addAttribute("minioUrl", minioService.getMinioUrl());
        return "admin_company_add";
    }

    @GetMapping("/product/all")
    public String allProductPage(Model model) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("minioUrl", minioService.getMinioUrl());
        return "admin_products_all";
    }

    @GetMapping("/category/all")
    public String allCategoryPage(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("minioUrl", minioService.getMinioUrl());
        return "admin_categories_all";
    }

    @GetMapping("/company/all")
    public String allCompanyPage(Model model) {
        model.addAttribute("companies", companyService.getAll());
        model.addAttribute("minioUrl", minioService.getMinioUrl());
        return "admin_companies_all";
    }

    @PostMapping("/company/{company}/delete")
    public String deleteCategory(@PathVariable Company company) {
        companyService.delete(company);
        return "redirect:/admin/company/all";
    }

    @GetMapping("/users")
    public String getAllUsersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin_all_users";
    }

    @PostMapping("/users/{user}/makeAdmin")
    public String makeAdmin(@PathVariable User user) {
        userService.makeAdmin(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{user}/makeUser")
    public String makeUser(@PathVariable User user) {
        userService.makeUser(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/images/all")
    public String allImagesPage(Model model){
        List<Item> imgs = minioService.getAllImages();
        model.addAttribute("imgs",imgs);
        return "all_images";
    }

    @GetMapping("/company/{company}/edit")
    public String editCompanyPage(@PathVariable Company company, Model model){
        model.addAttribute("company",company);
        return "admin_company_edit";
    }

    @PostMapping("/company/{company}/edit")
    public String editCompany(@PathVariable Company company,@RequestParam String name){
        companyService.edit(company,name);
        return"redirect:/admin/company/all";
    }
}
