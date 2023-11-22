package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Product;
import com.example.myapplication.R;
import com.example.myapplication.Utility;
import com.squareup.picasso.Picasso;

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
        Product product = listProduct.get(position);
        holder.nameProduct.setText(product.getName());
        holder.descriptionProduct.setText(product.getDescription());
        holder.priceProduct.setText(Utility.formatMoney(product.getPrice()) + " đ");
        holder.quantityProduct.setText("x" + product.getQuantity());
        Picasso.get().load(product.getImageUrl()).into(holder.imageProduct);
    }

    @Override
    public int getItemCount() {
        return listProduct == null ? 0 : listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameProduct, descriptionProduct, priceProduct, quantityProduct;
        private ImageView imageProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nameProduct = itemView.findViewById(R.id.nameProduct);
            this.descriptionProduct = itemView.findViewById(R.id.descriptionProduct);
            this.priceProduct = itemView.findViewById(R.id.priceProduct);
            this.quantityProduct = itemView.findViewById(R.id.quantityProduct);
            this.imageProduct = itemView.findViewById(R.id.imageProduct);
        }
    }
}
