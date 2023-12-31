package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private Button btn;
    private GoogleSignInClient client;
    private FirebaseAuth mAuth;
    private static final int REQ_ONE_TAP = 2;
    private boolean showOneTapUI = true;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private SensorManager sensorManager;
    private Sensor pressure;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
//        mAuth = FirebaseAuth.getInstance();
//        Button btn2 = findViewById(R.id.btnRegister);
//
//        // FirebaseAuth.getInstance().signOut();
//        oneTapClient = Identity.getSignInClient(this);
//        signInRequest = BeginSignInRequest.builder()
//                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                        .setSupported(true)
//                        .setServerClientId(getString(R.string.default_web_client_id))
//                        .setFilterByAuthorizedAccounts(true)
//                        .build()
//                ).build();
//        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        client = GoogleSignIn.getClient(this, options);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("oke", "onClick: " + mAuth.getCurrentUser());
//                Intent intent = client.getSignInIntent();
//                startActivityForResult(intent, 200);
//            }
//        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 200:
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
                                            Log.d("oke", "onComplete: " + user.getDisplayName()+ " "+ user.getEmail() + " " + user.getPhotoUrl() + " " + user.getUid());

                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.d("LOGIN_GOOGLE", "signInWithCredential:failure", task.getException());

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
//        if(requestCode==200){
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                GoogleSignInAccount account = task.getResult();
//                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
//                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isComplete()){
//                            Log.d("LOGIN_ERROR", "onComplete: " + account.getDisplayName()+ " "+ account.getEmail() + " " + account.getPhotoUrl() + " " + account.getId());
//                        }else {
//                            Log.d("LOGIN_ERROR", "onComplete: Error" + task.getException());
//                        }
//                    }
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float millibarsOfPressure = event.values[0];
        Log.d("APPDATA", "onSensorChanged: " + millibarsOfPressure);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorManager.registerListener(this, pressure, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}