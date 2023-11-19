package com.example.myapplication.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentActivity.*;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.View.Fragment.FavouriteFagment;
import com.example.myapplication.View.Fragment.HomeFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.RoundedCornerTreatment;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton floatingActionButton;
    private FragmentManager fragmentManager ;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        floatingActionButton = findViewById(R.id.fab);
        frameLayout = findViewById(R.id.frame_layout);
        fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        ReplaceFragment(new HomeFragment());
        floatingActionButton.setOnClickListener(v -> {
            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
        });
        // khi nhấn 2 lần vào 1 item nào đó thì sẽ không làm gì cả
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

            }
        });

//        bottomNavigationView.setOnNavigationItemSelectedListener(item -> when(item.getItemId()));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.home_fragment){
                    ReplaceFragment(new HomeFragment());
                } else if (i == R.id.favourite_fagment) {
                    ReplaceFragment(new FavouriteFagment());
                }
                return true;
            }
        });


    }
    private void ReplaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}