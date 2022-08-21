package com.example.sampleapp53.squadMode;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.squadLeaderBoardAdapter;
import com.example.sampleapp53.basicLayout.dashboard;

public class result_squad_mode extends AppCompatActivity {
    RecyclerView recyclerView;
    squadLeaderBoardAdapter squadLeaderBoardAdapter;
    TextView timer;
    int watch = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_squad_mode);
        timer = findViewById(R.id.timerToExitSquadResult);
        squadLeaderBoardAdapter = new squadLeaderBoardAdapter(MainActivityForSqadPlay.helper);
        recyclerView = findViewById(R.id.squadResult);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(squadLeaderBoardAdapter);
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(watch + "" );
                watch = watch -1;
            }

            @Override
            public void onFinish() {
                Intent i = new Intent(getApplicationContext(), dashboard.class);
                startActivity(i);
            }
        }.start();
    }
}