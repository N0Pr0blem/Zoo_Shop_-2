package org.example.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.model.Category;
import org.example.model.ProductType;
import org.example.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    Logger logger = LogManager.getLogger(CompanyService.class);

    public void add(String name, List<String> selectedProductTypes) {
        Category categoryFromDB = categoryRepository.findByName(name);
        if (categoryFromDB == null) {
            Category category = new Category(name);
            Set<ProductType> productTypes = selectedProductTypes
                    .stream()
                    .map(ProductType::valueOf)
                    .collect(Collectors.toSet());
            category.setProductTypes(productTypes);
            categoryRepository.save(category);
        }
    }

    public ArrayList<Category> getAll() {
        return (ArrayList<Category>) categoryRepository.findAll();
    }

    public List<Category> findByType(ProductType productType) {
        List<Category> allCategories = categoryRepository.findAll();
        List<Category> categoriesWithProductType = new ArrayList<>();
        for(Category category:allCategories){
            if(category.getProductTypes().contains(productType))
                categoriesWithProductType.add(category);
        }
        return categoriesWithProductType;
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public void edit(Category category, String name, List<String> selectedProductTypes) {
        category.setName(name);
        Set<ProductType> productTypes = selectedProductTypes
                .stream()
                .map(ProductType::valueOf)
                .collect(Collectors.toSet());
        category.setProductTypes(productTypes);
        category.regeneratePath();
        categoryRepository.save(category);
    }
}
