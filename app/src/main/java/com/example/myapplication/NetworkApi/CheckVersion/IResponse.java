package com.example.myapplication.NetworkApi.CheckVersion;

import com.example.myapplication.NetworkApi.Home.ResponseCategory;

public interface IResponse {
    public void onResponseCheckVersion(ResponseVersion responseVersion);
    public void onFailure(Throwable t);
}
