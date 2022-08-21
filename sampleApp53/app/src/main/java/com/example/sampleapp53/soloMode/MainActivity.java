package com.example.sampleapp53.soloMode;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapter;
import com.example.sampleapp53.adapterForCardView.adapterForChapterSelection;
import com.example.sampleapp53.adapterForCardView.adapter_quick_question;
import com.example.sampleapp53.myDBHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {
    public static Cursor cursor3;
    public static TextView question;
    public static TextView answer1;
    public static TextView answer2;
    public static String previousAnswer;
    int startingCOunter = 3;
    TextView mcc_question;
    TextView mcc_answer1;
    TextView mcc_answer2;
    TextView mcc_answer3;
    TextView mcc_answer4;
    TextView lane1_box1;
    TextView lane1_box2;
    TextView lane1_box3;
    TextView lane1_box4;
    TextView lane2_box1;
    TextView lane2_box2;
    TextView lane2_box3;
    TextView lane2_box4;
    TextView mcq_question;
    TextView mcq_answer1;
    TextView mcq_answer2;
    TextView mcq_answer3;
    TextView mcq_answer4;
    static TextView XP;
    public static TextView Coins;
    ArrayList <String>questionsStorer;
    ArrayList <String>answerStorer;
    ArrayList <String>wrongAnsStorer;
    Handler handler;
    Runnable runnable;
    adapter_quick_question madapter_quick_question;
    public static RecyclerView recyclerView;
    public static VideoView gameVideo;
    TextView compliments;

    static Cursor cursor;
    SQLiteDatabase database;
    static Cursor cursor2;
    com.example.sampleapp53.myDBHelper helper;
    public FirebaseFirestore firedata;
    Map<String, Object> m;

    private GestureDetectorCompat gestureDetectorCompat = null;
    public static ArrayList <String> questions;
    public static ArrayList <String> answers ;
    public static  ArrayList <String> wrongAnswers ;
    Map<String, Object> docData;
    public static ArrayList <String> mcc_questions;
    public static ArrayList <String> mcc_answers1 ;
    public static  ArrayList <String> mcc_answers2 ;
    public static ArrayList <String> mcc_answers3 ;
    public static  ArrayList <String> mcc_wrong_answers ;
    static int k = 0;
    myDBHelper myDBHelper;
    public static  ArrayList <String> pq ;

    public static ArrayList <String> matching_column1;
    public static ArrayList <String> matching_column2;
    public TextView matching_heading;
    public static ArrayList <String> mcq_questions;
    public static ArrayList <String> mcq_wrong_answers1 ;
    public static ArrayList <String> detailedAnswerState ;
    SQLiteDatabase db;
    public static  ArrayList <String> mcq_answers ;
    Scanner scanner = null;
    int n = 0;
    int x = n+1;
    int y = n+2;
    int z = n+3;
    int p = 0;
    int q = 0;
    int selected_box = 0;
    String match = null;
    public static int s = 0;
    public void mcq_helper1(){
        right_ans_score++;
        score = score + 1000;
        s++;
        if(s==mcq_questions.size()){
            Toast.makeText(this, "Round 2", Toast.LENGTH_SHORT).show();
            cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});

            if(cursor.getCount()==0){
                Intent z = new Intent(getApplicationContext(), resultActivity.class);
                startActivity(z);
            }
            else{
                int correct_answer = rn.nextInt(4);
                if(correct_answer ==0 ){
                    cursor.moveToFirst();
                    mcq_question.setText(mcq_questions.get(cursor.getInt(1)));
                    mcq_answer1.setText(mcq_answers.get(cursor.getInt(1)));
                    mcq_answer2.setText(mcq_wrong_answers1.get(cursor.getInt(1)));
                    mcq_answer3.setText(mcq_wrong_answers1.get(cursor.getInt(1)+1));
                    mcq_answer4.setText(mcq_wrong_answers1.get(cursor.getInt(1)+2));
                }
                else if (correct_answer ==1){
                    cursor.moveToFirst();
                    mcq_question.setText(mcq_questions.get(cursor.getInt(1)));
                    mcq_answer2.setText(mcq_answers.get(cursor.getInt(1)));
                    mcq_answer1.setText(mcq_wrong_answers1.get(cursor.getInt(1)));
                    mcq_answer3.setText(mcq_wrong_answers1.get(cursor.getInt(1)+1));
                    mcq_answer4.setText(mcq_wrong_answers1.get(cursor.getInt(1)+2));
                }
                else if (correct_answer ==2){
                    cursor.moveToFirst();
                    mcq_question.setText(mcq_questions.get(cursor.getInt(1)));
                    mcq_answer3.setText(mcq_answers.get(cursor.getInt(1)));
                    mcq_answer2.setText(mcq_wrong_answers1.get(cursor.getInt(1)));
                    mcq_answer1.setText(mcq_wrong_answers1.get(cursor.getInt(1)+1));
                    mcq_answer4.setText(mcq_wrong_answers1.get(cursor.getInt(1)+2));
                }
                else if (correct_answer ==3){
                    cursor.moveToFirst();
                    mcq_question.setText(mcq_questions.get(cursor.getInt(1)));
                    mcq_answer4.setText(mcq_answers.get(cursor.getInt(1)));
                    mcq_answer2.setText(mcq_wrong_answers1.get(cursor.getInt(1)));
                    mcq_answer3.setText(mcq_wrong_answers1.get(cursor.getInt(1)+1));
                    mcq_answer1.setText(mcq_wrong_answers1.get(cursor.getInt(1)+2));
                }
            }
        }
        else if(s<mcq_questions.size()){
            int correct_answer = rn.nextInt(4);

            if(correct_answer ==0 ){
                mcq_question.setText(mcq_questions.get(s));
                mcq_answer1.setText(mcq_answers.get(s));
                mcq_answer2.setText(mcq_wrong_answers1.get(s));
                mcq_answer3.setText(mcq_wrong_answers1.get(s+1));
                mcq_answer4.setText(mcq_wrong_answers1.get(s+2));
            }
            else if(correct_answer == 1){
                mcq_question.setText(mcq_questions.get(s));
                mcq_answer2.setText(mcq_answers.get(s));
                mcq_answer1.setText(mcq_wrong_answers1.get(s));
                mcq_answer3.setText(mcq_wrong_answers1.get(s+1));
                mcq_answer4.setText(mcq_wrong_answers1.get(s+2));
            }
            else if(correct_answer == 2){
                mcq_question.setText(mcq_questions.get(s));
                mcq_answer3.setText(mcq_answers.get(s));
                mcq_answer2.setText(mcq_wrong_answers1.get(s));
                mcq_answer1.setText(mcq_wrong_answers1.get(s+1));
                mcq_answer4.setText(mcq_wrong_answers1.get(s+2));
            }
            else if(correct_answer == 3){
                mcq_question.setText(mcq_questions.get(s));
                mcq_answer4.setText(mcq_answers.get(s));
                mcq_answer2.setText(mcq_wrong_answers1.get(s));
                mcq_answer3.setText(mcq_wrong_answers1.get(s+1));
                mcq_answer1.setText(mcq_wrong_answers1.get(s+2));
            }
        }
    }
    public void mcq_helper2(){
        right_ans_score++;
        score = score + 1000;
        int correct_answer = rn.nextInt(4);

        if(correct_answer ==0 ){
            if(cursor.isLast()){
                int p = cursor.getCount();
                Toast.makeText(this, "Get ready for next round!", Toast.LENGTH_SHORT).show();
                cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});
                if(cursor.getCount()==p){
                    Intent i = new Intent(getApplicationContext(), resultActivity.class);
                    startActivity(i);
                }
                else{
                    cursor.moveToPosition(p-1);
                    mcq_question.setText(mcq_questions.get(cursor.getInt(1)));
                    mcq_answer1.setText(mcq_answers.get(cursor.getInt(1)));
                    mcq_answer2.setText(mcq_wrong_answers1.get(cursor.getInt(1)));
                    mcq_answer3.setText(mcq_wrong_answers1.get(cursor.getInt(1)+1));
                    mcq_answer4.setText(mcq_wrong_answers1.get(cursor.getInt(1)+2));
                }
            }else{
                cursor.moveToNext();
                mcq_question.setText(mcq_questions.get(cursor.getInt(1)));
                mcq_answer1.setText(mcq_answers.get(cursor.getInt(1)));
                mcq_answer2.setText(mcq_wrong_answers1.get(cursor.getInt(1)));
                mcq_answer3.setText(mcq_wrong_answers1.get(cursor.getInt(1)+1));
                mcq_answer4.setText(mcq_wrong_answers1.get(cursor.getInt(1)+2));
            }
        }
        else if (correct_answer == 1){
            if(cursor.isLast()){
                int p = cursor.getCount();
                Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
                cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});
                if(cursor.getCount()==p){
                    Intent i = new Intent(getApplicationContext(), resultActivity.class);
                    startActivity(i);
                }
                else{
                    cursor.moveToPosition(p-1);
                    mcq_question.setText(mcq_questions.get(cursor.getInt(1)));
                    mcq_answer2.setText(mcq_answers.get(cursor.getInt(1)));
                    mcq_answer1.setText(mcq_wrong_answers1.get(cursor.getInt(1)));
                    mcq_answer3.setText(mcq_wrong_answers1.get(cursor.getInt(1)+1));
                    mcq_answer4.setText(mcq_wrong_answers1.get(cursor.getInt(1)+2));
                }

            }else{
                cursor.moveToNext();
                mcq_question.setText(mcq_questions.get(cursor.getInt(1)));
                mcq_answer2.setText(mcq_answers.get(cursor.getInt(1)));
                mcq_answer1.setText(mcq_wrong_answers1.get(cursor.getInt(1)));
                mcq_answer3.setText(mcq_wrong_answers1.get(cursor.getInt(1)+1));
                mcq_answer4.setText(mcq_wrong_answers1.get(cursor.getInt(1)+2));
            }
        }
        else if(correct_answer == 2){
            if(cursor.isLast()){
                int p = cursor.getCount();
                Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
                cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});
                if(cursor.getCount()==p){
                    Intent i = new Intent(getApplicationContext(), resultActivity.class);
                    startActivity(i);
                }
                else{
                    cursor.moveToPosition(p-1);
                    mcq_question.setText(mcq_questions.get(cursor.getInt(1)));
                    mcq_answer3.setText(mcq_answers.get(cursor.getInt(1)));
                    mcq_answer2.setText(mcq_wrong_answers1.get(cursor.getInt(1)));
                    mcq_answer1.setText(mcq_wrong_answers1.get(cursor.getInt(1)+1));
                    mcq_answer4.setText(mcq_wrong_answers1.get(cursor.getInt(1)+2));
                }

            }else{
                cursor.moveToNext();
                mcq_question.setText(mcq_questions.get(cursor.getInt(1)));
                mcq_answer3.setText(mcq_answers.get(cursor.getInt(1)));
                mcq_answer2.setText(mcq_wrong_answers1.get(cursor.getInt(1)));
                mcq_answer1.setText(mcq_wrong_answers1.get(cursor.getInt(1)+1));
                mcq_answer4.setText(mcq_wrong_answers1.get(cursor.getInt(1)+2));
            }
        }
        else if (correct_answer==3){
            if(cursor.isLast()){
                int p = cursor.getCount();
                Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
                cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});
                if(cursor.getCount()==p){
                    Intent i = new Intent(getApplicationContext(), resultActivity.class);
                    startActivity(i);
                }
                else{
                    cursor.moveToPosition(p-1);
                    mcq_question.setText(mcq_questions.get(cursor.getInt(1)));
                    mcq_answer4.setText(mcq_answers.get(cursor.getInt(1)));
                    mcq_answer2.setText(mcq_wrong_answers1.get(cursor.getInt(1)));
                    mcq_answer3.setText(mcq_wrong_answers1.get(cursor.getInt(1)+1));
                    mcq_answer1.setText(mcq_wrong_answers1.get(cursor.getInt(1)+2));
                }

            }else{
                cursor.moveToNext();
                mcq_question.setText(mcq_questions.get(cursor.getInt(1)));
                mcq_answer4.setText(mcq_answers.get(cursor.getInt(1)));
                mcq_answer2.setText(mcq_wrong_answers1.get(cursor.getInt(1)));
                mcq_answer3.setText(mcq_wrong_answers1.get(cursor.getInt(1)+1));
                mcq_answer1.setText(mcq_wrong_answers1.get(cursor.getInt(1)+2));
            }
        }
    }
    public void matching_logic_helper(){
        if(lane1_box1.getText().equals(match)){
            lane1_box1.setText("");
            lane1_box1.setBackgroundColor(0);
        }
        else if(lane1_box2.getText().equals(match)){
            lane1_box2.setText("");
            lane1_box2.setBackgroundColor(0);
        }
        else if(lane1_box3.getText().equals(match)){
            lane1_box3.setText("");
            lane1_box3.setBackgroundColor(0);
        }
        else if(lane1_box4.getText().equals(match)){
            lane1_box4.setText("");
            lane1_box4.setBackgroundColor(0);
        }
    }

    public void threecc_logic(int message){
        helper = new myDBHelper(this);
        database = helper.getReadableDatabase();
        if(message == 0){
            if(mcc_answer1.getText().toString().equals(mcc_answers1.get(k))
                    || mcc_answer1.getText().toString().equals(mcc_answers2.get(k))
                    || mcc_answer1.getText().toString().equals(mcc_answers3.get(k))
            ){
                mcc_answer1.setBackgroundColor(Color.GREEN);
                right_ans_score++;
                score = score + 1000;
                intrinsic_mcc_answered_questions = intrinsic_mcc_answered_questions + 1;

                 if(k<mcc_questions.size()){
                    int correct_answer = rn.nextInt(4);
                    if(intrinsic_mcc_answered_questions == 3){
                        intrinsic_mcc_answered_questions = 0;

                        mcc_answer1.setBackgroundColor(Color.TRANSPARENT);
                        mcc_answer2.setBackgroundColor(Color.TRANSPARENT);
                        mcc_answer3.setBackgroundColor(Color.TRANSPARENT);
                        mcc_answer4.setBackgroundColor(Color.TRANSPARENT);
                        if(correct_answer ==0 ){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer1.setText(mcc_answers1.get(k));
                            mcc_answer2.setText(mcc_answers2.get(k));
                            mcc_answer3.setText(mcc_answers3.get(k));
                            mcc_answer4.setText(mcc_wrong_answers.get(k));
                            k++;

                        }
                        else if(correct_answer == 1){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer1.setText(mcc_answers1.get(k));
                            mcc_answer2.setText(mcc_answers2.get(k));
                            mcc_answer4.setText(mcc_answers3.get(k));
                            mcc_answer3.setText(mcc_wrong_answers.get(k));
                            k++;

                        }
                        else if(correct_answer == 2){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer1.setText(mcc_answers1.get(k));
                            mcc_answer4.setText(mcc_answers2.get(k));
                            mcc_answer3.setText(mcc_answers3.get(k));
                            mcc_answer2.setText(mcc_wrong_answers.get(k));
                            k++;
                        }
                        else if(correct_answer == 3 ){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer4.setText(mcc_answers1.get(k));
                            mcc_answer2.setText(mcc_answers2.get(k));
                            mcc_answer3.setText(mcc_answers3.get(k));
                            mcc_answer1.setText(mcc_wrong_answers.get(k));
                            k++;
                        }
                    }

                }
                else if(k==mcc_questions.size()){
                    Toast.makeText(this, "Round 2", Toast.LENGTH_SHORT).show();
                    cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});

                    if(cursor.getCount()==0){
                        Intent z = new Intent(getApplicationContext(), resultActivity.class);
                        startActivity(z);
                    }
                    else{
                        int correct_answer = rn.nextInt(4);

                        if(intrinsic_mcc_answered_questions == 3){
                            intrinsic_mcc_answered_questions = 0;

                            mcc_answer1.setBackgroundColor(Color.TRANSPARENT);
                            mcc_answer2.setBackgroundColor(Color.TRANSPARENT);
                            mcc_answer3.setBackgroundColor(Color.TRANSPARENT);
                            mcc_answer4.setBackgroundColor(Color.TRANSPARENT);

                            if(correct_answer ==0 ){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                            else if(correct_answer == 1){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                            else if(correct_answer == 2){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                            else if(correct_answer == 3 ){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                        }

                    }
                }

            }
            else{
                Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
                mcc_answer1.setBackgroundColor(Color.RED);
                wrong_ans_score++;
                score = score - 300;
            }
        }
        if(message == 1){
            if(mcc_answer2.getText().toString().equals(mcc_answers1.get(k))
                    || mcc_answer2.getText().toString().equals(mcc_answers2.get(k))
                    || mcc_answer2.getText().toString().equals(mcc_answers3.get(k))
            ){
                mcc_answer2.setBackgroundColor(Color.GREEN);
                right_ans_score++;
                score = score + 1000;
                intrinsic_mcc_answered_questions = intrinsic_mcc_answered_questions + 1;




                 if(k<mcc_questions.size()){
                    int correct_answer = rn.nextInt(4);


                    if(intrinsic_mcc_answered_questions == 3){
                        intrinsic_mcc_answered_questions = 0;

                        mcc_answer1.setBackgroundColor(Color.TRANSPARENT);
                        mcc_answer2.setBackgroundColor(Color.TRANSPARENT);
                        mcc_answer3.setBackgroundColor(Color.TRANSPARENT);
                        mcc_answer4.setBackgroundColor(Color.TRANSPARENT);
                        if(correct_answer ==0 ){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer1.setText(mcc_answers1.get(k));
                            mcc_answer2.setText(mcc_answers2.get(k));
                            mcc_answer3.setText(mcc_answers3.get(k));
                            mcc_answer4.setText(mcc_wrong_answers.get(k));
                            k++;
                        }
                        else if(correct_answer == 1){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer1.setText(mcc_answers1.get(k));
                            mcc_answer2.setText(mcc_answers2.get(k));
                            mcc_answer4.setText(mcc_answers3.get(k));
                            mcc_answer3.setText(mcc_wrong_answers.get(k));
                            k++;
                        }
                        else if(correct_answer == 2){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer1.setText(mcc_answers1.get(k));
                            mcc_answer4.setText(mcc_answers2.get(k));
                            mcc_answer3.setText(mcc_answers3.get(k));
                            mcc_answer2.setText(mcc_wrong_answers.get(k));
                            k++;
                        }
                        else if(correct_answer == 3 ){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer4.setText(mcc_answers1.get(k));
                            mcc_answer2.setText(mcc_answers2.get(k));
                            mcc_answer3.setText(mcc_answers3.get(k));
                            mcc_answer1.setText(mcc_wrong_answers.get(k));
                            k++;
                        }
                    }
                }
                else if(k==mcc_questions.size()){
                    Toast.makeText(this, "Round 2", Toast.LENGTH_SHORT).show();
                    cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});

                    if(cursor.getCount()==0){
                        Intent z = new Intent(getApplicationContext(), resultActivity.class);
                        startActivity(z);
                    }
                    else{
                        int correct_answer = rn.nextInt(4);


                        if(intrinsic_mcc_answered_questions == 3){
                            intrinsic_mcc_answered_questions = 0;

                            mcc_answer1.setBackgroundColor(Color.TRANSPARENT);
                            mcc_answer2.setBackgroundColor(Color.TRANSPARENT);
                            mcc_answer3.setBackgroundColor(Color.TRANSPARENT);
                            mcc_answer4.setBackgroundColor(Color.TRANSPARENT);

                            if(correct_answer ==0 ){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                            else if(correct_answer == 1){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                            else if(correct_answer == 2){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                            else if(correct_answer == 3 ){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                        }

                    }
                }
            }
            else{
                Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
                mcc_answer2.setBackgroundColor(Color.RED);
                wrong_ans_score++;
                score = score - 300;
            }
        }
        if(message == 2){
            if(mcc_answer3.getText().toString().equals(mcc_answers1.get(k))
                    || mcc_answer3.getText().toString().equals(mcc_answers2.get(k))
                    || mcc_answer3.getText().toString().equals(mcc_answers3.get(k))
            ){
                mcc_answer3.setBackgroundColor(Color.GREEN);
                right_ans_score++;
                score = score + 1000;
                intrinsic_mcc_answered_questions = intrinsic_mcc_answered_questions + 1;



                 if(k<mcc_questions.size()){
                    int correct_answer = rn.nextInt(4);


                    if(intrinsic_mcc_answered_questions == 3){
                        intrinsic_mcc_answered_questions = 0;

                        mcc_answer1.setBackgroundColor(Color.TRANSPARENT);
                        mcc_answer2.setBackgroundColor(Color.TRANSPARENT);
                        mcc_answer3.setBackgroundColor(Color.TRANSPARENT);
                        mcc_answer4.setBackgroundColor(Color.TRANSPARENT);
                        if(correct_answer ==0 ){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer1.setText(mcc_answers1.get(k));
                            mcc_answer2.setText(mcc_answers2.get(k));
                            mcc_answer3.setText(mcc_answers3.get(k));
                            mcc_answer4.setText(mcc_wrong_answers.get(k));
                            k++;
                        }
                        else if(correct_answer == 1){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer1.setText(mcc_answers1.get(k));
                            mcc_answer2.setText(mcc_answers2.get(k));
                            mcc_answer4.setText(mcc_answers3.get(k));
                            mcc_answer3.setText(mcc_wrong_answers.get(k));
                            k++;
                        }
                        else if(correct_answer == 2){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer1.setText(mcc_answers1.get(k));
                            mcc_answer4.setText(mcc_answers2.get(k));
                            mcc_answer3.setText(mcc_answers3.get(k));
                            mcc_answer2.setText(mcc_wrong_answers.get(k));
                            k++;
                        }
                        else if(correct_answer == 3 ){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer4.setText(mcc_answers1.get(k));
                            mcc_answer2.setText(mcc_answers2.get(k));
                            mcc_answer3.setText(mcc_answers3.get(k));
                            mcc_answer1.setText(mcc_wrong_answers.get(k));
                            k++;
                        }
                    }
                }
                if(k==mcc_questions.size()){
                    Toast.makeText(this, "Round 2", Toast.LENGTH_SHORT).show();
                    cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});

                    if(cursor.getCount()==0){
                        Intent z = new Intent(getApplicationContext(), resultActivity.class);
                        startActivity(z);
                    }
                    else{
                        int correct_answer = rn.nextInt(4);


                        if(intrinsic_mcc_answered_questions == 3){
                            intrinsic_mcc_answered_questions = 0;

                            mcc_answer1.setBackgroundColor(Color.TRANSPARENT);
                            mcc_answer2.setBackgroundColor(Color.TRANSPARENT);
                            mcc_answer3.setBackgroundColor(Color.TRANSPARENT);
                            mcc_answer4.setBackgroundColor(Color.TRANSPARENT);

                            if(correct_answer ==0 ){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                            else if(correct_answer == 1){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                            else if(correct_answer == 2){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                            else if(correct_answer == 3 ){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                        }

                    }
                }
            }
            else{
                Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
                mcc_answer3.setBackgroundColor(Color.RED);
                wrong_ans_score++;
                score = score - 300;
            }
        }
        if(message == 3){
            if(mcc_answer4.getText().toString().equals(mcc_answers1.get(k))
                    || mcc_answer4.getText().toString().equals(mcc_answers2.get(k))
                    || mcc_answer4.getText().toString().equals(mcc_answers3.get(k))
            ){
                mcc_answer4.setBackgroundColor(Color.GREEN);
                right_ans_score++;
                score = score + 1000;
                intrinsic_mcc_answered_questions = intrinsic_mcc_answered_questions + 1;



                 if(k<mcc_questions.size()){
                    int correct_answer = rn.nextInt(4);

                    if(intrinsic_mcc_answered_questions == 3){
                        intrinsic_mcc_answered_questions = 0;

                        mcc_answer1.setBackgroundColor(Color.TRANSPARENT);
                        mcc_answer2.setBackgroundColor(Color.TRANSPARENT);
                        mcc_answer3.setBackgroundColor(Color.TRANSPARENT);
                        mcc_answer4.setBackgroundColor(Color.TRANSPARENT);
                        if(correct_answer ==0 ){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer1.setText(mcc_answers1.get(k));
                            mcc_answer2.setText(mcc_answers2.get(k));
                            mcc_answer3.setText(mcc_answers3.get(k));
                            mcc_answer4.setText(mcc_wrong_answers.get(k));
                            k++;
                        }
                        else if(correct_answer == 1){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer1.setText(mcc_answers1.get(k));
                            mcc_answer2.setText(mcc_answers2.get(k));
                            mcc_answer4.setText(mcc_answers3.get(k));
                            mcc_answer3.setText(mcc_wrong_answers.get(k));
                            k++;
                        }
                        else if(correct_answer == 2){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer1.setText(mcc_answers1.get(k));
                            mcc_answer4.setText(mcc_answers2.get(k));
                            mcc_answer3.setText(mcc_answers3.get(k));
                            mcc_answer2.setText(mcc_wrong_answers.get(k));
                            k++;
                        }
                        else if(correct_answer == 3 ){

                            mcc_question.setText(mcc_questions.get(k));
                            mcc_answer4.setText(mcc_answers1.get(k));
                            mcc_answer2.setText(mcc_answers2.get(k));
                            mcc_answer3.setText(mcc_answers3.get(k));
                            mcc_answer1.setText(mcc_wrong_answers.get(k));
                            k++;
                        }
                    }
                }
                if(k==mcc_questions.size()){
                    Toast.makeText(this, "Round 2", Toast.LENGTH_SHORT).show();
                    cursor = database.rawQuery("SELECT CHAPTER, ARRAY_INDEX FROM MARKED_QUESTIONS", new String[]{});

                    if(cursor.getCount()==0){
                        Intent z = new Intent(getApplicationContext(), resultActivity.class);
                        startActivity(z);
                    }
                    else{
                        int correct_answer = rn.nextInt(4);
                        if(intrinsic_mcc_answered_questions == 3){
                            intrinsic_mcc_answered_questions = 0;

                            mcc_answer1.setBackgroundColor(Color.TRANSPARENT);
                            mcc_answer2.setBackgroundColor(Color.TRANSPARENT);
                            mcc_answer3.setBackgroundColor(Color.TRANSPARENT);
                            mcc_answer4.setBackgroundColor(Color.TRANSPARENT);

                            if(correct_answer ==0 ){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                            else if(correct_answer == 1){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                            else if(correct_answer == 2){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                            else if(correct_answer == 3 ){
                                cursor.moveToFirst();
                                mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                                mcc_answer4.setText(mcc_answers1.get(cursor.getInt(1)));
                                mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                                mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                                mcc_answer1.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                            }
                        }

                    }
                }
            }
            else{
                Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
                mcc_answer4.setBackgroundColor(Color.RED);
                wrong_ans_score++;
                score = score - 300;
            }
        }

    }
    public void threecc_logic2(int message){
        helper = new myDBHelper(this);
        database = helper.getReadableDatabase();
        if(message == 0){
            if(mcc_answer1.getText().toString().equals(mcc_answers1.get(cursor.getInt(1)))
            || mcc_answer1.getText().toString().equals(mcc_answers2.get(cursor.getInt(1)))
                    || mcc_answer1.getText().toString().equals(mcc_answers3.get(cursor.getInt(1)))
            ){
                right_ans_score++;
                score = score + 1000;
                int correct_answer = rn.nextInt(4);
                intrinsic_mcc_answered_questions = intrinsic_mcc_answered_questions +1;

                if(correct_answer ==0 && intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "Get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }
                else if (correct_answer == 1&& intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }
                else if(correct_answer == 2&& intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }

                else if(correct_answer ==3&& intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }
            }
            else{
                Toast.makeText(this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                wrong_ans_score++;
                score = score - 300;

            }
        }
        else if(message == 1){
            if(mcc_answer2.getText().toString().equals(mcc_answers1.get(cursor.getInt(1)))
                    || mcc_answer2.getText().toString().equals(mcc_answers2.get(cursor.getInt(1)))
                    || mcc_answer2.getText().toString().equals(mcc_answers3.get(cursor.getInt(1)))
            ){
                right_ans_score++;
                score = score + 1000;
                int correct_answer = rn.nextInt(4);
                intrinsic_mcc_answered_questions = intrinsic_mcc_answered_questions +1;

                if(correct_answer ==0 && intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "Get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }
                else if (correct_answer == 1&& intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }
                else if(correct_answer == 2&& intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }

                else if(correct_answer ==3&& intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }
            }
            else{
                Toast.makeText(this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                wrong_ans_score++;
                score = score - 300;

            }
        }
        else if(message == 2){
            if(mcc_answer3.getText().toString().equals(mcc_answers1.get(cursor.getInt(1)))
                    || mcc_answer3.getText().toString().equals(mcc_answers2.get(cursor.getInt(1)))
                    || mcc_answer3.getText().toString().equals(mcc_answers3.get(cursor.getInt(1)))
            ){
                right_ans_score++;
                score = score + 1000;
                int correct_answer = rn.nextInt(4);
                intrinsic_mcc_answered_questions = intrinsic_mcc_answered_questions +1;

                if(correct_answer ==0 && intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "Get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }
                else if (correct_answer == 1&& intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }
                else if(correct_answer == 2&& intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }

                else if(correct_answer ==3&& intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }
            }
            else{
                Toast.makeText(this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                wrong_ans_score++;
                score = score - 300;

            }
        }
        else if(message == 3){
            if(mcc_answer4.getText().toString().equals(mcc_answers1.get(cursor.getInt(1)))
                    || mcc_answer4.getText().toString().equals(mcc_answers2.get(cursor.getInt(1)))
                    || mcc_answer4.getText().toString().equals(mcc_answers3.get(cursor.getInt(1)))
            ){
                right_ans_score++;
                score = score + 1000;
                int correct_answer = rn.nextInt(4);
                intrinsic_mcc_answered_questions = intrinsic_mcc_answered_questions +1;

                if(correct_answer ==0 && intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "Get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }
                else if (correct_answer == 1&& intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }
                else if(correct_answer == 2&& intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }

                else if(correct_answer ==3&& intrinsic_mcc_answered_questions==3){
                    if(cursor.isLast()){
                        int p = cursor.getCount();
                        Toast.makeText(this, "get ready for next round!", Toast.LENGTH_SHORT).show();
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
                        mcc_question.setText(mcc_questions.get(cursor.getInt(1)));
                        mcc_answer4.setText(mcc_answers1.get(cursor.getInt(1)));
                        mcc_answer2.setText(mcc_answers2.get(cursor.getInt(1)));
                        mcc_answer3.setText(mcc_answers3.get(cursor.getInt(1)));
                        mcc_answer1.setText(mcc_wrong_answers.get(cursor.getInt(1)));
                        intrinsic_mcc_answered_questions = 0;
                    }
                }
            }
            else{
                Toast.makeText(this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                wrong_ans_score++;
                score = score - 300;

            }
        }
    }
    public void matching2(){
        if(lane2_box1.getText().equals("") && lane2_box2.getText().equals("")
                && lane2_box3.getText().equals("") && lane2_box4.getText().equals("") ){
             p = rn.nextInt(4);
             q = rn.nextInt(4);

            matching_heading.setText("Match");
            if(n<=matching_column1.size() - 2){
               n =  n+4;
            }
            if(p == 0){
                lane1_box1.setText(MainActivity.matching_column1.get(n));
                lane1_box2.setText(MainActivity.matching_column1.get(x));
                lane1_box3.setText(MainActivity.matching_column1.get(y));
                lane1_box4.setText(MainActivity.matching_column1.get(z));
            }
            else if(p==1){
                lane1_box1.setText(MainActivity.matching_column1.get(n));
                lane1_box2.setText(MainActivity.matching_column1.get(x));
                lane1_box4.setText(MainActivity.matching_column1.get(y));
                lane1_box3.setText(MainActivity.matching_column1.get(z));
            }
            else if(p==2){
                lane1_box1.setText(MainActivity.matching_column1.get(n));
                lane1_box4.setText(MainActivity.matching_column1.get(x));
                lane1_box3.setText(MainActivity.matching_column1.get(y));
                lane1_box2.setText(MainActivity.matching_column1.get(z));
            }
            else if(p==3){
                lane1_box4.setText(MainActivity.matching_column1.get(n));
                lane1_box2.setText(MainActivity.matching_column1.get(x));
                lane1_box3.setText(MainActivity.matching_column1.get(y));
                lane1_box1.setText(MainActivity.matching_column1.get(z));
            }
            if(q == 0){
                lane2_box1.setText(MainActivity.matching_column2.get(n));
                lane2_box2.setText(MainActivity.matching_column2.get(x));
                lane2_box3.setText(MainActivity.matching_column2.get(y));
                lane2_box4.setText(MainActivity.matching_column2.get(z));
            }
            else if(q==1){
                lane2_box1.setText(MainActivity.matching_column2.get(n));
                lane2_box2.setText(MainActivity.matching_column2.get(x));
                lane2_box4.setText(MainActivity.matching_column2.get(y));
                lane2_box3.setText(MainActivity.matching_column2.get(z));
            }
            else if(q==2){
                lane2_box1.setText(MainActivity.matching_column2.get(n));
                lane2_box4.setText(MainActivity.matching_column2.get(x));
                lane2_box3.setText(MainActivity.matching_column2.get(y));
                lane2_box2.setText(MainActivity.matching_column2.get(z));
            }
            else if(q==3){
                lane2_box4.setText(MainActivity.matching_column2.get(n));
                lane2_box2.setText(MainActivity.matching_column2.get(x));
                lane2_box3.setText(MainActivity.matching_column2.get(y));
                lane2_box1.setText(MainActivity.matching_column2.get(z));
            }
        }
    }
    public void matching_logic(int message){
        if(message == 1){
            lane1_box1.setBackgroundColor(Color.LTGRAY);
            lane1_box2.setBackgroundColor(0);
            lane1_box3.setBackgroundColor(0);
            lane1_box4.setBackgroundColor(0);
            match = lane1_box1.getText().toString();
            
        }
        else if(message == 2){
            lane1_box1.setBackgroundColor(0);
            lane1_box2.setBackgroundColor(Color.LTGRAY);
            lane1_box3.setBackgroundColor(0);
            lane1_box4.setBackgroundColor(0);
            match = lane1_box2.getText().toString();
            int selected_box = 2;
        }
        else if(message == 3){
            lane1_box1.setBackgroundColor(0);
            lane1_box2.setBackgroundColor(0);
            lane1_box3.setBackgroundColor(Color.LTGRAY);
            lane1_box4.setBackgroundColor(0);
            match = lane1_box3.getText().toString();
            int selected_box = 3;
        }
        else if(message == 4){
            lane1_box1.setBackgroundColor(0);
            lane1_box2.setBackgroundColor(0);
            lane1_box3.setBackgroundColor(0);
            lane1_box4.setBackgroundColor(Color.LTGRAY);
            match = lane1_box4.getText().toString();
        }
        if(message == 5&& match.equals(matching_column1.get(matching_column2.indexOf(lane2_box1.getText().toString()))) )
        {
            lane2_box1.setBackgroundColor(0);
            lane2_box1.setText("");
            matching_logic_helper();
            matching2();
        }
        else if(message == 6 && match.equals(matching_column1.get(matching_column2.indexOf(lane2_box2.getText().toString())))){
            lane2_box2.setBackgroundColor(0);
            lane2_box2.setText("");
            matching_logic_helper();
            matching2();
        }
        else if(message == 7 && match.equals(matching_column1.get(matching_column2.indexOf(lane2_box3.getText().toString())))){
            lane2_box3.setBackgroundColor(0);
            lane2_box3.setText("");
            matching_logic_helper();
            matching2();
        }
        else if(message == 8 && match.equals(matching_column1.get(matching_column2.indexOf(lane2_box4.getText().toString())))){
            lane2_box4.setBackgroundColor(0);
            lane2_box4.setText("");
            matching_logic_helper();
            matching2();
        }
    }
    public void matching_logic2(int message){
        if(message == 1){
            lane1_box1.setBackgroundColor(Color.LTGRAY);
            lane1_box2.setBackgroundColor(0);
            lane1_box3.setBackgroundColor(0);
            lane1_box4.setBackgroundColor(0);
            match = lane1_box1.getText().toString();
        }
    }
    public void mcq_logic (int message){
        if(message == 0){
            if(mcq_answer1.getText().toString().equals(mcq_answers.get(s)) ){
               mcq_helper1();
            }
            else{
                Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
                wrong_ans_score++;
                score = score - 300;
            }
        }
        else if(message ==1){
            if(mcq_answer2.getText().toString().equals(mcq_answers.get(s))){
              mcq_helper1();
            }
            else{
                Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
                wrong_ans_score++;
                score = score - 300;
            }
        }
        else if(message ==2){
            if(mcq_answer3.getText().toString().equals(mcq_answers.get(s))){
                mcq_helper1();
            }
            else{
                Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
                wrong_ans_score++;
                score = score - 300;
            }
        }
        else if(message ==3){
            if(mcq_answer4.getText().toString().equals(mcq_answers.get(s))){
                mcq_helper1();
            }
            else{
                Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
                wrong_ans_score++;
                score = score - 300;
            }
        }
    }
    public void mcq_logic2 (int message){

        if(message == 0){
            if(mcq_answer1.getText().toString().equals(mcq_answers.get(cursor.getInt(1)))){
                mcq_helper2();
            }
            else{
                Toast.makeText(this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                wrong_ans_score++;
                score = score - 300;
            }
        }
        if(message == 1){
            if(mcq_answer2.getText().toString().equals(mcq_answers.get(cursor.getInt(1)))){
                mcq_helper2();
            }
            else{
                Toast.makeText(this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                wrong_ans_score++;
                score = score - 300;
            }
        }
        if(message == 2){
            if(mcq_answer3.getText().toString().equals(mcq_answers.get(cursor.getInt(1)))){
                mcq_helper2();
            }
            else{
                Toast.makeText(this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                wrong_ans_score++;
                score = score - 300;
            }
        }
        if(message == 3){
            if(mcq_answer4.getText().toString().equals(mcq_answers.get(cursor.getInt(1)))){
                mcq_helper2();
            }
            else{
                Toast.makeText(this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                wrong_ans_score++;
                score = score - 300;
            }
        }
    }
    ImageView health;
    ArrayList<String> quick_questions;
    public static int j =0;
    public void waitingLoop(){
        Random rn = new Random();
        int x = rn.nextInt(3);
        if(x == 0){
            gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.waita);
            gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            gameVideo.start();
        }
        else if(x ==1){
            gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.waitb);
            gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            gameVideo.start();
        }
        else if(x ==2){
            gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.waitc);
            gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            gameVideo.start();
        }
    }
    public void heroWins(){
        Random rn = new Random();
        int x = rn.nextInt(3);
        if(x == 0){
            gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.heroa);
            gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                }
            });
            gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    onAnsweredMarkedRight();
                    waitingLoop();
                }
            });
        }
        else if(x ==1){
            gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.herob);
            gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                }
            });
            gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    onAnsweredMarkedRight();
                    waitingLoop();
                }
            });
        }
        else if(x ==2){
            gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.heroc);
            gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                }
            });
            gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    onAnsweredMarkedRight();
                    waitingLoop();
                }
            });
        }
    }
    public void villyWins(){
        Random rn = new Random();
        int x = rn.nextInt(3);
        if(x == 0){
            gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.villya);
            gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                }
            });
            gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    onAnswerMarkedWrong();
                    waitingLoop();


                }
            });
        }
        else if(x ==1){
            gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.villyb);
            gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                }
            });
            gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    onAnswerMarkedWrong();
                    waitingLoop();
                }
            });
        }
        else if(x ==2){
            gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.villyc);
            gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                }
            });
            gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    onAnswerMarkedWrong();
                    waitingLoop();
                }
            });
        }
    }
    @Override
    protected void onDestroy() {
        j = 0;
        Intent i = new Intent(getApplicationContext(), resultActivity.class);
        startActivity(i);
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        health= findViewById(R.id.health);
        question = findViewById(R.id.question);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        compliments = findViewById(R.id.complimentsovo);
        gameVideo = findViewById(R.id.videoView);
        handler = new Handler();
        Coins = findViewById(R.id.Coins);
        XP = findViewById(R.id.XP);
        Coins.setTextColor(getResources().getColor(R.color.gold));

        XP.setTextColor(getResources().getColor(R.color.gold));
        quick_questions = new ArrayList<>();
        question.bringToFront();
        answer1.bringToFront();
       answer2.bringToFront();
       XP.bringToFront();
       Coins.bringToFront();
       ActionBar actionBar = getSupportActionBar();
       actionBar.hide();
//        matching_heading = findViewById(R.id.matching_heading);
        questionsStorer = new ArrayList<>();
        answerStorer= new ArrayList<>();
        wrongAnsStorer = new ArrayList<>();
        detailedAnswerState = new ArrayList<>();
        firedata  = FirebaseFirestore.getInstance();
        for(int i =0; i<300; i++){
            detailedAnswerState.add("Unattempted");
        }
        waitingLoop();






//       lane1_box1 = findViewById(R.id.lane1_box1);
//       lane1_box2 = findViewById(R.id.lane1_box2);
//       lane1_box3 = findViewById(R.id.lane1_box3);
//       lane1_box4 = findViewById(R.id.lane1_box4);
//       lane2_box1 = findViewById(R.id.lane2_box1);
//       lane2_box2 = findViewById(R.id.lane2_box2);
//       lane2_box3 = findViewById(R.id.lane2_box3);
//       lane2_box4 = findViewById(R.id.lane2_box4);
//
//        mcc_question = findViewById(R.id.mcc_question);
//        mcc_answer1 = findViewById(R.id.mcc_answer1);
//        mcc_answer2 = findViewById(R.id.mcc_answer2);
//        mcc_answer3 = findViewById(R.id.mcc_answer3);
//        mcc_answer4 = findViewById(R.id.mcc_answer4);
//
//        mcq_answer1 = findViewById(R.id.mcq_answer1);
//        mcq_answer2 = findViewById(R.id.mcq_answer2);
//        mcq_answer3 = findViewById(R.id.mcq_answer3);
//        mcq_answer4 = findViewById(R.id.mcq_answer4);
//        mcq_question = findViewById(R.id.mcq_question);
// storing data in firestore


//       if(mcq_answer1.getText() != ""){
//           mcq_answer1.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   if(s<mcq_questions.size()){
//                       mcq_logic(0);
//                   }
//                   else{
//                       mcq_logic2(0);
//                   }
//               }
//           });
//           mcq_answer2.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   if(s<mcq_questions.size()){
//                       mcq_logic(1);
//                   }
//                   else{
//                       mcq_logic2(1);
//                   }
//               }
//           });
//           mcq_answer3.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   if(s<mcq_questions.size()){
//                       mcq_logic(2);
//                   }
//                   else{
//                       mcq_logic2(2);
//                   }
//               }
//           });
//           mcq_answer4.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   if(s<mcq_questions.size()){
//                       mcq_logic(3);
//                   }
//                   else{
//                       mcq_logic2(3);
//                   }
//               }
//           });
//       }

//       if(mcc_answer1.getText() != ""){
//           mcc_answer1.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   if(k<mcc_questions.size()){
//                       threecc_logic(0);
//                   }
//                   else{
//                       threecc_logic2(0);
//                   }
//               }
//           });
//           mcc_answer2.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   if(k<mcc_questions.size()){
//                       threecc_logic(1);
//                   }
//                   else{
//                       threecc_logic2(1);
//                   }
//               }
//           });
//           mcc_answer3.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   if(k<mcc_questions.size()){
//                       threecc_logic(2);
//                   }
//                   else{
//                       threecc_logic2(2);
//                   }
//               }
//           });
//           mcc_answer4.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   if(k<mcc_questions.size()){
//                       threecc_logic(3);
//                   }
//                   else{
//                       threecc_logic2(3);
//                   }
//               }
//           });
//       }

//       if(lane1_box1.getText() != ""){
//           lane1_box1.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   matching_logic(1);
//               }
//           });
//           lane1_box2.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   matching_logic(2);
//               }
//           });
//           lane1_box3.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   matching_logic(3);
//               }
//           });
//           lane1_box4.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   matching_logic(4);
//               }
//           });
//           lane2_box1.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   matching_logic(5);
//               }
//           });
//           lane2_box2.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   matching_logic(6);
//               }
//           });
//           lane2_box3.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   matching_logic(7);
//               }
//           });
//           lane2_box4.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   matching_logic(8);
//               }
//           });
//       }

        questions = new ArrayList<>();
        answers = new ArrayList<>();
        wrongAnswers = new ArrayList<>();

//        mcc_questions = new ArrayList<>();
//        mcc_answers1 = new ArrayList<>();
//        mcc_answers2 = new ArrayList<>();
//        mcc_answers3 = new ArrayList<>();
//        mcc_wrong_answers = new ArrayList<>();
//
//        matching_column1 = new ArrayList<>();
//        matching_column2 = new ArrayList<>();
//
//        mcq_questions = new ArrayList<>();
//        mcq_answers = new ArrayList<>();
//        mcq_wrong_answers1 = new ArrayList<>();
        for(int i = 0; i<300; i++){
            detailedAnswerState.add("");
        }


        detectSwipeListener gestureListener = new detectSwipeListener();
        gestureListener.setActivity(this);
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);

        helper = new myDBHelper(this);
        database = helper.getReadableDatabase();
        db = helper.getReadableDatabase();
        helper = new myDBHelper(this);
        database = helper.getReadableDatabase();
        cursor3 = db.rawQuery("SELECT QUESTION, ANSWER FROM DETAILED_ANALYSIS_OF_CHAPTER WHERE BOOK = " + "'"+ adapter.book_selected_byUser+"'" + " AND CHAPTER = " + "'"+adapterForChapterSelection.finalChapterSelected+"'" , new String[]{});
        if(cursor3.getCount() != 0){
            cursor3.moveToFirst();
            for(int i = 0; i<=cursor3.getCount();i++){
                detailedAnswerState.set(cursor3.getInt(0), cursor3.getString(1));

                if(cursor3.isLast()){

                }else{
                    cursor3.moveToNext();
                }
            }
        }

        Task<DocumentSnapshot> task = firedata.collection("Books")
                .document(adapter.book_selected_byUser.trim())
                .collection("Chapters")
                .document(adapterForChapterSelection.finalChapterSelected.trim())
                .get();
        task.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                DocumentSnapshot d = documentSnapshot;
                m = d.getData();
                questions = (ArrayList<String>) m.get("questions");
                answers = (ArrayList<String>) m.get("answers");
                wrongAnswers = (ArrayList<String>) m.get("wrong_answers");
//                mcc_questions = (ArrayList<String>) m.get("threecc_questions");
//                mcc_answers1 = (ArrayList<String>) m.get("threecc_answers1");
//                mcc_answers2 = (ArrayList<String>) m.get("threecc_answers2");
//                mcc_answers3 = (ArrayList<String>) m.get("threecc_answers3");
//                mcc_wrong_answers = (ArrayList<String>) m.get("threecc_wrong_answers");
//                matching_column1 = (ArrayList<String>) m.get("matching column1");
//                matching_column2 = (ArrayList<String>) m.get("matching_column2");
//                mcq_questions = (ArrayList<String>) m.get("mcq_questions");
//                mcq_answers = (ArrayList<String>) m.get("mcq_answers");
//                mcq_wrong_answers1 = (ArrayList<String>) m.get("mcq_wrong_answer1");
                quick_questions.addAll(questions);
                settingDetailedRecycler();
                answer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if  (MainActivity.j < MainActivity.questions.size()){
                            displayMessage(0);

                        }
                        else if (MainActivity.j >= MainActivity.questions.size()){
                            displayMessage2(0);
                        }
                    }
                });
                answer2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                          if( MainActivity.j < MainActivity.questions.size()){
                            displayMessage(1);
                        }
                        else if( MainActivity.j >= MainActivity.questions.size()){
                            displayMessage2(1);
                        }
                    }
                });

            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });

        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                answer1.setTextColor(getResources().getColor(R.color.gold));
                answer1.setTextSize(48);
                answer1.setText(startingCOunter+"");
                startingCOunter--;
            }

            @Override
            public void onFinish() {
                answer1.setTextColor(getResources().getColor(R.color.white));
                answer1.setTextSize(16);

                Random rn = new Random();
                gameVideo.start();

                int i = rn.nextInt(2);

                question.setText(questions.get(0));
                if(i == 0){
                    answer1.setText("A. "+answers.get(j));
                    answer2.setText("B. "+wrongAnswers.get(j));
                }
                else{
                    answer2.setText("B. "+answers.get(j));
                    answer1.setText("A. "+wrongAnswers.get(j));
                }
            }
        }.start();

  }
public void settingDetailedRecycler(){
    db = helper.getReadableDatabase();
    helper = new myDBHelper(this);
    database = helper.getReadableDatabase();
    cursor3 = db.rawQuery("SELECT QUESTION, ANSWER FROM DETAILED_ANALYSIS_OF_CHAPTER WHERE BOOK = " + "'"+ adapter.book_selected_byUser+"'" + " AND CHAPTER = " + "'"+adapterForChapterSelection.finalChapterSelected+"'" , new String[]{});
    if(cursor3.getCount() != 0){
        cursor3.moveToFirst();
        for(int i = 0; i<=cursor3.getCount();i++){
            detailedAnswerState.set(cursor3.getInt(0), cursor3.getString(1));

            if(cursor3.isLast()){

            }else{
                cursor3.moveToNext();
            }
        }
    }
    madapter_quick_question = new adapter_quick_question(quick_questions);
    recyclerView = findViewById(R.id.questionNavigator);
    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    recyclerView.setAdapter(madapter_quick_question);
    recyclerView.bringToFront();
    recyclerView.setTranslationX(-2000);
}
    //menu creation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.cameraPage:
//                Toast.makeText(this, "Capture", Toast.LENGTH_SHORT).show();
//
//                return true;
//        }
        return super.onOptionsItemSelected(item);
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
    public void storingDetailedQuestionRIGHT(){
        myDBHelper = new myDBHelper(this);
        if(!detailedAnswerState.get(j).equals("")){
            myDBHelper.updateDetailedQuestions(adapter.book_selected_byUser, adapterForChapterSelection.finalChapterSelected, MainActivity.j, "Correct");
        }
        myDBHelper.insertQuestionDetiled(adapter.book_selected_byUser, adapterForChapterSelection.finalChapterSelected, MainActivity.j, "Correct");
    }
    public void storingDetailedQuestionWRONG(){
        myDBHelper = new myDBHelper(this);
        if(!detailedAnswerState.get(j).equals("")){
            myDBHelper.updateDetailedQuestions(adapter.book_selected_byUser, adapterForChapterSelection.finalChapterSelected, MainActivity.j, "Incorrect");
        }
        myDBHelper.insertQuestionDetiled(adapter.book_selected_byUser, adapterForChapterSelection.finalChapterSelected, MainActivity.j, "Incorrect");
    }
    public void complimentTimer(){
    new CountDownTimer(2000, 2000) {
        @Override
        public void onTick(long millisUntilFinished) {
        }
        @Override
        public void onFinish() {
            compliments.setText("");
        }
    }.start();
}
    public void compliments(){
        compliments.bringToFront();

        if(j!=0 && j%10==0){
            compliments.setTextColor(getResources().getColor(R.color.gold));
            compliments.setText("Keep hustling!");
            complimentTimer();

        }
        else if(j!=0 && j%9 ==0){
             compliments.setTextColor(getResources().getColor(R.color.red));
            compliments.setText("Butcher!");
            complimentTimer();

        }
        else if(j!=0 && j%8 ==0){
             compliments.setTextColor(getResources().getColor(R.color.red));
            compliments.setText("Overkill!");
            complimentTimer();
        }
        else if(j!=0 && j%7 ==0){
             compliments.setTextColor(getResources().getColor(R.color.red));
            compliments.setText("Savage!");
            complimentTimer();
        }
        else if(j!=0 && j%6 ==0){
             compliments.setTextColor(getResources().getColor(R.color.red));
            compliments.setText("You are a beast!");
            complimentTimer();
        }
        else if(j!=0 && j%5 ==0){
             compliments.setTextColor(getResources().getColor(R.color.red));
            compliments.setText("Blood lust!");
            complimentTimer();
        }
        else if(j!=0 && j%4 ==0){
             compliments.setTextColor(getResources().getColor(R.color.red));
            compliments.setText("Brutality!");
            complimentTimer();
        }
        else if(j!=0 && j%3 ==0){
             compliments.setTextColor(getResources().getColor(R.color.red));
            compliments.setText("Fatality!");
            complimentTimer();
        }

    }

    static int intrinsic_mcc_answered_questions = 0;
    Random rn = new Random();

    public void onAnsweredMarkedRight(){
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
        answer2.setBackgroundColor(80000000);
        XP.setText("XP: " +score);
        Coins.setText("Coins: " + right_ans_score + "");

    }
    public void onAnswerMarkedWrong(){
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
        answer2.setBackgroundColor(80000000);
        XP.setText("XP: " +score);
        Coins.setText("Coins: " + right_ans_score + "");
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

    }

    public void displayMessage(int message){
        settingDetailedRecycler();
        if(message == 0){
            if(answer1.getText().toString().equals("A. "+answers.get(j)) ){
                answer1.setBackgroundColor(getResources().getColor(R.color.lightBlue));
                compliments();
                storingDetailedQuestionRIGHT();
                heroWins();
                right_ans_score++;
                score = score + 1000;
                previousAnswer = "right";
            }
            else{
                answer1.setBackgroundColor(getResources().getColor(R.color.red));
                villyWins();
                previousAnswer = "wrong";
                storingDetailedQuestionWRONG();
            }
        }
        else if(message ==1){
            if(answer2.getText().toString().equals("B. "+answers.get(j))){
                previousAnswer = "right";
                answer2.setBackgroundColor(getResources().getColor(R.color.lightBlue));
                storingDetailedQuestionRIGHT();
                heroWins();
                compliments();
            }
            else{
                previousAnswer = "wrong";
                answer2.setBackgroundColor(getResources().getColor(R.color.red));
                villyWins();
                storingDetailedQuestionWRONG();
            }
        }
    }
    public void displayMessage2(int message){

        settingDetailedRecycler();
        if(message == 0){

            if(answer1.getText().toString().equals("A. "+answers.get(cursor.getInt(1)))){
                compliments();
                heroWins();
                previousAnswer = "right";
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
                previousAnswer = "wrong";
                villyWins();
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
                previousAnswer = "right";
                heroWins();
                compliments();
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
                previousAnswer = "wrong";
                villyWins();
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