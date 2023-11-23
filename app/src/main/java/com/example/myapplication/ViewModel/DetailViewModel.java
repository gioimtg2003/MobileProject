package com.example.myapplication.ViewModel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Model.Cart;
import com.example.myapplication.Repository.DetailRepository;

import java.util.List;

public class DetailViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> idProduct = new MutableLiveData<Integer>();
    private DetailRepository detailRepository;
    public MutableLiveData<Integer> getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(MutableLiveData<Integer> idProduct) {
        this.idProduct = idProduct;
    }

    public DetailViewModel(@NonNull Application application) {
        super(application);
        idProduct.postValue(0);
        detailRepository = new DetailRepository(application);
    }

    public void addCart(){
        detailRepository.addCart(this.idProduct.getValue());
        Toast.makeText(getApplication().getApplicationContext(), "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
    }
}
