package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.myapplication.Adapter.ProductAdapter;
import com.example.myapplication.Database.DatabaseProduct;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends AppCompatActivity {
    private RecyclerView rcvProduct;
    private ProductAdapter mProductAdapter;
    private ImageView backPress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        this.rcvProduct = this.findViewById(R.id.rcv_product);
        this.backPress = this.findViewById(R.id.back_press);
        this.backPress.setOnClickListener(v -> {
            finish();
        });
        this.mProductAdapter = new ProductAdapter(getList());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcvProduct.setLayoutManager(gridLayoutManager);
        rcvProduct.setAdapter(mProductAdapter);
    }
    private List<Product> getList(){
        List<Product> list = new ArrayList<Product>();
        DatabaseProduct databaseProduct = new DatabaseProduct(this);
        return databaseProduct.getAllProduct();
    }
}