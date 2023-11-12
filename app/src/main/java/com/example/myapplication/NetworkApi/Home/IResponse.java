package com.example.myapplication.NetworkApi.Home;

public interface IResponse {
    public void onResponseGetData(ResponseCategory responseCategory);
    public void onFailure(Throwable t);
}
