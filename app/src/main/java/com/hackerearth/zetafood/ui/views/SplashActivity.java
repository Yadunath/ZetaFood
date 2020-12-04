package com.hackerearth.zetafood.ui.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.hackerearth.zetafood.MainActivity;
import com.hackerearth.zetafood.R;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler=new Handler();
        handler.postDelayed(() -> {
            Intent intent=new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        },2000);
    }
}