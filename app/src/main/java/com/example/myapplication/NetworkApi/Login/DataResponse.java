package com.example.myapplication.NetworkApi.Login;

import com.google.gson.annotations.SerializedName;

public class DataResponse {
    @SerializedName("_id")
    private String _id;
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;

    public DataResponse(String _id, String email, String name, String phone) {
        this._id = _id;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
