package com.bahmanexample.lezzat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.bahmanexample.lezzat.Adapter.BestFoodsAdapter;
import com.bahmanexample.lezzat.Adapter.CategoryAdapter;
import com.bahmanexample.lezzat.Domain.Category;
import com.bahmanexample.lezzat.Domain.Foods;
import com.bahmanexample.lezzat.Domain.Location;
import com.bahmanexample.lezzat.Domain.Price;
import com.bahmanexample.lezzat.Domain.Time;
import com.bahmanexample.lezzat.R;
import com.bahmanexample.lezzat.databinding.ActivityMainBinding;
import com.bahmanexample.lezzat.viewModel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
   MyViewModel viewModel;
   Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Initialize ViewModel
        viewModel = new ViewModelProvider(MainActivity.this).get(MyViewModel.class);
        //Location
        initiateLocation();
        //Time
        initiateTime();
        //Price
        initiatePrice();
        //BestFoods
        initiateFoods();
        //Category
        initiateCategory();
        //sub parts like sign out, search etc.
        setVariable();
    }

    private void setVariable() {
        binding.logoutBtn.setOnClickListener(v ->{
          viewModel.signOut();
            startActivity(new  Intent(MainActivity.this, LoginActivity.class));
        });
        binding.searchBtn.setOnClickListener(view -> {
            String text = binding.searchEdt.getText().toString();
            if (!text.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, ListFoodActivity.class);
                intent.putExtra("text", text);
                intent.putExtra("isSearch", true);
                startActivity(intent);
                binding.searchEdt.setText("");
            }
        });
        binding.cartBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
    }

    private void initiateLocation(){
        List<Location> locationList = new ArrayList<>();
        viewModel.getMyLocationLivedata().observe(MainActivity.this, new Observer<ArrayList<Location>>() {
            @Override
            public void onChanged(ArrayList<Location> locations) {
                locationList.clear();
                for (Location l: locations){
                    locationList.add(l);
                }
                ArrayAdapter<Location> locationArrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_items
                ,locationList);
                locationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                locationArrayAdapter.notifyDataSetChanged();
                binding.locationsp.setAdapter(locationArrayAdapter);
            }
        });
            }
    private void initiateTime(){
        List<Time> timeList = new ArrayList<>();
        viewModel.getMyTimeLivedata().observe(MainActivity.this, new Observer<ArrayList<Time>>() {
            @Override
            public void onChanged(ArrayList<Time> times) {
               timeList.clear();
                for (Time t: times){
                    timeList.add(t);
                }
                ArrayAdapter<Time> timeArrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_items
                        ,timeList);
                timeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                timeArrayAdapter.notifyDataSetChanged();
                binding.timesp.setAdapter(timeArrayAdapter);
            }
        });
    }
    private void initiatePrice(){
        List<Price> priceList = new ArrayList<>();
        viewModel.getMyPriceLivedata().observe(MainActivity.this, new Observer<ArrayList<Price>>() {
            @Override
            public void onChanged(ArrayList<Price> prices) {
                priceList.clear();
                for (Price p: prices){
                    priceList.add(p);
                }
                ArrayAdapter<Price> priceArrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_items
                        ,priceList);
                priceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                priceArrayAdapter.notifyDataSetChanged();
                binding.pricesp.setAdapter(priceArrayAdapter);
            }
        });
    }
    private void initiateFoods(){
        binding.progressBarBestFood.setVisibility(View.VISIBLE);
        ArrayList<Foods> foodsList = new ArrayList<>();
        viewModel.getMyFoodsLivedata().observe(MainActivity.this, new Observer<ArrayList<Foods>>() {
            @Override
            public void onChanged(ArrayList<Foods> foods) {
                foodsList.clear();

                for (Foods f : foods) {
                    foodsList.add(f);
                }
                if (foodsList.size() > 0) {
                    binding.recyclerViewBestFood.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    RecyclerView.Adapter adapter = new BestFoodsAdapter( foodsList);
                    adapter.notifyDataSetChanged();
                    binding.recyclerViewBestFood.setAdapter(adapter);

                }
                binding.progressBarBestFood.setVisibility(View.GONE);
            }
        });
    }
    private void initiateCategory() {
        binding.progressBarCathegory.setVisibility(View.VISIBLE);
        ArrayList<Category> categoryList = new ArrayList<>();
        viewModel.getMyCategoryLivedata().observe(MainActivity.this, new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(ArrayList<Category> categorys) {
                categoryList.clear();

                for (Category g : categorys) {
                    categoryList.add(g);
                }
                if (categoryList.size() > 0) {
                    binding.cathegoryRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
                    RecyclerView.Adapter adapter = new CategoryAdapter(categoryList);
                    adapter.notifyDataSetChanged();
                    binding.cathegoryRecyclerView.setAdapter(adapter);

                }
                binding.progressBarCathegory.setVisibility(View.GONE);
            }
        });
    }
}