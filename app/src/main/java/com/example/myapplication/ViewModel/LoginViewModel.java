package com.example.myapplication.ViewModel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.NetworkApi.Login.LoginBody;
import com.example.myapplication.NetworkApi.Login.LoginResponse;
import com.example.myapplication.Repository.MainRepository;
import com.example.myapplication.View.Activity_Login;

import java.io.Closeable;

public class LoginViewModel extends AndroidViewModel {
    private final String SHARED_PREFERENCES_NAME = "APP_STORAGE";
    private final MutableLiveData<Integer> mProgressBarData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mLoginResult = new MutableLiveData<>();

    public MutableLiveData<String> getmLoginMessage() {
        return mLoginMessage;
    }

    private final MutableLiveData<String> mLoginMessage = new MutableLiveData<>();
    MainRepository mainRepository;
    public MutableLiveData<Integer> getmProgressBarData() {
        return mProgressBarData;
    }

    public MutableLiveData<Boolean> getmLoginResult() {
        return mLoginResult;
    }

    public LoginViewModel(@NonNull Application application ){
        super(application);
        mainRepository = new MainRepository();
        mProgressBarData.postValue(View.GONE);
        mLoginResult.postValue(false);
        mLoginMessage.postValue("");
    }

    /**
     * Hàm dùng để login
     */
    public void login(String email, String password){
        mProgressBarData.postValue(View.VISIBLE);
        mainRepository.login(new LoginBody(email, password), new MainRepository.LoginCallback() {
            @Override
            public void onResponse(LoginResponse loginResponse) {
                if (loginResponse.getCode() == 200){
                    mProgressBarData.postValue(View.GONE);
                    mLoginResult.postValue(true);
                    mLoginMessage.postValue(loginResponse.getData().getName());
                    SharedPreferences sharedPreferences = getApplication().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("is_login", true);
                    editor.putString("id_user", loginResponse.getData().get_id());
                    editor.putString("name_user", loginResponse.getData().getName());
                    editor.putString("email_user", loginResponse.getData().getEmail());
                    editor.putString("phone_user", loginResponse.getData().getPhone());
                    editor.putString("password_user", password);
                    editor.apply();

                }else {
                    mProgressBarData.postValue(View.GONE);
                    mLoginResult.postValue(false);
                    mLoginMessage.postValue("Failed on: " + loginResponse.getMessage());
                }
            }
            @Override
            public void onFailure(Throwable t) {
                mProgressBarData.postValue(View.GONE);
                mLoginResult.postValue(false);
                mLoginMessage.postValue("Faild on: " + t.getMessage());
            }
        });
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
