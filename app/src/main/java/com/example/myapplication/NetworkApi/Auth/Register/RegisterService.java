package com.example.myapplication.NetworkApi.Auth.Register;

import com.example.myapplication.NetworkApi.Auth.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {
    @POST("api/v1/register")
    Call<AuthResponse> register(@Body RegisterBody registerBody);
}
