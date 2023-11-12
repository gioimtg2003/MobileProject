package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.Model.Image;
import com.example.myapplication.R;

import java.util.List;

public class PhotoViewPagerAdapter extends PagerAdapter {
    private List<Image> imgList;

    public PhotoViewPagerAdapter(List<Image> imgList) {
        this.imgList = imgList;
    }

    @Override
    public int getCount() {
        return imgList != null ? imgList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from( container.getContext() ).inflate( R.layout.item_image_slide, container, false );
        ImageView imgPhoto = view.findViewById( R.id.img_photo );
        Image photo = imgList.get( position );
        imgPhoto.setImageResource( photo.getResourceID() );
        container.addView( view );

        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView( (View) object);
    }
}
