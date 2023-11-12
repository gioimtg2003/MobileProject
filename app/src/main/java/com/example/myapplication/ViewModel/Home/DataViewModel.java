package com.example.myapplication.ViewModel.Home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.Category;
import com.example.myapplication.NetworkApi.Home.IResponse;
import com.example.myapplication.NetworkApi.Home.ResponseCategory;
import com.example.myapplication.Repository.DataRepository;

import java.util.List;

public class DataViewModel extends ViewModel {
    private MutableLiveData<List<Category>> categoryMutableLiveData = new MutableLiveData<List<Category>>();
    private Category category;
    private DataRepository dataRepository;

    public DataViewModel() {
        dataRepository = new DataRepository();

    }

    public MutableLiveData<List<Category>> getCategoryMutableLiveData() {
        return categoryMutableLiveData;
    }

    public void setCategoryMutableLiveData(MutableLiveData<List<Category>> categoryMutableLiveData) {
        this.categoryMutableLiveData = categoryMutableLiveData;
    }

    public void getDataCategory(){
        dataRepository.getData(new IResponse() {
            @Override
            public void onResponseGetData(ResponseCategory responseCategory) {
                if(responseCategory.getCode() == 200){
                    categoryMutableLiveData.postValue(responseCategory.getData());
                    Log.d("HOME", "Lấy dữ lieệu oke");

                }
                Log.d("HOME", String.valueOf(responseCategory.getCode()));
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("HOME",t.getMessage());
            }
        });
    }

}
