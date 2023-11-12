package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.myapplication.Model.Category.CategorySqlite;
import com.example.myapplication.Model.Product.ProductSqlite;

import java.util.ArrayList;
import java.util.List;

public class DatabaseProduct extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DatabaseProduct";

    public DatabaseProduct(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS product (id INTEGER PRIMARY KEY AUTOINCREMENT, _id TEXT, name TEXT, price INTEGER, quantity INTEGER, imageUrl TEXT, description TEXT, category TEXT)";
        db.execSQL(sql);
        String sql1 = "CREATE TABLE IF NOT EXISTS category (id INTEGER PRIMARY KEY AUTOINCREMENT, _id TEXT, name TEXT, imageUrl TEXT)";
        db.execSQL(sql1);
        String sql2 = "CREATE TABLE IF NOT EXISTS favouriteProduct (id INTEGER PRIMARY KEY AUTOINCREMENT, idProduct INTEGER)";
        db.execSQL(sql2);
        String sql3 = "CREATE TABLE IF NOT EXISTS cartProduct (id INTEGER PRIMARY KEY AUTOINCREMENT, idProduct INTEGER, quantity INTEGER)";
        db.execSQL(sql3);
    }
    public void AddFavouriteProduct(int idProduct){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idProduct", idProduct);
        db.insert("favouriteProduct", null, values);
        db.close();
    }
    public void DeleteFavouriteProduct(int idProduct){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("favouriteProduct", "idProduct = ?", new String[]{String.valueOf(idProduct)});
        db.close();
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
    public void AddCategory(CategorySqlite category){
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
    public void addProduct(ProductSqlite product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", product.get_Id());
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("quantity", product.getQuantity());
        values.put("imageUrl", product.getImageUrl());
        values.put("description", product.getDescription());
        values.put("category", product.getCategory());
        db.insert("product", null, values);
        db.close();
    }
    public List<ProductSqlite> getAllProduct(){
        List<ProductSqlite> productList = new ArrayList<ProductSqlite>();
        String sql = "SELECT * FROM product";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ProductSqlite product = new ProductSqlite(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return productList;
    }
    public List<CategorySqlite> getAllCategory(){
        List<CategorySqlite> categoryList = new ArrayList<CategorySqlite>();
        String sql = "SELECT * FROM category";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            CategorySqlite category = new CategorySqlite(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), null);
            categoryList.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return categoryList;
    }
    public Boolean checkDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM product";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0){
            return true;
        }
        return false;
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
