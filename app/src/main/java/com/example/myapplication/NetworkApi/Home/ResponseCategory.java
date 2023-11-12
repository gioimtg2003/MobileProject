package com.example.myapplication.NetworkApi.Home;

import com.example.myapplication.Model.Category.Category;

import java.util.List;

public class ResponseCategory {
    private int code;
    private String message;

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

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    public ResponseCategory(int code, String message, List<Category> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private List<Category> data;
}
