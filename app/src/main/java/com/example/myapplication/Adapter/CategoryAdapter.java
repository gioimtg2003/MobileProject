package com.example.myapplication.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Category;
import com.example.myapplication.R;
import com.example.myapplication.View.ListProductActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final List<Category> mCategory;


    public CategoryAdapter(List<Category> mCategory) {
        this.mCategory = mCategory;

    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Category category = mCategory.get(position);
        holder.tvNameCategory.setText(category.getName());
        Picasso.get()
                .load(category.getImageUrl())
                .error(R.drawable.category_garan)
                .into(holder.imgCategory);
        holder.imgCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // put bundle
                Intent intent = new Intent(v.getContext(), ListProductActivity.class);
                intent.putExtra("idCategory", category.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategory == null ? 0 : mCategory.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCategory;
        private TextView tvNameCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.img_category);
            tvNameCategory = itemView.findViewById(R.id.tv_name_category);
        }
    }
}
