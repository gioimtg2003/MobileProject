package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Product;
import com.example.myapplication.R;
import com.example.myapplication.View.Fragment.DetailsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductRandomAdapter extends RecyclerView.Adapter<ProductRandomAdapter.ViewHolder> {
    private List<Product> productList;
    public ProductRandomAdapter(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductRandomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductRandomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRandomAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        if (product == null) {
            return;
        }
        if(product.getFavourite() == 1){
            holder.favorite.setImageResource(R.drawable.heart_no_stroke);
        }else{
            holder.favorite.setImageResource(R.drawable.heart_stroke);
        }
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.valueOf(product.getPrice()));
        Picasso.get().load(product.getImageUrl()).into(holder.imgProduct);
        holder.imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DetailsFragment();

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView favorite;
        private ImageView imgProduct;
        private TextView tvName;
        private TextView tvPrice;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct=itemView.findViewById(R.id.img_product);
            tvName=itemView.findViewById(R.id.tv_name);
            tvPrice=itemView.findViewById(R.id.tv_price);
            favorite = itemView.findViewById(R.id.favourite);
            cardView = itemView.findViewById(R.id.card_product);
        }
    }
}
