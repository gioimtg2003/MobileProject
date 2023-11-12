package com.example.myapplication.Model.Product;

import androidx.annotation.NonNull;

public class ProductSqlite extends Product{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductSqlite(int id, String _id, String name, int price, int quantity, String description, String imageUrl, String category) {
        super(_id, name, price, quantity, description, imageUrl, category);
        this.id = id;
    }
    public ProductSqlite(String _id, String name, int price, int quantity, String description, String imageUrl, String category) {
        super(_id, name, price, quantity, description, imageUrl, category);

    }

    @NonNull
    @Override
    public String toString() {
        return "id: " + String.valueOf(this.getId()) + " _id: " + this.get_Id() + " name: " + this.getName() + " price: " + String.valueOf(this.getPrice()) + " quantity: " + String.valueOf(this.getQuantity()) + " description: " + this.getDescription() + " imageUrl: " + this.getImageUrl() + " category: " + this.getCategory();
    }
}
