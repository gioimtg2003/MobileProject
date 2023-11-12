package com.example.myapplication.Model.Category;

import com.example.myapplication.Model.Product.Product;

import java.util.List;

public class CategorySqlite extends Category{
    public CategorySqlite(int id, String _id, String name, String imageUrl, List<Product> products) {
        super(_id, name, imageUrl, products);
        this.id = id;
    }
    public CategorySqlite(String _id, String name, String imageUrl) {
        super(_id, name, imageUrl);
    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
