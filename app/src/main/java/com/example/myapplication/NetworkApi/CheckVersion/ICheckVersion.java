package com.example.myapplication.NetworkApi.CheckVersion;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICheckVersion {
    @GET("api/v1/version")
    Call<ResponseVersion> getVersion();
}
