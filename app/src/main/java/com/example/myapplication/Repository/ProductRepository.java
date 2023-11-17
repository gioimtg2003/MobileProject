package com.example.myapplication.Repository;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.Database.DatabaseProduct;
import com.example.myapplication.Model.Product;

import java.util.List;

public class ProductRepository {
    private final Context context;
    private final DatabaseProduct databaseProduct;
    public ProductRepository(Application application) {
        this.context = application.getApplicationContext();
        this.databaseProduct = new DatabaseProduct(this.context);
    }
    public void fetchDataSQLite(int idCategory, IFetchDataCallBack iFetchDataCallBack){
       iFetchDataCallBack.onResponseGetData(databaseProduct.getProductByCategory(idCategory));
    }
    public void fetchDataProductFavourite(IFetchDataCallBack iFetchDataCallBack){
        iFetchDataCallBack.onResponseGetData(databaseProduct.getAllProduct(2));
    }
    public void addFavouriteProduct(int idProduct){
        databaseProduct.AddFavouriteProduct(idProduct);
    }
    public void deleteFavouriteProduct(int idProduct){
        databaseProduct.DeleteFavouriteProduct(idProduct);
    }
    public interface IFetchDataCallBack{
        void onResponseGetData(List<Product> productList);
    }
}
