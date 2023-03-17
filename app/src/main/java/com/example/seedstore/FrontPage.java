package com.example.seedstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.seedstore.databinding.ActivityFrontPageBinding;

public class FrontPage extends AppCompatActivity {

    ActivityFrontPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFrontPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.frontloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FrontPage.this, Signin.class);
                startActivity(intent);
            }
        });

        binding.frontregisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FrontPage.this,Signup.class);
                startActivity(intent);
            }
        });
    }
}