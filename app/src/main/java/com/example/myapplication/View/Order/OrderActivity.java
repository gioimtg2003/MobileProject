package com.example.myapplication.View.Order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Adapter.OrderItemAdapter;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;
import com.example.myapplication.Utility;
import com.example.myapplication.ViewModel.Order.OrderViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private Intent intent;
    private List<Integer> listChecked;
    private OrderViewModel orderViewModel;
    private OrderItemAdapter orderItemAdapter;
    private RecyclerView rcvOrderItem;
    private Button btnOrder;
    private TextView txtTotalMoney, txtTotalMoney1 , txtTotalMoney0;
    private MaterialToolbar materialToolbar;
    private String idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        getDataSharePreferences();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvOrderItem.setLayoutManager(linearLayoutManager);
        this.orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        getDataFromIntent();
        getProductInCart();
        handleClick();
        listenerData();
    }
    private void initView(){
        this.rcvOrderItem = this.findViewById(R.id.recyclerViewOrder);
        this.btnOrder = this.findViewById(R.id.btnOrder);
        this.txtTotalMoney = this.findViewById(R.id.totalMoney);
        this.txtTotalMoney0 = this.findViewById(R.id.totalMoney0);
        this.txtTotalMoney1 = this.findViewById(R.id.totalMoney1);
        this.materialToolbar = this.findViewById(R.id.materialToolbar);

    }
    private void getDataFromIntent(){
        intent = getIntent();
        listChecked = intent.getIntegerArrayListExtra("listChecked");
        orderViewModel.getListIdChecked().setValue(listChecked);
    }
    private void getProductInCart(){
        orderViewModel.getProductInCart();
    }
    private void listenerData(){
        orderViewModel.getListProductInCart().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                orderItemAdapter = new OrderItemAdapter(productList);
                rcvOrderItem.setAdapter(orderItemAdapter);
            }
        });
        orderViewModel.getTotalMoney().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                txtTotalMoney0.setText(Utility.formatMoney(integer) + " đ");
                txtTotalMoney.setText(Utility.formatMoney(integer) + " đ");
                txtTotalMoney1.setText(Utility.formatMoney(integer) + " đ");
            }
        });
    }
    private void handleClick(){
        this.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        this.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderViewModel.createOrder("hello", idUser);
                onBackPressed();
            }
        });
    }
    private void getDataSharePreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("APP_STORAGE", MODE_PRIVATE);
        this.idUser = sharedPreferences.getString("id_user", "");
    }
}