package com.example.myapplication.NetworkApi.OrderDetail;
import  com.example.myapplication.Model.Product;
public class OrderDetailItem {
    private Product Product;
    private int Quantity;
    private int Price;

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

    public OrderDetailItem(com.example.myapplication.Model.Product product, int quantity, int price) {
        Product = product;
        Quantity = quantity;
        Price = price;
    }
}
