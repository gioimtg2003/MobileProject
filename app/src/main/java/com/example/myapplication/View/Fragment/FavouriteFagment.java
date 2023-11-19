package com.example.myapplication.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Adapter.ProductAdapter;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;
import com.example.myapplication.View.ListProductActivity;
import com.example.myapplication.ViewModel.ProductViewModel;

import java.util.List;


public class FavouriteFagment extends Fragment {
    private RecyclerView rcvProduct;
    private ProductAdapter mProductAdapter;
    private ImageView backPress;
    private TextView tvNameCategory;
    private int idCategory;
    private ProductViewModel productViewModel;
    private GridLayoutManager gridLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favourite_fagment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rcvProduct = view.findViewById(R.id.rcv_product);
        this.tvNameCategory = view.findViewById(R.id.title_category);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        mProductAdapter = new ProductAdapter(productViewModel, (byte) 1);
        gridLayoutManager = new GridLayoutManager(requireActivity(),2);
        setAdapter();
    }

    private void setAdapter() {
        productViewModel.fetchDataProductFavourite();
        productViewModel.getListProductMutableLiveData().observe(requireActivity(), new Observer<List<Product>>() {
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