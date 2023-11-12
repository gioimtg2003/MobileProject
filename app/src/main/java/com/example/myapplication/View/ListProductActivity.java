package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Adapter.ProductAdapter;
import com.example.myapplication.Database.DatabaseProduct;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends AppCompatActivity {
    private RecyclerView rcvProduct;
    private ProductAdapter mProductAdapter;
    private ImageView backPress;
    private TextView tvNameCategory;
    private int idCategory;
    private ProductViewModel productViewModel;
    private GridLayoutManager gridLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        this.rcvProduct = this.findViewById(R.id.rcv_product);
        this.backPress = this.findViewById(R.id.back_press);
        this.tvNameCategory = this.findViewById(R.id.title_category);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        Intent intent = getIntent();
        this.idCategory = intent.getIntExtra("idCategory",0);
        this.tvNameCategory.setText(intent.getStringExtra("nameCategory"));
        this.backPress.setOnClickListener(v -> {
            finish();
        });
        mProductAdapter = new ProductAdapter(productViewModel);
        gridLayoutManager = new GridLayoutManager(ListProductActivity.this,2);
        setAdapter();


    }
    private void setAdapter(){
        productViewModel.fetchDataSQLite(this.idCategory);
        productViewModel.getListProductMutableLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                mProductAdapter.setProductList(products);
                rcvProduct.setAdapter(mProductAdapter);
                rcvProduct.setLayoutManager(gridLayoutManager);
                Log.d("APPDATA", "onChanged: " + products.size());
            }
        });

    }
}