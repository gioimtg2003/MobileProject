package com.example.myapplication.Repository;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.Database.DatabaseProduct;
import com.example.myapplication.Model.Product;

import java.util.List;

public class FavouriteRepository {
    private final Context context;
    private DatabaseProduct databaseProduct;
    public FavouriteRepository(Application application){
        this.context = application.getApplicationContext();
        this.databaseProduct = new DatabaseProduct(this.context);
    }
    public void getFavourite(IResponseFavourite iResponseFavourite){
        List<Product> productList = this.databaseProduct.getAllProduct(2);
        iResponseFavourite.onResponseFavourite(productList);
    }
    public interface IResponseFavourite{
        void onResponseFavourite(List<Product> productList);
    }
}
