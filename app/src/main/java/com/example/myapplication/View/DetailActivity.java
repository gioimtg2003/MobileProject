package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ViewModel.DetailViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private String _id;
    private int id;
    private String name;
    private int price;
    private String imageUrl;
    private String description;
    private MaterialToolbar materialToolbar;
    private ImageView imageView;
    private TextView nameProduct;
    private TextView priceProduct;
    private TextView descriptionDetail;
    private TextInputLayout inputComment;
    private Button btnAddCart;
    private RecyclerView recyclerView;
    private DetailViewModel detailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Intent intent = getIntent();
        this.detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        getDataFromIntent(intent);
        mappingId();
        setDataFormIntentToView();
        handleClick();

    }

    private void getDataFromIntent(Intent i){
        this._id = i.getStringExtra("_id");
        this.id = i.getIntExtra("id", 0);
        this.name = i.getStringExtra("name");
        this.price = i.getIntExtra("price", 0);
        this.description = i.getStringExtra("description");
        this.imageUrl = i.getStringExtra("imageUrl");
        // post value to view model
        this.detailViewModel.getIdProduct().postValue(this.id);
    }
    private void mappingId(){
        this.materialToolbar = this.findViewById(R.id.materialToolbar2);
        this.nameProduct = this.findViewById(R.id.nameProductDetail);
        this.priceProduct = this.findViewById(R.id.priceProductDetail);
        this.descriptionDetail = this.findViewById(R.id.descriptionDetail);
        this.inputComment = this.findViewById(R.id.InputComment);
        this.btnAddCart = this.findViewById(R.id.btnAddCart);
        this.recyclerView = this.findViewById(R.id.rcvComment);
        this.imageView = this.findViewById(R.id.imageProductDetail);
    }
    private void setDataFormIntentToView(){
        this.materialToolbar.setTitle(this.name);
        this.nameProduct.setText(this.name);
        this.priceProduct.setText(String.valueOf(this.price));
        this.descriptionDetail.setText(this.description);
        Picasso.get().load(this.imageUrl).into(this.imageView);
    }
    private void handleClick(){
        this.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        this.btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailViewModel.addCart();
            }
        });
    }

}