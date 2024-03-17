package org.example.controller;

import org.example.model.Category;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add")
    public String addCategoryPage(){
        return "category_add";
    }

    @PostMapping("/add")
    public String addCategory(Category category){
        categoryService.add(category);
        return "redirect:/";
    }

}
