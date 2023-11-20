package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Intent intent = getIntent();
        getDataFromIntent(intent);
        mappingId();
        setDataFormIntentToView();

    }

    private void getDataFromIntent(Intent i){
        this._id = i.getStringExtra("_id");
        this.id = i.getIntExtra("id", 0);
        this.name = i.getStringExtra("name");
        this.price = i.getIntExtra("price", 0);
        this.description = i.getStringExtra("description");
        this.imageUrl = i.getStringExtra("imageUrl");
    }
    private void mappingId(){
        this.materialToolbar = this.findViewById(R.id.materialToolbar2);
        this.nameProduct = this.findViewById(R.id.nameProductDetail);
        this.priceProduct = this.findViewById(R.id.priceProductDetail);
        this.descriptionDetail = this.findViewById(R.id.descriptionDetail);
        this.inputComment = this.findViewById(R.id.InputComment);
        this.btnAddCart = this.findViewById(R.id.btnAddCart);
        this.recyclerView = this.findViewById(R.id.rcvComment);
    }
    private void setDataFormIntentToView(){
        this.materialToolbar.setTitle(this.name);
        this.nameProduct.setText(this.name);
        this.priceProduct.setText(String.valueOf(this.price));
        this.descriptionDetail.setText(this.description);
    }

}