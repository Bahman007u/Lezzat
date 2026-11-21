package com.bahmanexample.lezzat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.bahmanexample.lezzat.R;
import com.bahmanexample.lezzat.databinding.ActivityIntroBinding;
import com.bahmanexample.lezzat.viewModel.MyViewModel;

public class IntroActivity extends AppCompatActivity {

    ActivityIntroBinding binding;
    MyViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(IntroActivity.this).get(MyViewModel.class);

        binding.Signin.setOnClickListener(view -> startActivity(new Intent(IntroActivity.this, SignupActivity.class)));
        binding.loginbtn.setOnClickListener(view -> setVariable());
        getWindow().setStatusBarColor(Color.parseColor("#FFE4B5"));

    }

    private void setVariable() {
            if (viewModel.getCurrentUser() != null){
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }else{
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            }

    }
}