package com.bahmanexample.lezzat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.bahmanexample.lezzat.Adapter.FoodListAdapter;
import com.bahmanexample.lezzat.Domain.Foods;
import com.bahmanexample.lezzat.R;
import com.bahmanexample.lezzat.databinding.ActivityListFoodBinding;
import com.bahmanexample.lezzat.databinding.ActivityMainBinding;
import com.bahmanexample.lezzat.viewModel.MyViewModel;

import java.util.ArrayList;

public class ListFoodActivity extends AppCompatActivity {
    ActivityListFoodBinding binding;

    private RecyclerView.Adapter adapterListFood;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean isSearch;
    MyViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(ListFoodActivity.this).get(MyViewModel.class);

        getIntentExtra();
        initList();
    }

    private void initList() {
        binding.progressBarFoodlist.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<Foods>();

        viewModel.getMyListsLiveData(isSearch,searchText,categoryId).observe(ListFoodActivity.this, new Observer<ArrayList<Foods>>() {
            @Override
            public void onChanged(ArrayList<Foods> foods) {
                list.clear();
                for (Foods f : foods) {
                    list.add(f);
                }
                if (list.size() > 0) {

                    binding.foodListRecycleView.setLayoutManager(new GridLayoutManager(ListFoodActivity.this, 2));
                    adapterListFood = new FoodListAdapter(list);
                    adapterListFood.notifyDataSetChanged();
                    binding.foodListRecycleView.setAdapter(adapterListFood);
                }
                binding.progressBarFoodlist.setVisibility(View.GONE);
            }

        });
    }

    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId", 0);
        categoryName = getIntent().getStringExtra("CategoryName");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch", false);

        binding.titleTxt.setText(categoryName);
        binding.backBtn.setOnClickListener(v -> finish());
    }
}