package com.example.myapplication.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Product;
import com.example.myapplication.R;
import com.example.myapplication.View.DetailActivity;
import com.example.myapplication.ViewModel.ProductViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    private List<Product> productList;
    private ProductViewModel productViewModel;
    // 1 là yêu thích, 0 là list sản phẩm
    private final byte favourite;

    public ProductAdapter(ProductViewModel productViewModel, byte favourite) {
        this.productViewModel = productViewModel;
        this.favourite = favourite;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        if (product == null) {
            return;
        }
        if(product.getFavourite() == 1){
            holder.favorite.setImageResource(R.drawable.heart_no_stroke);
        }else{
            holder.favorite.setImageResource(R.drawable.heart_stroke);
        }
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(product.getFavourite() == 1){
                    productViewModel.deleteFavouriteProduct(product.getId());
                    // Nếu đang ở màn hình yêu thích thì cập nhật lại dữ liệu
                    if (favourite == 1)
                        productViewModel.fetchDataProductFavourite();
                    else
                        productViewModel.fetchDataSQLite(product.getIdCategory());

                }else{
                    productViewModel.addFavouriteProduct(product.getId());
                    // Nếu đang ở màn hình yêu thích thì cập nhật lại dữ liệu
                    if (favourite == 1)
                        productViewModel.fetchDataProductFavourite();
                    else
                        productViewModel.fetchDataSQLite(product.getIdCategory());
                }
            }
        });
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.valueOf(product.getPrice()));
        Picasso.get().load(product.getImageUrl()).into(holder.imgProduct);
        holder.imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(v.getContext(), DetailActivity.class);
                detail.putExtra("_id", product.get_id());
                detail.putExtra("id", product.getId());
                detail.putExtra("name", product.getName());
                detail.putExtra("price", product.getPrice());
                detail.putExtra("description", product.getDescription());
                detail.putExtra("imageUrl", product.getImageUrl());
                startActivity(v.getContext(), detail, null);
            }
        });
    }
    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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
