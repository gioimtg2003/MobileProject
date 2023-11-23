package com.example.myapplication.NetworkApi.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IOrderService {
    @POST("/api/v1/create/order")
    Call<ResponseOrder> createOrder(@Body BodyOrder bodyOrder);
    @GET("/api/v1/order/:userid")
    Call<ResponseOrder> getOrderByUserId(@Path("userid") String userId);
}
