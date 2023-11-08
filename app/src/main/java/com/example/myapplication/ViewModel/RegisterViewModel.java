package com.example.myapplication.ViewModel;

import android.app.Application;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.NetworkApi.Auth.AuthResponse;
import com.example.myapplication.NetworkApi.Auth.IResponse;
import com.example.myapplication.NetworkApi.Auth.Register.RegisterBody;
import com.example.myapplication.Repository.AuthRepository;

import kotlinx.coroutines.flow.MutableStateFlow;

public class RegisterViewModel extends AndroidViewModel {
    public MutableLiveData<Boolean> getValidatePassword() {
        return ValidatePassword;
    }

    public void setValidatePassword(MutableLiveData<Boolean> validatePassword) {
        ValidatePassword = validatePassword;
    }

    private MutableLiveData<Boolean> ValidatePassword = new MutableLiveData<Boolean>();
    private MutableLiveData<String> Password = new MutableLiveData<String>();
    private AuthRepository authRepository;
    private MutableLiveData<String> Message = new MutableLiveData<String>();

    public void setPassword(MutableLiveData<String> password) {
        Password = password;
    }
    public MutableLiveData<Boolean> getCheckRegister() {
        return checkRegister;
    }

    public void setCheckRegister(MutableLiveData<Boolean> checkRegister) {
        this.checkRegister = checkRegister;
    }

    private MutableLiveData<Boolean> checkRegister = new MutableLiveData<Boolean>();

    public MutableLiveData<String> getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password.postValue(password);
        checkPassword(password);
    }

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        ValidatePassword.postValue(true);
        Password.postValue("");
        authRepository = new AuthRepository();
        checkRegister.postValue(false);
    }
    private void checkPassword(String password){
        if (password.length() < 6){
            ValidatePassword.postValue(false);
        }
        else {
            ValidatePassword.postValue(true);
        }
    }
    public void register(String name, String email, String password, String phone){
        authRepository.Register(new RegisterBody(name, email, password, phone), new IResponse() {
            @Override
            public void onResponseAuth(AuthResponse loginResponse) {
                if(loginResponse.getCode() == 200){
                    checkRegister.setValue(true);
                    Message.setValue(loginResponse.getMessage());
                }else {
                    checkRegister.setValue(false);
                    Message.setValue(loginResponse.getMessage());
                }
                Log.d("REGISTER", getMessage().getValue());
                Log.d("REGISTER", String.valueOf(loginResponse.getCode()));
            }
            @Override
            public void onFailure(Throwable t) {
                getMessage().postValue(t.getMessage());
                checkRegister.setValue(false);
            }
        });
    }

    public MutableLiveData<String> getMessage() {
        return Message;
    }

    public void setMessage(MutableLiveData<String> message) {
        Message = message;
    }
}
