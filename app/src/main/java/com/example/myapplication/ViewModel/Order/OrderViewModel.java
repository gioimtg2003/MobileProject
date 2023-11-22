package com.example.myapplication.ViewModel.Order;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Model.Product;
import com.example.myapplication.Repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderViewModel extends AndroidViewModel {
    private OrderRepository orderRepository;
    private MutableLiveData<List<Integer>> listIdChecked = new MutableLiveData<>();
    private MutableLiveData<List<Product>> listProductInCart = new MutableLiveData<>();
    private MutableLiveData<Integer> totalMoney = new MutableLiveData<>();
    public OrderViewModel(@NonNull Application application) {
        super(application);
        this.orderRepository = new OrderRepository(application);
        initData();
    }
    private void initData(){
        List<Product> listProduct =  new ArrayList<Product>();
        List<Integer> listId = new ArrayList<Integer>();
        this.listIdChecked.setValue(listId);
        this.listProductInCart.setValue(listProduct);
    }
    public void getProductInCart(){
        orderRepository.getProductInCart(listIdChecked.getValue(), new OrderRepository.IDataProduct() {
            @Override
            public void ProductList(List<Product> listProduct) {
                listProductInCart.setValue(listProduct);
            }
        });
        int totalMoneyTemp = 0;
        for (Product product : listProductInCart.getValue()){
            totalMoneyTemp += product.getPrice() * product.getQuantity();
        }
        totalMoney.setValue(totalMoneyTemp);
    }
    public MutableLiveData<List<Integer>> getListIdChecked() {
        return listIdChecked;
    }

    public void setListIdChecked(MutableLiveData<List<Integer>> listIdChecked) {
        this.listIdChecked = listIdChecked;
    }

    public MutableLiveData<List<Product>> getListProductInCart() {
        return listProductInCart;
    }

    public void setListProductInCart(MutableLiveData<List<Product>> listProductInCart) {
        this.listProductInCart = listProductInCart;
    }

    public MutableLiveData<Integer> getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(MutableLiveData<Integer> totalMoney) {
        this.totalMoney = totalMoney;
    }
}
