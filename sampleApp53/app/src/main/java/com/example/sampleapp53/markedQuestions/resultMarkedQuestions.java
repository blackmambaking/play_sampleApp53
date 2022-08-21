package com.example.sampleapp53.markedQuestions;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp53.R;

public class resultMarkedQuestions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_marked_questions);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
    }
}