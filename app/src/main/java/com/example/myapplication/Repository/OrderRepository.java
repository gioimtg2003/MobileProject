package com.example.myapplication.Repository;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.Database.DatabaseProduct;
import com.example.myapplication.Model.Product;
import com.example.myapplication.NetworkApi.Order.BodyOrder;
import com.example.myapplication.NetworkApi.Order.BodyOrderDetail;
import com.example.myapplication.NetworkApi.Order.IOrderService;
import com.example.myapplication.NetworkApi.Order.ResponseOrder;
import com.example.myapplication.NetworkApi.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository {
    private Context context;
    private DatabaseProduct databaseProduct;
    public OrderRepository(Application application) {
        this.context = application.getApplicationContext();
        this.databaseProduct = new DatabaseProduct(context);
    }
    public void getProductInCart(List<Integer> listIdChecked, IDataProduct iGetDataFromSqlite){
        List<Product> productInCart = new ArrayList<Product>();
        for (int i : listIdChecked) {
            Product product = databaseProduct.getProductInCart(i);
            productInCart.add(product);
        }
        iGetDataFromSqlite.ProductList(productInCart);
    }

    public void createOrder(String address, String userId, List<BodyOrderDetail> listOrderDetail, IResponseOrder iResponseOrder){
        IOrderService iOrderService = RetrofitClientInstance.getInstance().create(IOrderService.class);
        Call<ResponseOrder> callOrder = iOrderService.createOrder(new BodyOrder(address, userId, listOrderDetail));
        callOrder.enqueue(new Callback<ResponseOrder>() {
            @Override
            public void onResponse(Call<ResponseOrder> call, Response<ResponseOrder> response) {
                if (response.isSuccessful()){
                    if(response.body() != null){
                        if(response.body().getCode() == 200){
                            iResponseOrder.IResponseOrder(response.body());
                        }else {
                            iResponseOrder.onFailure(new Throwable(response.body().getMessage()));
                        }
                    }
                }else {
                    iResponseOrder.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<ResponseOrder> call, Throwable t) {
                iResponseOrder.onFailure(t);
            }
        });
    }
    public interface IDataProduct{
        void ProductList(List<Product> listProduct);
    }
    public interface IResponseOrder{
        void  IResponseOrder(ResponseOrder response);
        void onFailure(Throwable t);
    }
}
