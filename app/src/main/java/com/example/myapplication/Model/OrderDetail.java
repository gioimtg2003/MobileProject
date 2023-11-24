package com.example.myapplication.Model;

import java.util.List;

public class OrderDetail {
    private String address;
    private String statusName;
    private String createDate;
    private List<OrderDetailItem> orderDetails;
    private int totalPrice;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderDetail(String address, String statusName, String createDate, List<OrderDetailItem> orderDetails, int totalPrice) {
        this.address = address;
        this.statusName = statusName;
        this.createDate = createDate;
        this.orderDetails = orderDetails;
        this.totalPrice = totalPrice;
    }

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
