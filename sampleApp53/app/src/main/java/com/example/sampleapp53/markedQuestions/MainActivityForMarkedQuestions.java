package com.example.sampleapp53.markedQuestions;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapterForMarkedQuestions;
import com.example.sampleapp53.adapterForCardView.adapter_quick_question;
import com.example.sampleapp53.myDBHelper;
import com.example.sampleapp53.soloMode.resultActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class MainActivityForMarkedQuestions extends AppCompatActivity {
    public static TextView question;
    public static TextView answer1;
    public static TextView answer2;
    static TextView XP;
    public static TextView Coins;
    ArrayList<String> questionsStorer;
    ArrayList <String>answerStorer;
    ArrayList <String>wrongAnsStorer;
    myDBHelper helper;
    SQLiteDatabase db;
    public static RecyclerView recyclerView;
    public static VideoView gameVideo;
    public static Cursor cursor;

    SQLiteDatabase database;
    public FirebaseFirestore firedata;
    ArrayList <String> playerNames;
    Map<String, Object> m;
    GestureDetectorCompat gestureDetectorCompat = null;

    public static ArrayList <String> questions;
    public static ArrayList <String> answers ;
    public static  ArrayList <String> wrongAnswers ;
    Map<String, Object> docData;
    ArrayList<String> quick_questions;
    adapter_quick_question adapter_quick_question;
    com.example.sampleapp53.adapterForCardView.squadLeaderBoardAdapter squadLeaderBoardAdapter;
    public static int j = 0;
    ImageView health;
    @Override
    protected void onDestroy() {
        j = 0;
        super.onDestroy();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_marked_questions);

        health= findViewById(R.id.healthpqr);
        question = findViewById(R.id.questionpqr);
        answer1 = findViewById(R.id.answer1pqr);
        answer2 = findViewById(R.id.answer2pqr);
        gameVideo = findViewById(R.id.videoViewpqr);
        quick_questions = new ArrayList<>();

        playerNames = new ArrayList<>();
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        Coins = findViewById(R.id.Coinspqr);
        XP = findViewById(R.id.XPpqr);
        Coins.setTextColor(getResources().getColor(R.color.gold));
        XP.setTextColor(getResources().getColor(R.color.gold));

        question.bringToFront();
        answer1.bringToFront();
        answer2.bringToFront();
        XP.bringToFront();
        Coins.bringToFront();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        questionsStorer = new ArrayList<>();
        answerStorer= new ArrayList<>();
        wrongAnsStorer = new ArrayList<>();
        firedata  = FirebaseFirestore.getInstance();

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

        detectSwipeMarkedQuestions gestureListener = new detectSwipeMarkedQuestions();
        gestureListener.setActivity(this);
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        helper = new myDBHelper(this);
        db = helper.getReadableDatabase();

        cursor = db.rawQuery("SELECT QUESTION, ANSWER, WRONG_ANSWER FROM MARKED_QUESTIONS_ABSOLUTE WHERE BOOK_NAME = " + "'"+adapterForMarkedQuestions.bookMarkedQuestions+"'" + " AND CHAPTER_NAME = " + "'"+adapterForMarkedQuestions.chapterMarkedQuestions+"'" , new String[]{});
        cursor.moveToFirst();
        if(cursor.getCount()!=0){
               for(int i =0; i<= cursor.getCount();i++){
            questions.add(cursor.getString(0));
            answers.add(cursor.getString(1));
            wrongAnswers.add(cursor.getString(2));
            if(cursor.isLast()){

            }else{
                cursor.moveToNext();
            }

        }
//            quick_questions.addAll(questions);
//            adapter_quick_question = new adapter_quick_question(quick_questions);
//            recyclerView = findViewById(R.id.questionNavigatorpqr);
//            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//            recyclerView.setAdapter(adapter_quick_question);
//            recyclerView.bringToFront();
//            recyclerView.setTranslationX(-2000);
            answer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if  (MainActivityForMarkedQuestions.j < MainActivityForMarkedQuestions.questions.size()){
                        displayMessage(0);

                    }
                    else if (MainActivityForMarkedQuestions.j >= MainActivityForMarkedQuestions.questions.size()){
                        displayMessage2(0);
                    }
                }
            });
            answer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( MainActivityForMarkedQuestions.j < MainActivityForMarkedQuestions.questions.size()){
                        displayMessage(1);
                    }
                    else if( MainActivityForMarkedQuestions.j >= MainActivityForMarkedQuestions.questions.size()){
                        displayMessage2(1);
                    }
                }
            });
        }

    }
      @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);

        return true;
    }
            //quiz logic

    int life = 3;

    static int score = 0;
    static int right_ans_score = 0;
    static int wrong_ans_score = 0;

    static int intrinsic_mcc_answered_questions = 0;
    Random rn = new Random();
    public void displayMessage(int message){

        if(message == 0){
            if(answer1.getText().toString().equals("A. "+answers.get(j)) ){
                right_ans_score++;
                score = score + 1000;
                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        answer1.setBackgroundColor(getResources().getColor(R.color.lightBlue));
                    }
                    @Override
                    public void onFinish() {
                        j++;
                        if(j==questions.size()){
                           // Toast.makeText(this, "Round 2", Toast.LENGTH_SHORT).show();
                            cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});

                            if(cursor.getCount()==0){
                                Intent z = new Intent(getApplicationContext(), resultActivity.class);
                                startActivity(z);
                            }
                            else{
                                int correct_answer = rn.nextInt(2);

                                if(correct_answer ==0 ){
                                    cursor.moveToFirst();
                                    question.setText(questions.get(cursor.getInt(1)));
                                    answer2.setText("B. "+answers.get(cursor.getInt(1)));
                                    answer1.setText("A. "+wrongAnswers.get(cursor.getInt(1)));

                                }
                                else{
                                    cursor.moveToFirst();
                                    question.setText(questions.get(cursor.getInt(1)));
                                    answer1.setText("A. "+answers.get(cursor.getInt(1)));
                                    answer2.setText("B. "+wrongAnswers.get(cursor.getInt(1)));

                                }
                            }


                        }
                        else if(j<questions.size()){

                            int correct_answer = rn.nextInt(2);

                            if(correct_answer ==0 ){
                                question.setText(questions.get(j));
                                answer2.setText("B. "+answers.get(j));
                                answer1.setText("A. "+wrongAnswers.get(j));
                            }
                            else{
                                question.setText(questions.get(j));
                                answer1.setText("A. "+answers.get(j));
                                answer2.setText("B. "+wrongAnswers.get(j));
                            }

                        }
                        answer1.setBackgroundColor(80000000);
                        XP.setText("XP: " +score);
                        Coins.setText("Coins: " + right_ans_score + "");
                    }
                }.start();

            }
            else{
                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        answer1.setBackgroundColor(getResources().getColor(R.color.red));

                    }

                    @Override
                    public void onFinish() {
                        wrong_ans_score++;

                        score = score - 300;
                        life = life -1;
                        if(life == 2){
                            health.setImageResource(R.drawable.halffinal2);
                        }
                        else if (life == 1 ){
                            health.setImageResource(R.drawable.lowhealthfinal2);
                        }
                        else if(life == 0){
                           // Toast.makeText(this, "you died", Toast.LENGTH_SHORT).show();
                            Intent z = new Intent(getApplicationContext(), resultActivity.class);
                            startActivity(z);
                        }
                        answer1.setBackgroundColor(80000000);
                        XP.setText("XP: " +score);
                        Coins.setText("Coins: " + right_ans_score + "");
                    }
                }.start();

            }
        }
        else if(message ==1){

            if(answer2.getText().toString().equals("B. "+answers.get(j))){
                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        answer2.setBackgroundColor(getResources().getColor(R.color.lightBlue));

                    }

                    @Override
                    public void onFinish() {
                        j++;
                        right_ans_score++;
                        score = score + 1000;
                        if(j==questions.size()){
                            cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});

                            if(cursor.getCount()==0){
                                Intent z = new Intent(getApplicationContext(), resultActivity.class);
                                startActivity(z);
                            }
                            else{
                               // Toast.makeText(this, "Round 2!", Toast.LENGTH_SHORT).show();
                                int correct_answer = rn.nextInt(2);

                                if(correct_answer ==0 ){
                                    cursor.moveToFirst();

                                    question.setText(questions.get(cursor.getInt(1)));
                                    answer2.setText("B. "+answers.get(cursor.getInt(1)));
                                    answer1.setText("A. "+wrongAnswers.get(cursor.getInt(1)));

                                }
                                else{
                                    cursor.moveToFirst();
                                    question.setText(questions.get(cursor.getInt(1)));
                                    answer1.setText("A. "+answers.get(cursor.getInt(1)));
                                    answer2.setText("B. "+wrongAnswers.get(cursor.getInt(1)));

                                }
                            }

                        }
                        else if(j<questions.size()){
                            int correct_answer = rn.nextInt(2);

                            if(correct_answer ==0 ){
                                question.setText(questions.get(j));
                                answer2.setText("B. "+answers.get(j));
                                answer1.setText("A. "+wrongAnswers.get(j));
                            }
                            else{
                                question.setText(questions.get(j));
                                answer1.setText("A. "+answers.get(j));
                                answer2.setText("B. "+wrongAnswers.get(j));
                            }

                        }
                        answer2.setBackgroundColor(80000000);
                        XP.setText("XP: " +score);
                        Coins.setText("Coins: " + right_ans_score + "");
                    }
                }.start();

            }
            else{
                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        answer2.setBackgroundColor(getResources().getColor(R.color.red));

                    }

                    @Override
                    public void onFinish() {
                        wrong_ans_score++;
                        score = score - 300;
                        life = life -1;
                        if(life == 2){
                            health.setImageResource(R.drawable.halffinal2);
                        }
                        else if (life == 1 ){
                            health.setImageResource(R.drawable.lowhealthfinal2);
                        }
                        else if(life == 0){
                          //  Toast.makeText(this, "you died", Toast.LENGTH_SHORT).show();
                            Intent z = new Intent(getApplicationContext(), resultActivity.class);
                            startActivity(z);
                        }
                        answer2.setBackgroundColor(80000000);
                        XP.setText("XP: " +score);
                        Coins.setText("Coins: " + right_ans_score + "");
                    }
                }.start();


            }

        }

    }
    public void displayMessage2(int message){


        if(message == 0){

            if(answer1.getText().toString().equals("A. "+answers.get(cursor.getInt(1)))){
                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        answer1.setBackgroundColor(getResources().getColor(R.color.lightBlue));

                    }

                    @Override
                    public void onFinish() {
                        right_ans_score++;
                        score = score + 1000;
                        int correct_answer = rn.nextInt(2);

                        if(correct_answer ==0 ){
                            if(cursor.isLast()){
                                int p = cursor.getCount();
                              //  Toast.makeText(this, "Get ready for next round!", Toast.LENGTH_SHORT).show();
                                cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});
                                if(cursor.getCount()==p){
                                    Intent i = new Intent(getApplicationContext(), resultActivity.class);
                                    startActivity(i);
                                }
                                else{
                                    cursor.moveToPosition(p-1);
                                }
                            }else{
                                cursor.moveToNext();
                                question.setText(questions.get(cursor.getInt(1)));
                                answer2.setText("B. "+answers.get(cursor.getInt(1)));
                                answer1.setText("A. "+wrongAnswers.get(cursor.getInt(1)));
                            }
                        }
                        else{
                            if(cursor.isLast()){
                                int p = cursor.getCount();
                                //Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
                                cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});
                                if(cursor.getCount()==p){
                                    Intent i = new Intent(getApplicationContext(), resultActivity.class);
                                    startActivity(i);
                                }
                                else{
                                    cursor.moveToPosition(p-1);
                                }

                            }else{
                                cursor.moveToNext();
                                question.setText(questions.get(cursor.getInt(1)));
                                answer1.setText("A. "+answers.get(cursor.getInt(1)));
                                answer2.setText("B. "+wrongAnswers.get(cursor.getInt(1)));
                            }
                        }
                        answer1.setBackgroundColor(80000000);
                        XP.setText("XP: " +score);
                        Coins.setText("Coins: " + right_ans_score + "");
                    }
                }.start();

            }
            else{
                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        answer1.setBackgroundColor(getResources().getColor(R.color.red));

                    }

                    @Override
                    public void onFinish() {

                        wrong_ans_score++;
                        score = score - 300;
                        life = life -1;
                        if(life == 2){
                            health.setImageResource(R.drawable.halffinal2);
                        }
                        else if (life == 1 ){
                            health.setImageResource(R.drawable.lowhealthfinal2);
                        }
                        else if(life == 0){
                         //   Toast.makeText(this, "you died", Toast.LENGTH_SHORT).show();
                            Intent z = new Intent(getApplicationContext(), resultActivity.class);
                            startActivity(z);
                        }
                        answer1.setBackgroundColor(80000000);
                        XP.setText("XP: " +score);
                        Coins.setText("Coins: " + right_ans_score + "");
                    }
                }.start();

            }
        }
        else if(message == 1){

            if(answer2.getText().toString().equals("B. "+answers.get(cursor.getInt(1)))){
                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        answer2.setBackgroundColor(getResources().getColor(R.color.lightBlue));

                    }

                    @Override
                    public void onFinish() {
                        right_ans_score++;
                        score = score + 1000;



                        int correct_answer = rn.nextInt(2);

                        if(correct_answer ==0 ){
                            if(cursor.isLast()){
                                int p = cursor.getCount();
                          //      Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
                                cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});
                                if(cursor.getCount()==p){
                                    Intent i = new Intent(getApplicationContext(), resultActivity.class);
                                    startActivity(i);
                                }
                                else{
                                    cursor.moveToPosition(p-1);
                                }

                            }
                            else{
                                cursor.moveToNext();
                                question.setText(questions.get(cursor.getInt(1)));
                                answer2.setText("B. "+answers.get(cursor.getInt(1)));
                                answer1.setText("A. "+wrongAnswers.get(cursor.getInt(1)));
                            }


                        }
                        else{
                            if(cursor.isLast()){
                                int p = cursor.getCount();
                             //   Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
                                if(cursor.getCount()==p){
                                    Intent i = new Intent(getApplicationContext(), resultActivity.class);
                                    startActivity(i);
                                }
                                else{
                                    cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});
                                    cursor.moveToPosition(p-1);
                                }

                            }else{
                                cursor.moveToNext();
                                question.setText(questions.get(cursor.getInt(1)));
                                answer1.setText("A. "+answers.get(cursor.getInt(1)));
                                answer2.setText("B. "+wrongAnswers.get(cursor.getInt(1)));
                            }


                        }
                        answer2.setBackgroundColor(80000000);
                        XP.setText("XP: " +score);
                        Coins.setText("Coins: " + right_ans_score + "");
                    }
                }.start();


            }
            else{
                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        answer2.setBackgroundColor(getResources().getColor(R.color.red));

                    }

                    @Override
                    public void onFinish() {

                        wrong_ans_score++;
                        score = score - 300;
                        life = life -1;
                        if(life == 2){
                            health.setImageResource(R.drawable.halffinal2);
                        }
                        else if (life == 1 ){
                            health.setImageResource(R.drawable.lowhealthfinal2);
                        }
                        else if(life == 0){
                           // Toast.makeText(this, "you died", Toast.LENGTH_SHORT).show();
                            Intent z = new Intent(getApplicationContext(), resultActivity.class);
                            startActivity(z);
                        }
                        answer2.setBackgroundColor(80000000);
                        XP.setText("XP: " +score);
                        Coins.setText("Coins: " + right_ans_score + "");
                    }
                }.start();

            }
        }

    };
}