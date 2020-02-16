package com.example.courierapp;

public class Product {
    private String pname;
    private String image_url;

    public Product(String pname, String image_url) {
        this.pname = pname;
        this.image_url = image_url;
    }

    public String getName() {
        return pname;
    }

    public String getImage_url() {
        return image_url;
    }
}