package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.OrderDetailItem;
import com.example.myapplication.R;
import com.example.myapplication.Utility;
import com.squareup.picasso.Picasso;

import java.util.List;


public class OrderDetailItemAdapter extends RecyclerView.Adapter <OrderDetailItemAdapter.ViewHolder> {
    private List<OrderDetailItem> orderDetailItemList;

    public OrderDetailItemAdapter(List<OrderDetailItem> orderDetailItemList) {
        this.orderDetailItemList = orderDetailItemList;
    }

    @NonNull
    @Override
    public OrderDetailItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_order_detail_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailItemAdapter.ViewHolder holder, int position) {
        OrderDetailItem orderDetailItem = orderDetailItemList.get(position);
        if (orderDetailItem == null) {
            return;
        }
        holder.productName.setText(orderDetailItem.getProduct().getName());
        holder.Description.setText(orderDetailItem.getProduct().getDescription());
        holder.Quantity.setText("Số lượng: " + String.valueOf(orderDetailItem.getQuantity()));
        holder.Price.setText("Giá: " + Utility.formatMoney(orderDetailItem.getPrice()) + " đ");
        Picasso.get().load(orderDetailItem.getProduct().getImageUrl()).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return orderDetailItemList!= null ? orderDetailItemList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productName, Quantity, Price, Description;
        private ImageView productImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imageProduct);
            productName = itemView.findViewById(R.id.nameProduct);
            Description = itemView.findViewById(R.id.descriptionProduct);
            Quantity = itemView.findViewById(R.id.quantityProduct);
            Price = itemView.findViewById(R.id.priceProduct);
        }
    }
}
