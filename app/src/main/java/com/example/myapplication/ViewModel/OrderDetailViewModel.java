package com.example.myapplication.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.OrderDetail;
import com.example.myapplication.Model.OrderDetailItem;
import com.example.myapplication.Repository.OrderDetailRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailViewModel extends ViewModel {
    private MutableLiveData<List<OrderDetailItem>> orderDetailItems = new MutableLiveData<List<OrderDetailItem>>();
    private MutableLiveData<List<OrderDetail>> orderDetailAPI = new MutableLiveData<List<OrderDetail>>();
    private MutableLiveData<List<OrderDetail>> orderDetail = new MutableLiveData<List<OrderDetail>>();

    private MutableLiveData<String> totalPrice = new MutableLiveData<String>();
    private MutableLiveData<String> address = new MutableLiveData<String>();
    private MutableLiveData<String> statusName = new MutableLiveData<String>();
    private MutableLiveData<String> createDate = new MutableLiveData<String>();
    private MutableLiveData<Boolean> checkBtn = new MutableLiveData<Boolean>();
    private OrderDetailRepository orderDetailRepository;
    public OrderDetailViewModel() {
        this.orderDetailRepository = new OrderDetailRepository();
        initData();
    }
    private void initData() {
        List<OrderDetailItem> orderDetailItemList = new ArrayList<OrderDetailItem>();
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        orderDetailItems.setValue(orderDetailItemList);
        orderDetail.setValue(orderDetailList);
        totalPrice.setValue("");
        address.setValue("");
        statusName.setValue("");
        createDate.setValue("");
        checkBtn.setValue(false);
    }

    public void getOrderDetails(String userId) {
        orderDetailRepository.getOrderDetail(userId, new OrderDetailRepository.IGetDataOrderDetail() {
            @Override
            public void onSuccess(List<OrderDetail> orderDetails) {
                List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
                int totalPrice = 0;
                for (OrderDetail item : orderDetails) {
                    for (OrderDetailItem orderDetailItem : item.getOrderDetails()) {
                        totalPrice += orderDetailItem.getPrice() * orderDetailItem.getQuantity();
                    }
                    orderDetailList.add(new OrderDetail(item.getAddress(), item.getStatusName(), item.getCreateDate(), item.getOrderDetails(), totalPrice));
                }
                orderDetail.setValue(orderDetailList);
                Log.d("ORDERDETAIL", "size chi tiết: " + String.valueOf(orderDetail.getValue().size()));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public MutableLiveData<List<OrderDetailItem>> getOrderDetailItems() {
        return orderDetailItems;
    }

    public void setOrderDetailItems(MutableLiveData<List<OrderDetailItem>> orderDetailItems) {
        this.orderDetailItems = orderDetailItems;
    }

    public MutableLiveData<List<OrderDetail>> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(MutableLiveData<List<OrderDetail>> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public MutableLiveData<String> getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(MutableLiveData<String> totalPrice) {
        this.totalPrice = totalPrice;
    }

    public MutableLiveData<String> getAddress() {
        return address;
    }

    public void setAddress(MutableLiveData<String> address) {
        this.address = address;
    }

    public MutableLiveData<String> getStatusName() {
        return statusName;
    }

    public void setStatusName(MutableLiveData<String> statusName) {
        this.statusName = statusName;
    }

    public MutableLiveData<String> getCreateDate() {
        return createDate;
    }

    public void setCreateDate(MutableLiveData<String> createDate) {
        this.createDate = createDate;
    }

    public MutableLiveData<Boolean> getCheckBtn() {
        return checkBtn;
    }

    public void setCheckBtn(MutableLiveData<Boolean> checkBtn) {
        this.checkBtn = checkBtn;
    }
}
