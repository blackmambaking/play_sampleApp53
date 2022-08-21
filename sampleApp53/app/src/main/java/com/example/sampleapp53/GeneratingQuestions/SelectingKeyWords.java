package com.example.sampleapp53.GeneratingQuestions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapterForKeyWords;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SelectingKeyWords extends AppCompatActivity {
public static RecyclerView recyclerView;
public static LinearLayoutManager linearLayoutManager;
FloatingActionButton floatingActionButton;
public static adapterForKeyWords adapterForKeyWords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecting_key_words);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        adapterForKeyWords = new adapterForKeyWords(Scanning.listOfLists);
        floatingActionButton = findViewById(R.id.floatingActionButton4);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivityForCaptures.class);
                startActivity(i);
            }
        });
        recyclerView = findViewById(R.id.keyWordsSelectRecycler);
        recyclerView.setLayoutManager(linearLayoutManager = new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapterForKeyWords);


    }
}