package com.example.sampleapp53.oneVone;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapterForSquadRoom;
import com.example.sampleapp53.adapterForCardView.adapterForOneVOneResultQuestions;

public class resultForOneVOne extends AppCompatActivity {
    adapterForOneVOneResultQuestions adapterForOneVOneResultQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_for_one_vone);
        adapterForOneVOneResultQuestions = new adapterForOneVOneResultQuestions(MainActivityForOneVOne.questions);
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapterForOneVOneResultQuestions);
    }
}