package org.example.service;

import org.example.model.Product;
import org.example.model.PurchasedProduct;
import org.example.model.User;
import org.example.repos.PurchasedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class ShopService {
    @Autowired
    UserService userService;
    @Autowired
    PurchasedProductRepository productRepository;
    public void addProductToCart(Principal principal, Product product) {
        User user = userService.getUser(principal.getName());
        PurchasedProduct purchasedProduct = new PurchasedProduct(product);
        productRepository.save(purchasedProduct);
        user.getProducts().add(purchasedProduct);
        userService.save(user);
    }

    public void delete(Principal principal, PurchasedProduct product) {
        User user = userService.getUser(principal.getName());
        user.getProducts().remove(product);
        productRepository.delete(product);
        userService.save(user);
    }

    public void decrement(PurchasedProduct product) {
        if(product.getQuantity()!=1) {
            product.setQuantity(product.getQuantity() - 1);
            productRepository.save(product);
        }
    }

    public void increment(PurchasedProduct product) {
        if(product.getQuantity()< product.getBorder()){
            product.setQuantity(product.getQuantity() + 1);
            productRepository.save(product);
        }
    }
}
