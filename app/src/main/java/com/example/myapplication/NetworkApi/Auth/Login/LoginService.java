package com.example.myapplication.NetworkApi.Auth.Login;

import com.example.myapplication.NetworkApi.Auth.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/api/v1/login")
    Call<AuthResponse> login(@Body LoginBody loginBody);
}
