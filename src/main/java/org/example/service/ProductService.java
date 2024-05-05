package org.example.service;

import org.example.exception.DBException;
import org.example.model.*;
import org.example.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepo;

    public void addProduct(ProductContainer productContainer,Company company,Category category) throws DBException {
        Product productFromDB = productRepo.findByName(productContainer.getName());
        Product product = new Product();
        product.setName(productContainer.getName());
        product.setPrice(Double.parseDouble(productContainer.getPrice()));
        product.setDiscount(Double.parseDouble(productContainer.getDiscount()));
        product.setCompany(company);
        product.setCategory(category);
        product.setCount(Integer.parseInt(productContainer.getCount()));
        product.setDescription(productContainer.getDescription());
        product.setImage(productContainer.getImage());
        product.setType(ProductType.fromString(productContainer.getType()));

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

    public void edit(Product product, ProductContainer productContainer, Company company,Category category) {
        product.setName(productContainer.getName());
        product.setPrice(Double.parseDouble(productContainer.getPrice()));
        product.setDiscount(Double.parseDouble(productContainer.getDiscount()));
        product.setType(ProductType.fromString(productContainer.getType()));
        product.setCompany(company);
        product.setCategory(category);
        product.setDescription(productContainer.getDescription());
        product.setCount(Integer.parseInt(productContainer.getCount()));
        product.setImage(productContainer.getImage());
        productRepo.save(product);
    }
}
