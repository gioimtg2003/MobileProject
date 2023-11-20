package com.example.myapplication.Repository;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.Database.DatabaseProduct;
import com.example.myapplication.Model.Cart;

import java.util.List;

public class CartRepository {
    private Context context;
    private DatabaseProduct databaseProduct;
    public CartRepository(Application application) {
        this.context = application.getApplicationContext();
        this.databaseProduct = new DatabaseProduct(context);
    }
    public void increaseCart(int id){
        databaseProduct.handleCart(true, id);
    }
    public void descreaseCart(int id){
        databaseProduct.handleCart(false, id);
    }
    public void getAllCart(GetDataSqlite getDataSqlite){
        List<Cart> cartList = databaseProduct.getCart();
        getDataSqlite.getData(cartList);
    }
    public interface GetDataSqlite{
        void getData(List<Cart> cartList);
    }
}
