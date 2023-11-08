package com.example.myapplication.ViewModel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.NetworkApi.Auth.IResponse;
import com.example.myapplication.NetworkApi.Auth.Login.LoginBody;
import com.example.myapplication.NetworkApi.Auth.AuthResponse;
import com.example.myapplication.Repository.AuthRepository;

public class LoginViewModel extends AndroidViewModel {
    private final String SHARED_PREFERENCES_NAME = "APP_STORAGE";
    private final MutableLiveData<Integer> mProgressBarData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mLoginResult = new MutableLiveData<>();

    public MutableLiveData<String> getmLoginMessage() {
        return mLoginMessage;
    }

    private final MutableLiveData<String> mLoginMessage = new MutableLiveData<>();
    AuthRepository mainRepository;
    public MutableLiveData<Integer> getmProgressBarData() {
        return mProgressBarData;
    }

    public MutableLiveData<Boolean> getmLoginResult() {
        return mLoginResult;
    }

    public LoginViewModel(@NonNull Application application ){
        super(application);
        mainRepository = new AuthRepository();
        mProgressBarData.postValue(View.GONE);
        mLoginResult.setValue(false);
        mLoginMessage.postValue("");
    }

    /**
     * Hàm dùng để login
     */
    public void login(String email, String password){
        mProgressBarData.postValue(View.VISIBLE);
        mainRepository.login(new LoginBody(email, password), new IResponse() {
            @Override
            public void onResponseAuth(AuthResponse loginResponse) {
                if (loginResponse.getCode() == 200){
                    mProgressBarData.postValue(View.GONE);
                    mLoginResult.setValue(true);
                    mLoginMessage.setValue("Đăng nhập thành công");
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
                    mLoginResult.setValue(false);
                    mLoginMessage.setValue("Đăng nhập thất bại");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressBarData.postValue(View.GONE);
                mLoginResult.setValue(false);
                mLoginMessage.setValue("Đăng nhập thất bại");
            }
        });
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
