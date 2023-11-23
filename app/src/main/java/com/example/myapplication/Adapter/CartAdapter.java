package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Cart;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.Main.CartViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private List<Cart> cartList;
    private CartViewModel cartViewModel;

    public CartAdapter(List<Cart> cartList, CartViewModel cartViewModel) {
        this.cartList = cartList;
        this.cartViewModel = cartViewModel;
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
        holder.checkBox.setChecked(cart.getChecked());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        cartViewModel.addListChecked(position);
                    }else {
                        cartViewModel.removeListChecked(position);
                    }
            }
        });
        holder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Tính năng hiện đang được phát triển", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Tính năng hiện đang được phát triển", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList != null ? cartList.size() : 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private Button btnIncrease, btnDecrease;
        private CheckBox checkBox;
        private ImageView imageProduct;
        private TextView nameProduct, priceProduct;
        private EditText editQuantity;
            public CartViewHolder(@NonNull View itemView) {
                super(itemView);
                btnIncrease = itemView.findViewById(R.id.btnIncrease);
                btnDecrease = itemView.findViewById(R.id.btnDecrease);
                checkBox = itemView.findViewById(R.id.radioBtn);
                imageProduct = itemView.findViewById(R.id.imageProduct);
                nameProduct = itemView.findViewById(R.id.nameProduct);
                priceProduct = itemView.findViewById(R.id.priceProduct);
                editQuantity = itemView.findViewById(R.id.editQuantity);
                editQuantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {

                    }
                });
            }
        }
}
