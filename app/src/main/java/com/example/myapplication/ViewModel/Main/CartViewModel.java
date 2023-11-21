package com.example.myapplication.ViewModel.Main;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Model.Cart;
import com.example.myapplication.Repository.CartRepository;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private MutableLiveData<List<Cart>> listCart = new MutableLiveData<List<Cart>>();
    private MutableLiveData<List<Integer>> listChecked = new MutableLiveData<List<Integer>>();
    private MutableLiveData<Boolean> containerDelete = new MutableLiveData<Boolean>();
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
        for (Integer i : listCheckedTemp){
            Log.d("LISTCHECKED", "Add i = " + String.valueOf(i));
        }
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
        for (Integer i : listChecked.getValue()){
            Log.d("LISTCHECKED", "deleted i =" + String.valueOf(i));
        }
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
}
