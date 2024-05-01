package org.example.service;

import org.example.exception.DBException;
import org.example.model.Category;
import org.example.model.Product;
import org.example.model.ProductType;
import org.example.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepo;

    public void addProduct(Product product) throws DBException {
        Product productFromDB = productRepo.findByName(product.getName());
        if(productFromDB==null){
            productRepo.save(product);
        }
        else{
            throw new DBException("Такой товар уже есть в базе...");
        }
    }

    public ArrayList<Product> getAll() {
        return (ArrayList<Product>) productRepo.findAll();
    }

    public void delete(Product product) {
        productRepo.delete(product);
    }

    public ArrayList<Product> getProductsByType(ProductType productType) {
        return (ArrayList<Product>) productRepo.findByType(productType);
    }

    public ArrayList<Product> getProductsByCategoryAndType(Category category, ProductType productType) {
        ArrayList<Product> productsByType =(ArrayList<Product>) productRepo.findByType(productType);
        ArrayList<Product> res = new ArrayList<>();
        for(Product product : productsByType){
            if(product.getCategory().equals(category)){
                res.add(product);
            }
        }
        return res;
    }
}
