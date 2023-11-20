package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Cart;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private List<Cart> cartList;

    public CartAdapter(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.nameProduct.setText(cart.getName());
        holder.priceProduct.setText(String.valueOf(cart.getPrice()));
        holder.editQuantity.setText(String.valueOf(cart.getQuantity()));
        Picasso.get()
                .load(cart.getImageUrl())
                .error(R.drawable.category_garan)
                .into(holder.imageProduct);
    }

    @Override
    public int getItemCount() {
        return cartList != null ? cartList.size() : 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private Button btnIncrease, btnDecrease;
        private RadioButton radioBtn;
        private ImageView imageProduct;
        private TextView nameProduct, priceProduct, editQuantity;
            public CartViewHolder(@NonNull View itemView) {
                super(itemView);
                btnIncrease = itemView.findViewById(R.id.btnIncrease);
                btnDecrease = itemView.findViewById(R.id.btnDecrease);
                radioBtn = itemView.findViewById(R.id.radioBtn);
                imageProduct = itemView.findViewById(R.id.imageProduct);
                nameProduct = itemView.findViewById(R.id.nameProduct);
                priceProduct = itemView.findViewById(R.id.priceProduct);
                editQuantity = itemView.findViewById(R.id.editQuantity);
            }
        }
}
