package com.example.myapplication.Model;

public class OrderDetailItem {
    private Product Product;
    private int Quantity;
    private int Price;

    public OrderDetailItem(com.example.myapplication.Model.Product product, int quantity, int price) {
        Product = product;
        Quantity = quantity;
        Price = price;
    }

    public com.example.myapplication.Model.Product getProduct() {
        return Product;
    }

    public void setProduct(com.example.myapplication.Model.Product product) {
        Product = product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
