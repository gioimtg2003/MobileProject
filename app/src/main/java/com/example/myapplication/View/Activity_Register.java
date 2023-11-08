package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ViewModel.RegisterViewModel;

public class Activity_Register extends AppCompatActivity {
    private EditText email;
    private EditText firstName;
    private EditText password;
    private EditText phone;
    private Button btnRegister;
    private TextView txtErrorPassword;
    private RegisterViewModel registerViewModel;
    private String email_;
    private String firstName_;
    private String password_;
    private String phone_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mapping();
        registerViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(RegisterViewModel.class);
        Validating();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getText();
                registerViewModel.register(firstName_, email_, password_, phone_);
            }
        });
        registerViewModel.getCheckRegister().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    onBackPressed();
                }
            }
        });
        registerViewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(Activity_Register.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void mapping(){
        email = findViewById(R.id.input_email);
        firstName = findViewById(R.id.input_name);
        phone = findViewById(R.id.input_phone);
        password = findViewById(R.id.input_password);
        btnRegister = findViewById(R.id.buttonRegister);
        txtErrorPassword = findViewById(R.id.error_password);
    }
    private void getText(){
        email_ = this.email.getText().toString();
        firstName_ = this.firstName.getText().toString();
        phone_ = this.phone.getText().toString();
        password_ = this.password.getText().toString();
    }
    private void Validating(){
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerViewModel.setPassword(password.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        registerViewModel.getValidatePassword().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean){
                    password.setBackground(getResources().getDrawable(R.drawable.error_password));
                    txtErrorPassword.setVisibility(View.VISIBLE);
                }
                else {
                    password.setBackground(getResources().getDrawable(R.drawable.custom_input));
                    txtErrorPassword.setVisibility(View.GONE);
                }
            }
        });
    }

}