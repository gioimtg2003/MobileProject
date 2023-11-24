package com.example.myapplication.NetworkApi.OrderDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IOrderDetailService {
    @GET("/user/order/:userId")
    Call<ResponseOrderDetail> getOrderDetail(@Path("userId") String userId);
}
