package com.bahmanexample.lezzat.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bahmanexample.lezzat.R;
import com.bahmanexample.lezzat.databinding.ActivityLoginBinding;
import com.bahmanexample.lezzat.viewModel.MyViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    MyViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(LoginActivity.this).get(MyViewModel.class);

        binding.loginBtn.setOnClickListener(view -> setVariable());
        binding.signUpLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setVariable() {

                String email = binding.userLoginEdt.getText().toString();
                String password = binding.passwordLoginEdt.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()) {
                    viewModel.signInwithemailpassword(email,password);
                }else {
                    Toast.makeText(LoginActivity.this, "Please fill username and password", Toast.LENGTH_SHORT).show();
                }
            }
    }