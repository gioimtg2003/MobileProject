package com.example.myapplication.View.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.RoundedCornerTreatment;

public class HomeActivity extends Activity {
    private BottomAppBar bottomAppBar;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



    }
}