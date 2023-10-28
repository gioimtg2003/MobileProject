package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.View.Home.HomeActivity;
import com.example.myapplication.ViewModel.LoginViewModel;

public class Activity_Login extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button btnLogin;
    private Button btnLoginGoogle;
    private EditText inputEmail;
    private EditText inputPassword;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);
        loginViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(LoginViewModel.class);
        mapping();
        checkSharedPrefs();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.login(inputEmail.getText().toString(), inputPassword.getText().toString());
            }
        });
        liveDataListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("APP_STORAGE", MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("is_login", false);
        if (isLogin){
            onBackPressed();
        }
    }

    private void mapping(){
        progressBar = findViewById(R.id.progressBar);
        btnLogin = findViewById(R.id.btn_login);
        btnLoginGoogle = findViewById(R.id.btn_login_google);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
    }
    private void liveDataListener(){
        loginViewModel.getmProgressBarData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(integer);
            }
        });
        loginViewModel.getmLoginResult().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Toast.makeText(Activity_Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent HomeIntent = new Intent(getApplication(), HomeActivity.class);
                    startActivity(HomeIntent);
                }
            }
        });
        loginViewModel.getmLoginMessage().observe(Activity_Login.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("LOGIN: ", s);
            }
        });
    }
    private void checkSharedPrefs(){
        SharedPreferences sharedPreferences = getSharedPreferences("APP_STORAGE", MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("is_login", false);
        if (isLogin){
            Intent HomeIntent = new Intent(getApplication(), HomeActivity.class);
            startActivity(HomeIntent);
        }
    }
}