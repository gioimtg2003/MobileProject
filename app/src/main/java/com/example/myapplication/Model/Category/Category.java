package com.example.myapplication.Model.Category;

import com.example.myapplication.Model.Product.Product;

import java.util.List;

public class Category {
    private String _id;
    private String name;
    private String imageUrl;
    private List<Product> products;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Category(String _id, String name, String imageUrl, List<Product> products) {
        this._id = _id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.products = products;
    }
    public Category(String _id, String name, String imageUrl) {
        this._id = _id;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
