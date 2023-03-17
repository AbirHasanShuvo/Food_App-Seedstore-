package com.example.seedstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.seedstore.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    ActivitySplashScreenBinding binding;
    private int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });
        thread.start();
    }

    public void doWork()
    {

        for (progress = 40;  progress<=100 ; progress+=20)

        {
            try {
                Thread.sleep(1000);
                binding.splashProgressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

    public void startApp()
    {
        Intent intent = new Intent(SplashScreen.this, FrontPage.class);
        startActivity(intent);
    }
}