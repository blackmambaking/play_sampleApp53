package com.example.sampleapp53.GeneratingQuestions;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp53.R;
import com.example.sampleapp53.myDBHelper;

import java.util.ArrayList;

public class MainActivityForCaptures extends AppCompatActivity {
    public static TextView question;
    public static TextView answer1;

    static TextView XP;

    public static VideoView gameVideo;

    public static ArrayList<String> questions;
    public static ArrayList <String> answers ;
    public static  ArrayList <String> wrongAnswers ;
    public static TextView timer;

    ImageView health;
    com.example.sampleapp53.myDBHelper myDBHelper;

    public static int j =0;

    TextView compliments;
    myDBHelper helper;
    SQLiteDatabase database;
    SQLiteDatabase db;
    Cursor cursor;


    int i = 0;
    int startingCOunter = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_captures);

        health= findViewById(R.id.imageView5);
        question = findViewById(R.id.textView31);

        gameVideo = findViewById(R.id.videoView2);
//        XP = findViewById(R.id.XPovo);
//        XP.setTextColor(getResources().getColor(R.color.gold));
        question.bringToFront();
//        answer1.bringToFront();
//        XP.bringToFront();
        gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.mariarun);
        gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        questions = new ArrayList<>();
        answers = new ArrayList<>();
        wrongAnswers = new ArrayList<>();

        helper = new myDBHelper(this);
        database = helper.getReadableDatabase();
        cursor = database.rawQuery("SELECT BLOCK_NAME, SUB_BLOCK, KEYWORDS FROM CAPTURE_INFO WHERE BLOCK_NAME = " + "'" + Scanning.blockName.getText().toString() + "'", new String[]{});
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            for(int i =0; i< cursor.getCount(); i++){
                String question = cursor.getString(1);
                String keyWord = cursor.getString(2);
                question.replace(keyWord, "_____");
                questions.add(question);
                answers.add(keyWord);
                if(cursor.isLast()){
                    Log.d("capture", questions.get(0));

                }else{
                    cursor.moveToNext();
                }
            }
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}