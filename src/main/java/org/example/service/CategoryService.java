package org.example.service;

import org.example.model.Category;
import org.example.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public void add(Category category){
        Category categoryFromDB = categoryRepository.findByName(category.getName());
        if(categoryFromDB==null){
            categoryRepository.save(category);
        }
    }

    public ArrayList<Category> getAll() {
        return (ArrayList<Category>) categoryRepository.findAll();
    }
}
