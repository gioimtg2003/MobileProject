package com.example.myapplication.Repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.myapplication.Database.DatabaseProduct;

public class DetailRepository {
    private Context context;
    private DatabaseProduct databaseProduct;
    public DetailRepository(Application application){
        this.context = application.getApplicationContext();
        this.databaseProduct = new DatabaseProduct(context);
    }
    public void addCart(int idProduct){
        databaseProduct.addCart(idProduct);
        Log.d ("APPDATA", "Add cart product id : " + String.valueOf(idProduct));
    }
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
