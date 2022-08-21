package com.example.sampleapp53.basicLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp53.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                Intent i = new Intent(getApplicationContext(), loginActivity.class);
                startActivity(i);

            }
        }.start();
    }

}