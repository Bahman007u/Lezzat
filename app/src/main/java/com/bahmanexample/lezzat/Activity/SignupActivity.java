package com.bahmanexample.lezzat.Activity;


import static android.opengl.ETC1.isValid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.bahmanexample.lezzat.R;
import com.bahmanexample.lezzat.databinding.ActivitySignupBinding;
import com.bahmanexample.lezzat.viewModel.MyViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    MyViewModel viewModel;
    private String TAG;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new  ViewModelProvider(SignupActivity.this).get(MyViewModel.class);
        binding.SignupBtn.setOnClickListener(view -> setVariable());
    }
    private void setVariable() {
        String email = binding.userSignupEdt.getText().toString();
        String password = binding.passwordSignupEdt.getText().toString();

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            return; // Return early if email is invalid
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return; // Return early if password is too short
        }

        // If email and password are valid, proceed with sign-up
        viewModel.firebaseSignupWithEmailAndPassword(email, password);
    }


    // Validation
    private boolean isValidEmail(CharSequence target) {
        if (target == null || TextUtils.isEmpty(target)) {
            return false;
        } else {
            String email = target.toString().toLowerCase(); // Convert to lowercase for case-insensitive comparison
            return (Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.endsWith("@gmail.com"));
        }
    }
}