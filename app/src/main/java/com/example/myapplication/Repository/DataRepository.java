package com.example.myapplication.Repository;

import android.util.Log;

import com.example.myapplication.NetworkApi.Home.ICategoryService;
import com.example.myapplication.NetworkApi.Home.IResponse;
import com.example.myapplication.NetworkApi.Home.ResponseCategory;
import com.example.myapplication.NetworkApi.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository
{
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
}
