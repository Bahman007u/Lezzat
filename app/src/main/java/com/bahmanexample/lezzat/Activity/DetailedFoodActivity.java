package com.bahmanexample.lezzat.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bahmanexample.lezzat.Domain.Foods;
import com.bahmanexample.lezzat.Helper.ManagmentCart;
import com.bahmanexample.lezzat.R;
import com.bahmanexample.lezzat.databinding.ActivityDetailedFoodBinding;
import com.bumptech.glide.Glide;

public class DetailedFoodActivity extends AppCompatActivity {
    ActivityDetailedFoodBinding binding;
    private Foods object;
    private int num =1;
    private ManagmentCart managmentCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setStatusBarColor(getResources().getColor(R.color.black));


        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        managmentCart = new ManagmentCart(this);


        binding.BackBtn.setOnClickListener(v->finish());

        Glide.with(DetailedFoodActivity.this)
                .load(object.getImagePath())
                .into(binding.pic);

        binding.priceTxt.setText("$" + object.getPrice());
        binding.titleTxt.setText(object.getTitle());
        binding.descriptionTxt.setText(object.getDescription());
        binding.rateTxt.setText(object.getStar() + "Rating");
        binding.ratingBar.setRating((float) object.getStar());
        binding.totalTxt.setText((num * object.getPrice() + "$"));

        binding.plusBtn.setOnClickListener(view -> {
            num = num+1;
            binding.numTxt.setText(num+" ");
            binding.totalTxt.setText("$"+(num * object.getPrice()));
        });
        binding.minusBtn.setOnClickListener(view -> {
            num = num-1;
            binding.numTxt.setText(num+"");
            binding.totalTxt.setText("$"+(num * object.getPrice()));
        });
        binding.addBtn.setOnClickListener(v ->{
            object.setNumberInCart(num);
            managmentCart.insertFood(object);
        });
    }

    private void getIntentExtra() {
        object = (Foods) getIntent().getSerializableExtra("object");
    }
}