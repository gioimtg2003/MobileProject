package com.example.myapplication.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.OrderDetailItem;

import java.util.List;

public class OrderDetailViewModel extends ViewModel {
    private MutableLiveData<List<OrderDetailItem>> orderDetailItems = new MutableLiveData<List<OrderDetailItem>>();
}
