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

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> productList;
    private ProductViewModel productViewModel;

    public ProductAdapter(List<Product> productList, ProductViewModel productViewModel) {
        this.productList = productList;
        this.productViewModel = productViewModel;
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
        Log.d("APPDATA", product.getName() + " id " + String.valueOf(product.getIdCategory()) + " favourite: " + String.valueOf(product.getFavourite()));
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
                productViewModel = new ViewModelProvider((ViewModelStoreOwner) v.getContext()).get(ProductViewModel.class);
                if(product.getFavourite() == 1){
                    productViewModel.deleteFavouriteProduct(product.getId());
                }else{
                    productViewModel.addFavouriteProduct(product.getId());
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView favorite;
        private ImageView imgUser;
        private TextView tvName;
        private TextView tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser=itemView.findViewById(R.id.img_user);
            tvName=itemView.findViewById(R.id.tv_name);
            tvPrice=itemView.findViewById(R.id.tv_price);
            favorite = itemView.findViewById(R.id.favourite);
        }
    }
}
