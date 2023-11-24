package com.example.myapplication.Repository;

import com.example.myapplication.Model.OrderDetail;
import com.example.myapplication.NetworkApi.OrderDetail.IOrderDetailService;
import com.example.myapplication.NetworkApi.OrderDetail.ResponseOrderDetail;
import com.example.myapplication.NetworkApi.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailRepository {
    public void getOrderDetail(String userId, IGetDataOrderDetail iGetDataOrderDetail) {
        IOrderDetailService iOrderDetailService = RetrofitClientInstance.getInstance().create(IOrderDetailService.class);
        Call<ResponseOrderDetail> call = iOrderDetailService.getOrderDetail(userId);
        call.enqueue(new Callback<ResponseOrderDetail>() {
            @Override
            public void onResponse(Call<ResponseOrderDetail> call, Response<ResponseOrderDetail> response) {
                if (response.isSuccessful()) {
                    ResponseOrderDetail responseOrderDetail = response.body();
                    if (responseOrderDetail != null && responseOrderDetail.getCode() == 200) {
                        iGetDataOrderDetail.onSuccess(responseOrderDetail.getData());
                    }else {
                        iGetDataOrderDetail.onFailure(new Throwable(responseOrderDetail.getMessage()));
                    }
                } else {
                    iGetDataOrderDetail.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<ResponseOrderDetail> call, Throwable t) {
                iGetDataOrderDetail.onFailure(t);
            }
        });

    }

    public interface IGetDataOrderDetail {
        void onSuccess(List<OrderDetail> orderDetail);
        void onFailure(Throwable t);
    }
}
