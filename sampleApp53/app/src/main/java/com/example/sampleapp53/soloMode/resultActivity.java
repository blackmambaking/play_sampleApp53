package com.example.sampleapp53.soloMode;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapter;
import com.example.sampleapp53.adapterForCardView.adapterForChapterSelection;
import com.example.sampleapp53.basicLayout.ChapterSelection;
import com.example.sampleapp53.basicLayout.dashboard;
import com.example.sampleapp53.myDBHelper;

import java.util.ArrayList;

public class resultActivity extends AppCompatActivity {
TextView right_ans_sc;
TextView wrong_ans_sc;
TextView retryFromCheckpoint;
TextView coins;
TextView timer;
int watch = 5;

public static TextView sco;
TextView home;
    private MainActivity activity = null;
    public MainActivity getActivity(){
        return activity;
    }

myDBHelper helper;
public static ArrayList <String> pq;
Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        helper = new myDBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        SQLiteDatabase database = helper.getReadableDatabase();
        timer = findViewById(R.id.timerToExitResult);

        new CountDownTimer(5000, 1000) {
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

        database.execSQL("delete from "+ myDBHelper.TABLE_NAME_MARKEDQUESTIONS);
//        retryFromCheckpoint = findViewById(R.id.retryFromCheck);
//        retryFromCheckpoint.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                retryFromCheckpoint.setBackgroundColor(getResources().getColor(R.color.lightGrey));
//                new CountDownTimer(2000, 1000) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        retryFromCheckpoint.setBackgroundColor(80000000);
//                    }
//
//                    @Override
//                    public void onFinish() {
//
//                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(i);
//                    }
//                }.start();
//            }
//        });

        coins = findViewById(R.id.coinsText);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        right_ans_sc = findViewById(R.id.r);
        wrong_ans_sc = findViewById(R.id.w);
        sco = findViewById(R.id.s);
//        home = findViewById(R.id.button);
        int wrongAnsScore = MainActivity.wrong_ans_score;
        int rightAnsScore = MainActivity.right_ans_score;
        int scor = MainActivity.score;
        coins.setText(rightAnsScore + "");
        wrong_ans_sc.setText("" + wrongAnsScore);
        right_ans_sc.setText("" + rightAnsScore);
        sco.setText("" + scor);


        cursor = database.rawQuery("SELECT COINS FROM COINS_AND_XP", new String[]{});
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            helper.updateCoinsAndXp(rightAnsScore, scor);
        }
        else{
            helper.insertCoinsAndXP(rightAnsScore, scor);
        }

        pq = new ArrayList<>();
        int nm = ChapterSelection.cursor3.getCount();
        if(nm!=0){
            ChapterSelection.cursor3.moveToFirst();
            for(int i =0; i < ChapterSelection.cursor3.getCount();i++)
            {

                pq.add(ChapterSelection.cursor3.getString(1));
                if(ChapterSelection.cursor3.isLast()){

                }
                else {
                    ChapterSelection.cursor3.moveToNext();
                }
            }
        }
        if(ChapterSelection.cursor3.getCount()!=0){
            ChapterSelection.cursor3.moveToFirst();
            for(int i = 0; i < ChapterSelection.cursor3.getCount();i++){

                if(adapterForChapterSelection.finalChapterSelected.equals(ChapterSelection.cursor3.getString(1))){
                    scor = scor + ChapterSelection.cursor3.getInt(2);
                    if(ChapterSelection.cursor3.isLast()){

                    }
                    else{
                        ChapterSelection.cursor3.moveToNext();
                    }
                }
                if(ChapterSelection.cursor3.isLast()){

                }
                else{
                    ChapterSelection.cursor3.moveToNext();
                }
            }
        }
            if(pq.contains(adapterForChapterSelection.finalChapterSelected)){
                helper.updateChapterScore(adapter.book_selected_byUser,adapterForChapterSelection.finalChapterSelected,scor);
            }
            else{
                helper.addChapterScore(adapter.book_selected_byUser, adapterForChapterSelection.finalChapterSelected, scor);
            }
        MainActivity.wrong_ans_score = 0;
        MainActivity.right_ans_score=0;
        MainActivity.score = 0;

//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent u = new Intent(getApplicationContext(), dashboard.class);
//                startActivity(u);
//            }
//        });


    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}