package com.example.myapplication.View.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.CategoryAdapter;
import com.example.myapplication.Adapter.PhotoViewPagerAdapter;
import com.example.myapplication.Adapter.ProductAdapter;
import com.example.myapplication.Database.DatabaseProduct;
import com.example.myapplication.Model.Category;
import com.example.myapplication.Model.Image;
import com.example.myapplication.Model.Product;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.Home.DataViewModel;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {

    private RecyclerView categoryRecyclerView;
    private RecyclerView productRecyclerView;
    private List<Category> categoryList ;
    private DataViewModel dataViewModel;
    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    private List<Image> mListPhoto;
    private DatabaseProduct databaseProduct;
    private Handler mHandler = new Handler();

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mViewPager.getCurrentItem() == mListPhoto.size() - 1) {
                mViewPager.setCurrentItem( 0 );
            } else {
                mViewPager.setCurrentItem( mViewPager.getCurrentItem() + 1 );
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TAG","OnCreateView: " + getActivity());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TAG","OnCreatedView: " + getActivity());
        dataViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        databaseProduct = new DatabaseProduct(requireActivity());
        // Mapping
        categoryRecyclerView = view.findViewById(R.id.recycerViewCategory);
        productRecyclerView = view.findViewById(R.id.recycerViewFoodDelicious);
        mViewPager = view.findViewById( R.id.viewPager);
        mCircleIndicator = view.findViewById( R.id.circle_indicator );
        // Set Data
        setViewPager();
        setDataCategory();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        productRecyclerView.setLayoutManager(gridLayoutManager);



    }
    private void setDataCategory(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        dataViewModel.getDataCategory();
        dataViewModel.getCategoryMutableLiveData().observe(requireActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryList = categories;
                categoryRecyclerView.setAdapter(new CategoryAdapter(categoryList));
                for (Category category : categoryList){
                    List<Product> productList = databaseProduct.getProductByCategory(category.getId());
                    for (Product product : productList){
                        Log.d("APPDATA", "id: " + product.getId() + " name: " + product.getName() + " price: " + product.getPrice() + " category: " + product.getIdCategory() + " favorite: " + product.getFavourite());
                    }
                }
            }
        });
    }
    private List<Image> setListProduct(){
        List<Image> list = new ArrayList<>();
        list.add(new Image(R.drawable.category_garan));
        list.add(new Image(R.drawable.category_garan));
        list.add(new Image(R.drawable.category_garan));
        list.add(new Image(R.drawable.category_garan));
        list.add(new Image(R.drawable.category_garan));
        return list;
    }
    private void setViewPager(){
        mListPhoto = setListProduct();
        PhotoViewPagerAdapter adapter = new PhotoViewPagerAdapter( mListPhoto );
        mViewPager.setAdapter( adapter );
        mCircleIndicator.setViewPager( mViewPager );
        mHandler.postDelayed( mRunnable, 2000 );
        mViewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            mHandler.removeCallbacks( mRunnable );
                mHandler.postDelayed( mRunnable, 3000 );
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        } );
    }
}