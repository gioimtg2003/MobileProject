package com.example.myapplication.Repository;

import androidx.annotation.NonNull;

import com.example.myapplication.NetworkApi.Auth.IResponse;
import com.example.myapplication.NetworkApi.Auth.Login.LoginBody;
import com.example.myapplication.NetworkApi.Auth.AuthResponse;
import com.example.myapplication.NetworkApi.Auth.Login.LoginService;
import com.example.myapplication.NetworkApi.Auth.Register.RegisterBody;
import com.example.myapplication.NetworkApi.Auth.Register.RegisterService;
import com.example.myapplication.NetworkApi.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    public AuthRepository() {
    }
    public void login(LoginBody loginBody, IResponse loginCallback){
        LoginService loginService = RetrofitClientInstance.getInstance().create(LoginService.class);
        Call<AuthResponse> initLogin = loginService.login(loginBody);

        initLogin.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                if (response.isSuccessful()){
                    loginCallback.onResponseAuth(response.body());

                }else {
                    loginCallback.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                loginCallback.onFailure(t);
            }
        });
    }
    public void Register(RegisterBody registerBody, IResponse registerCallback){
        RegisterService register = RetrofitClientInstance.getInstance().create(RegisterService.class);
        Call<AuthResponse> initRegister = register.register(registerBody);
        initRegister.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                if (response.isSuccessful()){
                     registerCallback.onResponseAuth(response.body());

                }else {
                    registerCallback.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                registerCallback.onFailure(t);
            }
        });
    }
    public interface LoginCallback{
        void onResponse(AuthResponse loginResponse);
        void onFailure(Throwable t);
    }

}
