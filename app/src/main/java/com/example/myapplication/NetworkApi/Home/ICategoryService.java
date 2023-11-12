package com.example.myapplication.NetworkApi.Home;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICategoryService {
    @GET("/api/v1/getAllCategory")
    Call<ResponseCategory> getAllCategory();
}
