package org.example.controller;

import org.example.model.Category;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
@PreAuthorize("hasAuthority('USER')")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/{category}/delete")
    public String deleteCategory(Category category){
        categoryService.delete(category);
        return "redirect:/admin/category/all";
    }

    @GetMapping("/{category}/edit")
    public String getCategoryEditPage(@PathVariable Category category, Model model){
        model.addAttribute("category",category);
        return "admin_categories_edit";
    }

    @PostMapping("/{category}/edit")
    public String editCategory(@RequestParam("categoryId") Category category,
                               @RequestParam("name") String name,
                               @RequestParam("productTypes") List<String> selectedProductTypes
    ){
        categoryService.edit(category,name,selectedProductTypes);
        return"redirect:/admin/category/all";
    }
}
