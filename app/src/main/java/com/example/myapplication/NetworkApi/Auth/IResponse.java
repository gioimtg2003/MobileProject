package com.example.myapplication.NetworkApi.Auth;

public interface IResponse {
    public void onResponseAuth(AuthResponse loginResponse);
    public void onFailure(Throwable t);
}
