package com.example.myapplication.NetworkApi.Order;

public class BodyOrderDetail {
    private String Product;
    private int Quantity;
    private int Price;

    public BodyOrderDetail(String product, int quantity, int price) {
        Product = product;
        Quantity = quantity;
        Price = price;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
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
