package com.example.agventgroceryapp.Models;

public class CategoryModel {
    String img_url;
    String name;
    String description;
    String discount;
    String price;

    public CategoryModel() {
    }

    public CategoryModel(String img_url, String name, String description, String discount, String price) {
        this.img_url = img_url;
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
