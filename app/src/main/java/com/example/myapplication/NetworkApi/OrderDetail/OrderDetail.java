package com.example.myapplication.NetworkApi.OrderDetail;

import java.util.List;

public class OrderDetail {
    private String address;
    private String statusName;
    private String createDate;
    private List<OrderDetailItem> orderDetails;

    public OrderDetail(String address, String statusName, String createDate, List<OrderDetailItem> orderDetails) {
        this.address = address;
        this.statusName = statusName;
        this.createDate = createDate;
        this.orderDetails = orderDetails;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<OrderDetailItem> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailItem> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
