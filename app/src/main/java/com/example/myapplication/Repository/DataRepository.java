package com.example.myapplication.Repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.Database.DatabaseProduct;
import com.example.myapplication.Model.Category;
import com.example.myapplication.Model.Product;
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
    private DatabaseProduct databaseProduct;
    private final Context context;
    public DataRepository(Application application) {
        this.context = application.getApplicationContext();
        this.databaseProduct = new DatabaseProduct(this.context);
        this.sharedPreferences = context.getSharedPreferences("version_data", Context.MODE_PRIVATE);
    }
    private void getDataFromServer(IResponse iResponseCallBack){
        ICategoryService getData = RetrofitClientInstance.getInstance().create(ICategoryService.class);
        Call<ResponseCategory>  initGetData = getData.getAllCategory();
        initGetData.enqueue(new Callback<ResponseCategory>() {
            @Override
            public void onResponse(Call<ResponseCategory> call, Response<ResponseCategory> response) {
                if(response.isSuccessful()){
                 iResponseCallBack.onResponseGetData(response.body());
                    Log.d("APPDATA", "Lấy dữ liệu oke nhé");
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
    private String getVersionLocal(){
        this.editor = this.sharedPreferences.edit();
        return sharedPreferences.getString("version", "1.0.0");
    }
    /**
     * Handle data
     * Nếu version server khác version local thì lấy dữ liệu từ server về và lưu vào SQLite.
     * Nếu version server giống version local thì lấy dữ liệu từ SQLite.
     */
    public void handleData(IListCategory iListCategory){
        ICheckVersion checkVersion = RetrofitClientInstance.getInstance().create(ICheckVersion.class);
        Call<ResponseVersion> initCheckVersion = checkVersion.getVersion();
        initCheckVersion.enqueue(new Callback<ResponseVersion>() {
            @Override
            public void onResponse(Call<ResponseVersion> call, Response<ResponseVersion> response) {
                if(response.isSuccessful()){
                    String oldVersion = getVersionLocal();
                    String newVersion = response.body().getVersion();
                    if(newVersion.equals(oldVersion)){
                        Log.d("APPDATA", "Check version oke: " + response.body().getVersion());
                        iListCategory.getListCategory(databaseProduct.getAllCategory());
                        for (Product p : databaseProduct.getAllProduct()){
                            Log.d("APPDATA", p.getName() + " " + String.valueOf(p.getIdCategory()));
                        }
                    }else {
                        Log.d("APPDATA", "Dữ liệu cũ" + oldVersion + " dữ liệu mới: " + newVersion);
                        databaseProduct.onUpgrade(databaseProduct.getWritableDatabase(), 1, 2);
                        setVersionLocal(newVersion);
                        getDataFromServer(new IResponse() {
                            @Override
                            public void onResponseGetData(ResponseCategory responseCategory) {
                                if (responseCategory.getCode() == 200) {
                                    Log.d("APPDATA", "Lấy dữ liệu từ server oke");
                                    for (int i = 0; i < responseCategory.getData().size(); i++) {
                                        Category category = responseCategory.getData().get(i);
                                        databaseProduct.addCategory(new Category(category.get_id(), category.getName(), category.getImageUrl()));
                                        for(Product product : responseCategory.getData().get(i).getProducts()){
                                            databaseProduct.addProduct(new Product(product.get_id(), product.getName(), product.getPrice(), product.getQuantity(), product.getImageUrl(), product.getDescription(), product.getCategory(), i + 1));
                                        }
                                    }
                                    iListCategory.getListCategory(databaseProduct.getAllCategory());

                                    Toast.makeText(context, "lấy dữ liệu oke" , Toast.LENGTH_SHORT).show();
                                }else{
                                    Log.d("APPDATA", "Lấy dữ liệu từ server fail: " + responseCategory.getMessage());
                                    Toast.makeText(context, "Lỗi trong quá trình lấy dữ liệu" , Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Log.d("APPDATA", "Lấy dữ liệu từ server fail: " + t.getMessage());
                                Toast.makeText(context, "Lỗi trong quá trình lấy dữ liệu" , Toast.LENGTH_SHORT).show();
                            }
                        });
                    }


                }else{
                    Log.d("APPDATA", "Check version fail: "+ response.message());
                }
            }
            @Override
            public void onFailure(Call<ResponseVersion> call,Throwable t) {
                Log.d("APPDATA", "Check version fail: " + t.getMessage());
            }
        });

    }
    private void setVersionLocal( String version){
        this.editor = this.sharedPreferences.edit();
        this.editor.putString("version", version);
        this.editor.commit();
    }


    public interface IListCategory{
        void getListCategory(List<Category> listCategory);
    }
}


