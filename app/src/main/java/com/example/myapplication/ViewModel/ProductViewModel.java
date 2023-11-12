package com.example.myapplication.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Model.Product;
import com.example.myapplication.Repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository productRepository;
    private MutableLiveData<List<Product>> listProductMutableLiveData = new MutableLiveData<List<Product>>();

    private MutableLiveData<Boolean> favouriteProductMutableLiveData = new MutableLiveData<Boolean>();
    public ProductViewModel(@NonNull Application application) {
        super(application);
        this.productRepository = new ProductRepository(application);
    }
    public void fetchDataSQLite(int idCategory){
        productRepository.fetchDataSQLite(idCategory, new ProductRepository.IFetchDataCallBack() {
            @Override
            public void onResponseGetData(List<Product> productList) {
                listProductMutableLiveData.postValue(productList);
            }
        });
    }
    public void addFavouriteProduct(int idProduct){
        productRepository.addFavouriteProduct(idProduct);

    }
    public void deleteFavouriteProduct(int idProduct){
        productRepository.deleteFavouriteProduct(idProduct);

        Log.d("APPDATA", "deleteFavouriteProduct: " + String.valueOf(listProductMutableLiveData.getValue().size()));
    }
    public MutableLiveData<Boolean> getFavouriteProductMutableLiveData() {
        return favouriteProductMutableLiveData;
    }

    public void setFavouriteProductMutableLiveData(MutableLiveData<Boolean> favouriteProductMutableLiveData) {
        this.favouriteProductMutableLiveData = favouriteProductMutableLiveData;

    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public MutableLiveData<List<Product>> getListProductMutableLiveData() {
        return listProductMutableLiveData;
    }

    public void setListProductMutableLiveData(MutableLiveData<List<Product>> listProductMutableLiveData) {
        this.listProductMutableLiveData = listProductMutableLiveData;
    }
}
