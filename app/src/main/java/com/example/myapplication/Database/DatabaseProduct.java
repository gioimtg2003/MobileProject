package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.Model.Cart;
import com.example.myapplication.Model.Category;
import com.example.myapplication.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseProduct extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DatabaseProduct";

    public DatabaseProduct(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Create table product, category, favouriteProduct, cartProduct.
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS product (id INTEGER PRIMARY KEY AUTOINCREMENT, _id TEXT, name TEXT, price INTEGER, quantity INTEGER, description TEXT, imageUrl TEXT, category TEXT, idCategory INTEGER, favourite INTEGER DEFAULT 0)";
        db.execSQL(sql);
        String sql1 = "CREATE TABLE IF NOT EXISTS category (id INTEGER PRIMARY KEY AUTOINCREMENT, _id TEXT, name TEXT, imageUrl TEXT)";
        db.execSQL(sql1);
        String sql3 = "CREATE TABLE IF NOT EXISTS cartProduct (id INTEGER PRIMARY KEY AUTOINCREMENT, idProduct INTEGER, quantity INTEGER)";
        db.execSQL(sql3);
    }
    public void AddFavouriteProduct(int idProduct){
        String  sql = "UPDATE product SET favourite = 1 WHERE id = " + idProduct;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);


        Log.d("APPDATA", "Add favourite product: " + String.valueOf(idProduct));
    }
    public void DeleteFavouriteProduct(int idProduct){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("favourite", 0);
        db.update("product", values, "id = ?", new String[]{String.valueOf(idProduct)});
    }
    public List<Integer> getAllFavouriteProduct(){
        List<Integer> productList = new ArrayList<Integer>();
        String sql = "SELECT * FROM favouriteProduct";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            productList.add(cursor.getInt(1));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return productList;
    }

    /**
     * Add category to database.
     * @param category
     */
    public void addCategory(Category category){
        String sql = "INSERT INTO category (name, image) VALUES ('Đồ ăn', 'https://cdn.icon-icons.com/icons2/2699/PNG/512/food_plate_dish_icon_168935.png')";
        SQLiteDatabase db = getWritableDatabase();
        // db.execSQL(sql);
        ContentValues values = new ContentValues();
        values.put("_id", category.get_id());
        values.put("name", category.getName());
        values.put("imageUrl", category.getImageUrl());
        db.insert("category", null, values);
        db.close();
    }

    /**
     * Add product to database.
     * @param product
     */
    public void addProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", product.get_id());
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("quantity", product.getQuantity());
        values.put("description", product.getDescription());
        values.put("imageUrl", product.getImageUrl());
        values.put("category", product.getCategory());
        values.put("idCategory", product.getIdCategory());
        db.insert("product", null, values);
        db.close();
    }

    /**
     * Get all product from database.
     *
     * @return
     */
    public List<Product> getAllProduct() {
        return getAllProduct(1);
    }

    /**
     * Get all product from database.
     * @return
     */
    public List<Product> getAllProduct(int type){
        List<Product> productList = new ArrayList<Product>();
        String sql;
        switch (type){
            case 1:
                sql = "SELECT * FROM product";
                break;
            case 2:
                sql = "SELECT * FROM product WHERE favourite = 1";
                break;
            default:
                sql = "SELECT * FROM product";
                break;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Product product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8), cursor.getInt(9));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return productList;
    }

    public List<Category> getAllCategory(){
        List<Category> categoryList = new ArrayList<Category>();
        String sql = "SELECT * FROM category";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Category category = new Category(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            categoryList.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return categoryList;
    }

    /**
     * Get product by category.
     * @param idCategory
     * @return
     */
    public List<Product> getProductByCategory(int idCategory){
        Log.d("APPDATA", "idCategory: " + String.valueOf(idCategory));
        List<Product> productList = new ArrayList<Product>();
        String sql = "SELECT * FROM product WHERE idCategory = " + idCategory;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Product product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8), cursor.getInt(9));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return productList;
    }

    /**
     * Thêm sản phẩm mới vào giỏ hàng
     * @param id
     */
    public void addCart(int id){
        if (checkCart(id)){
            String sql = "UPDATE cartProduct SET quantity = quantity + 1 WHERE idProduct = " + id;
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            db.close();
        }else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("idProduct", id);
            values.put("quantity", 1);
            db.insert("cartProduct", null, values);
            db.close();
        }
    }
    public void handleCart(Boolean type, int id){
        String sql = type ? "UPDATE cartProduct SET quantity = quantity + 1 WHERE idProduct = " + id : "UPDATE cartProduct SET quantity = quantity - 1 WHERE idProduct = " + id;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }
    public List<Cart> getCart(){
        int count = 0;
        List<Cart> cartList = new ArrayList<Cart>();
        String sql_ = "SELECT cartProduct.id AS id, product._id AS _id, product.name AS name, product.price AS price, product.imageUrl, cartProduct.quantity AS quantity FROM cartProduct INNER JOIN product ON product.id = cartProduct.idProduct";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql_, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Cart cart = new Cart(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5), false);
            cartList.add(cart);
            cursor.moveToNext();
            count++;
        }
        cursor.close();
        db.close();
        return cartList;
    }
    public Product getProductInCart(int id){
        String sql = "SELECT product.id AS id, product._id AS _id, product.name AS name, product.price AS price, product.description AS description, product.imageUrl AS imageUrl, cartProduct.quantity AS quantity FROM cartProduct INNER JOIN product ON product.id = cartProduct.idProduct WHERE cartProduct.id = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        Product product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6));
        cursor.close();
        db.close();
        return product;
    }
    private Boolean checkCart(int id){
        String sql = "SELECT * FROM cartProduct WHERE idProduct = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            return true;
        }
        return false;
    }
    public void deleteCart(int id){
        String sql = "DELETE FROM cartProduct WHERE id = " + id;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
        Log.d("DEBUGCART", "Delete cart product id : " + String.valueOf(id));
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS product";
        db.execSQL(sql);
        String sql1 = "DROP TABLE IF EXISTS category";
        db.execSQL(sql1);
        onCreate(db);
    }
}
