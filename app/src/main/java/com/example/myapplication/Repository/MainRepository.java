package com.example.myapplication.Repository;

import android.widget.Toast;

import com.example.myapplication.NetworkApi.Login.LoginBody;
import com.example.myapplication.NetworkApi.Login.LoginResponse;
import com.example.myapplication.NetworkApi.Login.LoginService;
import com.example.myapplication.NetworkApi.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    public MainRepository() {
    }
    public void login(LoginBody loginBody, LoginCallback loginCallback){
        LoginService loginService = RetrofitClientInstance.getInstance().create(LoginService.class);
        Call<LoginResponse> initLogin = loginService.login(loginBody);

        initLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    loginCallback.onResponse(response.body());

                }else {
                    loginCallback.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginCallback.onFailure(t);
            }
        });
    }
    public interface LoginCallback{
        void onResponse(LoginResponse loginResponse);
        void onFailure(Throwable t);
    }
}
