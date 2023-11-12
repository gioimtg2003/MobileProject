package com.example.myapplication.ViewModel.Home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Model.Category;
import com.example.myapplication.Model.Product;
import com.example.myapplication.Repository.DataRepository;

import java.util.List;

public class DataViewModel extends AndroidViewModel {
    private MutableLiveData<List<Category>> categoryMutableLiveData = new MutableLiveData<List<Category>>();
    private DataRepository dataRepository;

    public DataViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
    }


    public MutableLiveData<List<Category>> getCategoryMutableLiveData() {
        return categoryMutableLiveData;
    }

    public void setCategoryMutableLiveData(MutableLiveData<List<Category>> categoryMutableLiveData) {
        this.categoryMutableLiveData = categoryMutableLiveData;
    }

    public void getDataCategory(){
        dataRepository.handleData(new DataRepository.IListCategory() {
            @Override
            public void getListCategory(List<Category> listCategory) {
                categoryMutableLiveData.postValue(listCategory);
            }
        });
    }
}
