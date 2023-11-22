package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Product;
import com.example.myapplication.R;

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
    private List<Product> listProduct;
    public OrderItemAdapter(List<Product> listProduct){
        this.listProduct = listProduct;
    }
    @NonNull
    @Override
    public OrderItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activiti_order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listProduct == null ? 0 : listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameProduct, description, priceProduct, quantityProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
