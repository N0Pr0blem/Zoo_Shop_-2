package org.example.service;

import org.example.exception.DBException;
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

    public ArrayList<Product> getCatProducts() {
        return (ArrayList<Product>) productRepo.findByType(ProductType.CAT);
    }

    public ArrayList<Product> getDogProducts() {
        return (ArrayList<Product>) productRepo.findByType(ProductType.DOG);
    }

    public ArrayList<Product> getRodentProducts() {
        return (ArrayList<Product>) productRepo.findByType(ProductType.RODENT);
    }

    public ArrayList<Product> getParrotProducts() {
        return (ArrayList<Product>) productRepo.findByType(ProductType.PARROT);
    }

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
}
