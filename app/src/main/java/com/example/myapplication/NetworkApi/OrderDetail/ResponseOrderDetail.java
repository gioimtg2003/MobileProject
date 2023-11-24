package com.example.myapplication.NetworkApi.OrderDetail;

import java.util.List;

public class ResponseOrderDetail {
    private int code;
    private String message;
    private List<OrderDetail> data;

    public ResponseOrderDetail(int code, String message, List<OrderDetail> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OrderDetail> getData() {
        return data;
    }

    public void setData(List<OrderDetail> data) {
        this.data = data;
    }
}
