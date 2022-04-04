package com.example.agventgroceryapp.Models;

public class RecommendedProductsModel {
    String img_url;
    String name;
    String price;
    String rating;

    public RecommendedProductsModel() {
    }

    public RecommendedProductsModel(String img_url, String name, String price, String rating) {
        this.img_url = img_url;
        this.name = name;
        this.price = price;
        this.rating = rating;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
