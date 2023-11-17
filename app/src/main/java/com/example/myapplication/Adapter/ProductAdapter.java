package com.example.myapplication.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Product;
import com.example.myapplication.R;
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
                    if (favourite == 1)
                        productViewModel.fetchDataProductFavourite();
                    else
                        productViewModel.fetchDataSQLite(product.getIdCategory());

                }else{
                    productViewModel.addFavouriteProduct(product.getId());
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct=itemView.findViewById(R.id.img_product);
            tvName=itemView.findViewById(R.id.tv_name);
            tvPrice=itemView.findViewById(R.id.tv_price);
            favorite = itemView.findViewById(R.id.favourite);
        }
    }
}
