package com.example.myapplication.Repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.Database.DatabaseProduct;
import com.example.myapplication.Model.Category;
import com.example.myapplication.NetworkApi.CheckVersion.ICheckVersion;
import com.example.myapplication.NetworkApi.CheckVersion.ResponseVersion;
import com.example.myapplication.NetworkApi.Home.ICategoryService;
import com.example.myapplication.NetworkApi.Home.IResponse;
import com.example.myapplication.NetworkApi.Home.ResponseCategory;
import com.example.myapplication.NetworkApi.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository
{
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String versionLocal;
    private String versionServer;
    private DatabaseProduct databaseProduct;
    public DataRepository() {}
    public void getData(IResponse iResponseCallBack){
        ICategoryService getData = RetrofitClientInstance.getInstance().create(ICategoryService.class);
        Call<ResponseCategory>  initGetData = getData.getAllCategory();
        initGetData.enqueue(new Callback<ResponseCategory>() {
            @Override
            public void onResponse(Call<ResponseCategory> call, Response<ResponseCategory> response) {
                if(response.isSuccessful()){
                 iResponseCallBack.onResponseGetData(response.body());
                }else{
                    iResponseCallBack.onFailure(new Throwable(response.message()));
                }
            }
            @Override
            public void onFailure(Call<ResponseCategory> call, Throwable t) {
                iResponseCallBack.onFailure(t);
            }
        });
    }

    public void getProduct(){
        this.getData(new IResponse() {
            @Override
            public void onResponseGetData(ResponseCategory responseCategory) {
                if (responseCategory.getCode() == 200) {
                    Log.d("HOME", "Lấy dữ lieệu oke");
                }
            }
            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    public String getVersionLocal(Context context){
        sharedPreferences = context.getSharedPreferences("version", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        return sharedPreferences.getString("version", "1.0.0");
    }
    public String getVersion(){

        ICheckVersion checkVersion = RetrofitClientInstance.getInstance().create(ICheckVersion.class);
        Call<ResponseVersion> initCheckVersion = checkVersion.getVersion();
        initCheckVersion.enqueue(new Callback<ResponseVersion>() {
            @Override
            public void onResponse(@NonNull Call<ResponseVersion> call, @NonNull Response<ResponseVersion> response) {
                if(response.isSuccessful()){
                    versionServer =response.body() != null ? response.body().getVersion() : null;
                    Log.d("APP", "Check version oke: " + versionServer);

                }else{
                    Log.d("APP", "Check version fail: "+ response.message());

                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseVersion> call, @NonNull Throwable t) {
                Log.d("APP", "Check version fail: " + t.getMessage());
            }
        });
        return versionServer;
    }
    private void setVersionLocal(Context context, String version){
        sharedPreferences = context.getSharedPreferences("version", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("version", version);
        editor.commit();
    }
    private List<Category> getDataCategorySQLite(Context context){
        databaseProduct = new DatabaseProduct(context);
        return databaseProduct.getAllCategory();
    }
    public void checkVersion(Context context){
        String newVersion = getVersion();
        versionLocal = getVersionLocal(context);
        if(versionLocal.equals(newVersion)){
            Log.d("APP", "Version oke");
        }else{
            Log.d("APP", "Version fail");
            setVersionLocal(context, newVersion);
        }
    }
}
