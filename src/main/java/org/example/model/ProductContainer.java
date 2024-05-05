package org.example.model;

import lombok.Getter;

@Getter
public class   ProductContainer {
    private String name;
    private String price;
    private String discount;
    private String type;
    private String company;
    private String category;
    private String description;
    private String count;
    private String image;

    public ProductContainer(String name, String price, String discount, String type, String company, String category, String description, String count) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.type = type;
        this.company = company;
        this.category = category;
        this.description = description;
        this.count = count;
    }

    public ProductContainer setImage(String image) {
        this.image = image;
        return this;
    }
}
