package com.example.myapplication.View.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.CategoryAdapter;
import com.example.myapplication.Model.Category;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView categoryRecyclerView;
    private RecyclerView productRecyclerView;
    private List<Category> categoryList ;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("TAG","onAttach: " + getActivity());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG","onCreate: " + getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TAG","OnStart: " + getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TAG","OnStop: " + getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG","OnDestroy: " + getActivity());

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG","OnResume: " + getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TAG","OnCreateView: " + getActivity());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TAG","OnCreatedView: " + getActivity());

        categoryRecyclerView = view.findViewById(R.id.recycerViewCategory);
        productRecyclerView = view.findViewById(R.id.recycerViewFoodDelicious);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setAdapter(new CategoryAdapter(setDataCategory()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        productRecyclerView.setLayoutManager(gridLayoutManager);
        productRecyclerView.setAdapter(new CategoryAdapter(setDataCategory()));
    }
    private List<Category> setDataCategory(){
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Gà rán","2"));
        categoryList.add(new Category("Gà rán","2"));
        categoryList.add(new Category("Gà rán","2"));
        categoryList.add(new Category("Gà rán","2"));
        categoryList.add(new Category("Gà rán","2"));
        categoryList.add(new Category("Gà rán","2"));
        return categoryList;
    }
    private List<Product> setDataProduct() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Gà rán", 2, 2, "", ""));
        productList.add(new Product("Gà rán", 2, 2, "", ""));
        productList.add(new Product("Gà rán", 2, 2, "", ""));
        productList.add(new Product("Gà rán", 2, 2, "", ""));
        productList.add(new Product("Gà rán", 2, 2, "", ""));
        productList.add(new Product("Gà rán", 2, 2, "", ""));
        productList.add(new Product("Gà rán", 2, 2, "", ""));
        productList.add(new Product("Gà rán", 2, 2, "", ""));
        return productList;
    }

}