package com.example.myapplication.NetworkApi.Order;

import java.util.List;

public class BodyOrder {
    private String address;
    private String user;
    private List<BodyOrderDetail> orderDetails;

    public BodyOrder(String address, String user, List<BodyOrderDetail> orderDetails) {
        this.address = address;
        this.user = user;
        this.orderDetails = orderDetails;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<BodyOrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<BodyOrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
