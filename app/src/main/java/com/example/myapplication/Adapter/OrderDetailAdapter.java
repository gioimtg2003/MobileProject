package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.OrderDetail;
import com.example.myapplication.R;
import com.example.myapplication.Utility;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    private List<OrderDetail> orderDetailList;

    public OrderDetailAdapter(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @NonNull
    @Override
    public OrderDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_order_detail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.ViewHolder holder, int position) {
        OrderDetail orderDetail = orderDetailList.get(position);
        if (orderDetail == null) {
            return;
        }
        String statusName = orderDetail.getStatusName();
        switch (statusName) {
            case "Pending":
                holder.statusName.setText("Đang chờ xử lý");
                holder.btnHandle.setText("Hủy đơn hàng");
                break;
            case "Delivering":
                holder.statusName.setText("Đang giao hàng");
                holder.btnHandle.setText("Đang giao");
                break;
            case "Delivered":
                holder.statusName.setText("Đã giao hàng");
                holder.btnHandle.setText("Đánh giá");
                break;
        }
        holder.createDate.setText("Ngày tạo đơn: " + Utility.formatDate(orderDetail.getCreateDate()));
        holder.totalMoney.setText("Tổng tiền: " + Utility.formatMoney(orderDetail.getTotalPrice()) + " đ");

    }

    @Override
    public int getItemCount() {
        return orderDetailList != null ? orderDetailList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView statusName, createDate, address, totalMoney;
        private RecyclerView rcvOrderDetailItems;
        private Button btnHandle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            statusName = itemView.findViewById(R.id.statusName);
            createDate = itemView.findViewById(R.id.timeStatus);
            totalMoney = itemView.findViewById(R.id.totalPrice);
            rcvOrderDetailItems = itemView.findViewById(R.id.rcvOrderDetailItems);
            btnHandle = itemView.findViewById(R.id.btnHandle);
        }
    }
}
