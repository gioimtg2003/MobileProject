package com.example.myapplication.Model;

public class Product {
    private int id;
    private String _id;
    private String name;
    private int price;
    private int quantity;
    private String description;
    private String imageUrl;
    private String category;
    private int idCategory;
    private int favourite;
    public Product (int id, String _id, String name, int price, String description, String imageUrl, int quantity){
        this.id = id;
        this._id = _id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }
    public Product(int id, String _id, String name, int price, String imageUrl){
        this.id = id;
        this._id = _id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    public Product(int id, String _id, String name, int price, int quantity, String description, String imageUrl, String category, int idCategory, int favourite) {
        this.id = id;
        this._id = _id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.idCategory = idCategory;
        this.favourite = favourite;
    }

    public Product(String _id, String name, int price, int quantity, String description, String imageUrl, String category, int idCategory) {
        this._id = _id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.idCategory = idCategory;
    }

    public Product(String _id, String name, int price, int quantity, String description, String imageUrl, String category) {
        this._id = _id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public Product(int id, String _id, String name, int price, int quantity, String description, String imageUrl, String category) {
        this.id = id;
        this._id = _id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }
}
