package com.example.sisteminformasiperpustakaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        int loadingTime = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Splash = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(Splash);
                finish();
            }
        },loadingTime);
    }
}