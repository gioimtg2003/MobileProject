package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.myapplication.Adapter.OrderDetailAdapter;
import com.example.myapplication.Model.OrderDetail;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.OrderDetailViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    private RecyclerView rcvOrderDetailItem;
    private MaterialToolbar toolbar;
    private OrderDetailViewModel orderDetailViewModel;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        SharedPreferences sharedPreferences = getSharedPreferences("APP_STORAGE", MODE_PRIVATE);
        this.userId = sharedPreferences.getString("id_user", "");
        this.orderDetailViewModel = new ViewModelProvider(this).get(OrderDetailViewModel.class);
        orderDetailViewModel.getOrderDetails(userId);
        initView();
        handleClick();
        listenData();

    }

    private void initView() {
        rcvOrderDetailItem = findViewById(R.id.rcvOrderDetailItem);
        toolbar = findViewById(R.id.materialToolbar);
    }

    private void handleClick() {
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }
    private void listenData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvOrderDetailItem.setLayoutManager(linearLayoutManager);

        orderDetailViewModel.getOrderDetail().observe(this, new Observer<List<OrderDetail>>() {
            @Override
            public void onChanged(List<OrderDetail> orderDetails) {
                OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(orderDetails);
                rcvOrderDetailItem.setAdapter(orderDetailAdapter);

            }
        });

    }
}