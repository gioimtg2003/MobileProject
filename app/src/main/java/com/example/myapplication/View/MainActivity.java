package com.example.myapplication.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.myapplication.R;
import com.example.myapplication.View.Fragment.CartFragment;
import com.example.myapplication.View.Fragment.FavouriteFagment;
import com.example.myapplication.View.Fragment.HomeFragment;
import com.example.myapplication.View.Fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
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

        HomeFragment homeFragment = new HomeFragment();
        ReplaceFragment(new HomeFragment());
        floatingActionButton.setOnClickListener(v -> {
            ReplaceFragment(new CartFragment());
            bottomNavigationView.setSelectedItemId(R.id.cart);
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
                } else if(i == R.id.profile){
                    ReplaceFragment(new ProfileFragment());
                }
                return true;
            }
        });
    }
    private void ReplaceFragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}