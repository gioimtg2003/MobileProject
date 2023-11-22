package com.example.myapplication.ViewModel.Main;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Model.Cart;
import com.example.myapplication.Repository.CartRepository;
import com.example.myapplication.View.Order.OrderActivity;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private MutableLiveData<List<Cart>> listCart = new MutableLiveData<List<Cart>>();
    private MutableLiveData<List<Cart>> listProductBuy = new MutableLiveData<List<Cart>>();
    private MutableLiveData<List<Integer>> listChecked = new MutableLiveData<List<Integer>>();
    private MutableLiveData<Boolean> containerDelete = new MutableLiveData<Boolean>();
    private MutableLiveData<Integer> totalMoney = new MutableLiveData<Integer>();
    private CartRepository cartRepository;
    public CartViewModel(@NonNull Application application) {
        super(application);
        this.cartRepository = new CartRepository(application);
        initData();
    }
    private void initData(){
        this.containerDelete.setValue(false);
        List<Cart> listCart_ = new ArrayList<Cart>();
        List<Integer> listChecked_ = new ArrayList<Integer>();
        this.listCart.setValue(listCart_);
        this.listChecked.setValue(listChecked_);
        this.totalMoney.setValue(0);
        this.listProductBuy.setValue(listCart_);
    }

    /**
     * Cộng sản phẩm ở giỏ hàng
     */
    public void increaseCart(int id){
        cartRepository.increaseCart(id);
    }
    /**
     * Trừ sản phẩm ở giỏ hàng
     */
    public void decreaseCart(int id){
        cartRepository.descreaseCart(id);
    }

    public MutableLiveData<List<Cart>> getListCart() {
        cartRepository.getAllCart(new CartRepository.GetDataSqlite() {
            @Override
            public void getData(List<Cart> cartList) {
                listCart.postValue(cartList);
            }
        });
        return listCart;
    }
    public void addListChecked(int index){
        List<Integer> listCheckedTemp = listChecked.getValue();
        listCheckedTemp.add(index);
        listChecked.setValue(listCheckedTemp);

        if(listChecked.getValue().size() > 0){
            containerDelete.setValue(true);
        }else {
            containerDelete.setValue(false);
        }
        setTotalMoney();

        Log.d("DEBUGCART", "addListChecked id: " + String.valueOf(listCart.getValue().get(index).getId()));
        Log.d("DEBUGCART", "Size listchecked: " + String.valueOf(listChecked.getValue().size()));
    }
    public void addListProductBuy(int index){
        List<Cart> listProductBuyTemp = listProductBuy.getValue();
        listProductBuyTemp.add(listCart.getValue().get(index));
        listProductBuy.setValue(listProductBuyTemp);
    }
    public void removeListProductBuy(int index){
        List<Cart> listProductBuyTemp = listProductBuy.getValue();
        listProductBuyTemp.remove(listCart.getValue().get(index));
        listProductBuy.setValue(listProductBuyTemp);
    }
    public void removeListChecked(int index){
        List<Integer> integerList = listChecked.getValue();
        if(integerList.indexOf(index) > -1){
            integerList.remove(Integer.valueOf(index));
            listChecked.setValue(integerList);
            if(listChecked.getValue().size() > 0){
                containerDelete.setValue(true);
            }else {
                containerDelete.setValue(false);
            }
        }
        setTotalMoney();

        Log.d("DEBUGCART", "removeListChecked id: " + String.valueOf(listCart.getValue().get(index).getId()));
        Log.d("DEBUGCART", "Size listchecked: " + String.valueOf(listChecked.getValue().size()));
    }
    public void deleteCart(){
        for (Integer i : listChecked.getValue()){
            cartRepository.deleteCart(listCart.getValue().get(i).getId());
            Log.d("DEBUGCART", "deleteCart id: " + String.valueOf(listCart.getValue().get(i).getId()));
        }
        initData();
        getListCart();
    }
    public void buyCart(){
        Intent intent = new Intent(getApplication(), OrderActivity.class);

        List<Integer> listId = new ArrayList<Integer>();
        List<Cart> listCartTemp = listCart.getValue();
        for (Integer i : listChecked.getValue()){
            listId.add(listCartTemp.get(i).getId());
            Log.d("DEBUGCART", "buyCartID: " + String.valueOf(listCartTemp.get(i).getId()));
        }
        intent.putExtra("listChecked", (ArrayList<Integer>) listId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);
    }
    public void setListCart(MutableLiveData<List<Cart>> listCart) {
        this.listCart = listCart;
    }

    public MutableLiveData<Boolean> getContainerDelete() {
        return containerDelete;
    }

    public void setContainerDelete(MutableLiveData<Boolean> containerDelete) {
        this.containerDelete = containerDelete;
    }

    public MutableLiveData<List<Integer>> getListChecked() {
        return listChecked;
    }

    public void setListChecked(MutableLiveData<List<Integer>> listChecked) {
        this.listChecked = listChecked;
    }

    public MutableLiveData<Integer> getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney() {
        int totalMoneyTemp = 0;
        for (Integer i : listChecked.getValue()){
            totalMoneyTemp += listCart.getValue().get(i).getPrice() * listCart.getValue().get(i).getQuantity();
        }
        totalMoney.setValue(totalMoneyTemp);
    }

    public MutableLiveData<List<Cart>> getListProductBuy() {
        return listProductBuy;
    }

    public void setListProductBuy(MutableLiveData<List<Cart>> listProductBuy) {
        this.listProductBuy = listProductBuy;
    }
}
