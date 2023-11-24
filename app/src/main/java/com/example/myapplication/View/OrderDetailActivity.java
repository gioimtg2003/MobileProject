package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;
import com.google.android.material.appbar.MaterialToolbar;

public class OrderDetailActivity extends AppCompatActivity {
    private RecyclerView rcvOrderDetailItem;
    private MaterialToolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initView();
    }

    private void initView() {
        rcvOrderDetailItem = findViewById(R.id.rcvOrderDetailItem);
        toolbar = findViewById(R.id.toolbar);
    }
}