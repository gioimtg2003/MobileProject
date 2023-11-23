package com.example.myapplication.View.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.CartAdapter;
import com.example.myapplication.Model.Cart;
import com.example.myapplication.R;
import com.example.myapplication.Utility;
import com.example.myapplication.ViewModel.Main.CartViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {
    private RecyclerView rcvCart;
    private ConstraintLayout containerDelete;
    private CartViewModel cartViewModel;
    private CartAdapter cartAdapter;
    private TextView txtTotalMoney, title;
    private Button btnDelete, btnPurchase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("DEBUGCART", "fragment OnCreateView: ");
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvCart.setLayoutManager(linearLayoutManager);
        listenerData();
        handleClick();
        Log.d("DEBUGCART", "fragment onViewCreated: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ArrayList<Integer> listTemp = new ArrayList<Integer>();
        cartViewModel.getListChecked().setValue(listTemp);
        cartViewModel.getTotalMoney().setValue(0);
        cartViewModel.getContainerDelete().setValue(false);

    }

    private void initView(View view){
        rcvCart = view.findViewById(R.id.rcvCart);
        containerDelete = view.findViewById(R.id.containerDelete);
        txtTotalMoney = view.findViewById(R.id.totalMoney);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnPurchase = view.findViewById(R.id.btnPurchase);
        title = view.findViewById(R.id.title);
        title.setText("Giỏ hàng (0)");
    }
    private void listenerData(){
        this.cartViewModel.getListCart().observe(requireActivity(), new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {
                cartAdapter = new CartAdapter(carts, cartViewModel);
                rcvCart.setAdapter(cartAdapter);
                title.setText("Giỏ hàng (" + carts.size() + ")");
            }
        });
        this.cartViewModel.getContainerDelete().observe(requireActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    containerDelete.setVisibility(View.VISIBLE);
                }else{
                    containerDelete.setVisibility(View.GONE);
                }
            }
        });
        this.cartViewModel.getTotalMoney().observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                txtTotalMoney.setText("Tổng cộng: " + Utility.formatMoney(integer));
            }
        });
    }
    private void handleClick(){
        this.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartViewModel.deleteCart();
            }
        });
        this.btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartViewModel.getListChecked().getValue().size() > 0) {
                    cartViewModel.buyCart();
                }else {
                    Toast.makeText(requireActivity(), "Vui lòng chọn sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}