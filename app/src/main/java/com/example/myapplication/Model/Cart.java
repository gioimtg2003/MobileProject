package com.example.myapplication.Model;

public class Cart {
    private int id;
    private int idProduct;
    private int Quantity;

    public Cart(int id, int idProduct, int quantity) {
        this.id = id;
        this.idProduct = idProduct;
        Quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
