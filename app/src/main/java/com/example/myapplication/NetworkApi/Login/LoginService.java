package com.example.myapplication.NetworkApi.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/api/v1/login")
    Call<LoginResponse> login(@Body LoginBody loginBody);
}
