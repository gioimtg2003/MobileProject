package com.example.myapplication.ViewModel.Order;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Model.Product;
import com.example.myapplication.NetworkApi.Order.BodyOrderDetail;
import com.example.myapplication.NetworkApi.Order.ResponseOrder;
import com.example.myapplication.Repository.CartRepository;
import com.example.myapplication.Repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderViewModel extends AndroidViewModel {
    private OrderRepository orderRepository;
    private CartRepository cartRepository;
    private MutableLiveData<List<Integer>> listIdChecked = new MutableLiveData<>();
    private MutableLiveData<List<Product>> listProductInCart = new MutableLiveData<>();
    private MutableLiveData<List<BodyOrderDetail>> listOrderDetails = new MutableLiveData<>();
    private MutableLiveData<Integer> totalMoney = new MutableLiveData<>();
    public OrderViewModel(@NonNull Application application) {
        super(application);
        this.orderRepository = new OrderRepository(application);
        this.cartRepository = new CartRepository(application);
        initData();
    }
    private void initData(){
        List<Product> listProduct =  new ArrayList<Product>();
        List<Integer> listId = new ArrayList<Integer>();
        List<BodyOrderDetail> listOrderDetailTemp = new ArrayList<BodyOrderDetail>();
        this.listIdChecked.setValue(listId);
        this.listProductInCart.setValue(listProduct);
        this.listOrderDetails.setValue(listOrderDetailTemp);
    }
    public void getProductInCart(){
        orderRepository.getProductInCart(listIdChecked.getValue(), new OrderRepository.IDataProduct() {
            @Override
            public void ProductList(List<Product> listProduct) {
                listProductInCart.setValue(listProduct);
            }
        });
        List<BodyOrderDetail> listOrderDetailTemp = new ArrayList<BodyOrderDetail>();
        int totalMoneyTemp = 0;
        for (Product product : listProductInCart.getValue()){
            totalMoneyTemp += product.getPrice() * product.getQuantity();
            listOrderDetailTemp.add(new BodyOrderDetail(product.get_id(), product.getQuantity(), product.getPrice()));
        }
        listOrderDetails.setValue(listOrderDetailTemp);
        totalMoney.setValue(totalMoneyTemp);
    }
    public void createOrder(String address, String userId){
        orderRepository.createOrder(address, userId, listOrderDetails.getValue(), new OrderRepository.IResponseOrder() {
            @Override
            public void IResponseOrder(ResponseOrder response) {
                Log.d("DEBUGCART", response.getMessage());
                Toast.makeText(getApplication(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                for (Integer i : listIdChecked.getValue()){
                    cartRepository.deleteCart(i);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
