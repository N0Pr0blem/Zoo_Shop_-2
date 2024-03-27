package org.example.controller;

import org.example.model.Category;
import org.example.model.Company;
import org.example.model.Product;
import org.example.service.CategoryService;
import org.example.service.CompanyService;
import org.example.service.MinioService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ProductService productService;
    @Autowired
    CompanyService companyService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    MinioService minioService;

    @Value("${minio.url}")
    private String minioUrl;

    @GetMapping()
    public String getAdminPage(Model model){
        model.addAttribute("minioUrl",minioUrl);
        return "adminka";
    }

    @GetMapping("/product/add")
    public String addProductPage(Model model) {
        model.addAttribute("companies", companyService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        return "admin_product_add";
    }

    @PostMapping("/product/add")
    public String addProduct(Product product, @RequestParam File image, Model model) {
        try {
            product.setImage(image.getPath());
            minioService.uploadFileToMinIO(image);
            productService.addProduct(product);
            model.addAttribute("message", "Success");

        } catch (Exception ex) {
            model.addAttribute("message", ex.toString());
        } finally {
            return "redirect:/admin/product/add";
        }
    }

    @PostMapping("/product/{product}/delete")
    public String deleteProduct(@PathVariable Product product) {
        productService.delete(product);
        return "redirect:/product";
    }

    @GetMapping("/category/add")
    public String addCategoryPage(){
        return "admin_category_add";
    }

    @PostMapping("/category/add")
    public String addCategory(Category category){
        categoryService.add(category);
        return "redirect:/admin/category/add";
    }
    @PostMapping("/company/add")
    public String addCompany(Company company, Model model){
        String message="";
        try{
            companyService.addCompany(company);
        }
        catch(Exception ex){
        }
        finally{
            model.addAttribute("message",message);
        }
        return "redirect:/admin/company/add";
    }
    @GetMapping("/company/add")
    public String addCompanyPage(){
        return "admin_company_add";
    }
    @GetMapping("/product/all")
    public String allProductPage(Model model){
        model.addAttribute("products",productService.getAll());
        return "admin_products_all";
    }
    @GetMapping("/category/all")
    public String allCategoryPage(Model model){
        model.addAttribute("categories",categoryService.getAll());
        return "admin_categories_all";
    }
    @GetMapping("/company/all")
    public String allCompanyPage(Model model){
        model.addAttribute("companies",companyService.getAll());
        return "admin_companies_all";
    }
}
