package com.example.myapplication.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.myapplication.ViewModel.LoginViewModel;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Activity_Login extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button btnLogin;
    private Button btnRegister;
    private Button btnLoginGoogle;
    private EditText inputEmail;
    private EditText inputPassword;
    private LoginViewModel loginViewModel;
    private GoogleSignInClient client;
    private FirebaseAuth mAuth;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private static final int REQ_ONE_TAP = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkSharedPrefs();
        setContentView(R.layout.activity_login_app);
        loginViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(LoginViewModel.class);
        firebaseGetInstance();
        mapping();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.login(inputEmail.getText().toString(), inputPassword.getText().toString());
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegisterIntent = new Intent(getApplication(), Activity_Register.class);
                startActivity(RegisterIntent);
            }
        });
        btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = client.getSignInIntent();
                startActivityForResult(intent, 200);
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
        btnRegister = findViewById(R.id.btn_register);
    }
    private void liveDataListener(){
        loginViewModel.getmProgressBarData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(integer);
            }
        });
        loginViewModel.getmLoginMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.equals("")){
                    Toast.makeText(Activity_Login.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginViewModel.getmLoginResult().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
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
    private void firebaseGetInstance(){
        mAuth = FirebaseAuth.getInstance();
        oneTapClient = Identity.getSignInClient(this);
//        signInRequest = BeginSignInRequest.builder()
//                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                        .setSupported(true)
//                        .setServerClientId(getString(R.string.default_web_client_id))
//                        .setFilterByAuthorizedAccounts(true)
//                        .build()
//                ).build();
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(this, options);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_ONE_TAP:
                try {

                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                    String idToken = credential.getGoogleIdToken();
                    if (idToken !=  null) {
                        AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
                        mAuth.signInWithCredential(firebaseCredential)
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d("LOGIN_GOOGLE", "signInWithCredential:success");
                                            FirebaseUser user = mAuth.getCurrentUser();

                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w("LOGIN_GOOGLE", "signInWithCredential:failure", task.getException());

                                        }
                                    }
                                });
                        Log.d("LOGIN_GOOGLE", "Got ID token.");
                    }
                } catch (ApiException e) {
                    // ...
                }
                break;
        }
    }
}