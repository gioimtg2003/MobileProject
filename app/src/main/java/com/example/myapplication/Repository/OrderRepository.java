package com.example.myapplication.Repository;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.Database.DatabaseProduct;
import com.example.myapplication.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private Context context;
    private DatabaseProduct databaseProduct;
    public OrderRepository(Application application) {
        this.context = application.getApplicationContext();
        this.databaseProduct = new DatabaseProduct(context);
    }
    public void getProductInCart(List<Integer> listIdChecked, IDataProduct iGetDataFromSqlite){
        List<Product> productInCart = new ArrayList<Product>();
        for (int i : listIdChecked) {
            Product product = databaseProduct.getProductInCart(i);
            productInCart.add(product);
        }
        iGetDataFromSqlite.ProductList(productInCart);
    }
    public interface IDataProduct{
        void ProductList(List<Product> listProduct);
    }
}
