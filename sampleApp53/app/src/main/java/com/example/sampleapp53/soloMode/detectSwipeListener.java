package com.example.sampleapp53.soloMode;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.sampleapp53.adapterForCardView.adapter;
import com.example.sampleapp53.adapterForCardView.adapterForChapterSelection;
import com.example.sampleapp53.myDBHelper;

import java.util.Random;

public class detectSwipeListener extends GestureDetector.SimpleOnGestureListener{
    private static int MIN_SWIPE_DISTANCE_X= 100;
    private static int MIN_SWIPE_DISTANCE_Y= 100;
    int j = 0;
    private static int MAX_SWIPE_DISTANCE_X= 1000;
    private static int MAX_SWIPE_DISTANCE_Y= 1000;
    com.example.sampleapp53.myDBHelper myDBHelper;
    SQLiteDatabase db;
    Cursor cursor;
    private MainActivity activity = null;
    public MainActivity getActivity(){
        return activity;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float deltaX = e1.getX() - e2.getX();
        float deltaY = e1.getY() - e2.getY();
        float deltaXabs = Math.abs(deltaX);
        float deltaYabs = Math.abs(deltaY);
        if (deltaXabs >= MIN_SWIPE_DISTANCE_X && deltaXabs <= MAX_SWIPE_DISTANCE_X){
            if (deltaX>0){

                Toast.makeText(activity, "Left Swipe", Toast.LENGTH_SHORT).show();
                if (MainActivity.recyclerView.getTranslationX() == 0) {
                    MainActivity.recyclerView.setTranslationX(-2000);
                    MainActivity.question.setTranslationX(0);
                    MainActivity.answer1.setTranslationX(0);
                    MainActivity.answer2.setTranslationX(0);
                }else{

                }
            }
            else{

                Toast.makeText(activity, "Right Swipe", Toast.LENGTH_SHORT).show();

                if (MainActivity.recyclerView.getTranslationX() == -2000) {
                    MainActivity.recyclerView.setTranslationX(0);
                    MainActivity.recyclerView.bringToFront();
                    MainActivity.question.setTranslationX(-2000);
                    MainActivity.answer1.setTranslationX(-2000);
                    MainActivity.answer2.setTranslationX(-2000);

                }else {

                }
            }
        }

        if (deltaYabs >= MIN_SWIPE_DISTANCE_Y && deltaYabs <= MAX_SWIPE_DISTANCE_Y){
            if (deltaY>0 && MainActivity.j < MainActivity.questions.size()){
                this.activity.displayMessage(0);

            }
            else if (deltaY>0 && MainActivity.j >= MainActivity.questions.size()){
                this.activity.displayMessage2(0);
            }
            else if(deltaY<0 && MainActivity.j < MainActivity.questions.size()){
                this.activity.displayMessage(1);
            }
            else if(deltaY<0 && MainActivity.j >= MainActivity.questions.size()){
                this.activity.displayMessage2(1);
            }
        }

        return true;
    }


    @Override
    public boolean onDoubleTap(MotionEvent e) {
        myDBHelper = new myDBHelper(getActivity());
        myDBHelper.insertDataToAbsoluteMarkedQuestions(adapter.book_selected_byUser, adapterForChapterSelection.finalChapterSelected, MainActivity.questions.get(MainActivity.j), MainActivity.answers.get(MainActivity.j), MainActivity.wrongAnswers.get(MainActivity.j));
        if(MainActivity.j< MainActivity.questions.size()){
            myDBHelper.insertData(adapterForChapterSelection.finalChapterSelected, MainActivity.j);
            Toast.makeText(activity, "marked question number is " + (MainActivity.j+1), Toast.LENGTH_SHORT).show();
        }
        else{
            myDBHelper.insertData(adapterForChapterSelection.finalChapterSelected, MainActivity.cursor.getInt(1));
            Toast.makeText(activity, "marked question number is" + (MainActivity.cursor.getInt(1) + 1), Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Random rn = new Random();
        MainActivity.gameVideo.start();


//        int o = rn.nextInt(4);
//        this.activity.mcq_question.setText(MainActivity.mcq_questions.get(0));
//        if(o == 0){
//            activity.mcq_answer1.setText(MainActivity.mcq_answers.get(0));
//            activity.mcq_answer2.setText(MainActivity.mcq_wrong_answers1.get(1));
//            activity.mcq_answer3.setText(MainActivity.mcq_wrong_answers1.get(2));
//            activity.mcq_answer4.setText(MainActivity.mcq_wrong_answers1.get(3));
//        }
//        if(o == 1){
//            activity.mcq_answer2.setText(MainActivity.mcq_answers.get(0));
//            activity.mcq_answer1.setText(MainActivity.mcq_wrong_answers1.get(1));
//            activity.mcq_answer3.setText(MainActivity.mcq_wrong_answers1.get(2));
//            activity.mcq_answer4.setText(MainActivity.mcq_wrong_answers1.get(3));
//        }
//        if(o == 2){
//            activity.mcq_answer3.setText(MainActivity.mcq_answers.get(0));
//            activity.mcq_answer2.setText(MainActivity.mcq_wrong_answers1.get(1));
//            activity.mcq_answer1.setText(MainActivity.mcq_wrong_answers1.get(2));
//            activity.mcq_answer4.setText(MainActivity.mcq_wrong_answers1.get(3));
//        }
//        if(o == 3){
//            activity.mcq_answer4.setText(MainActivity.mcq_answers.get(0));
//            activity.mcq_answer2.setText(MainActivity.mcq_wrong_answers1.get(1));
//            activity.mcq_answer3.setText(MainActivity.mcq_wrong_answers1.get(2));
//            activity.mcq_answer1.setText(MainActivity.mcq_wrong_answers1.get(3));
//        }
        int i = rn.nextInt(2);
        myDBHelper = new myDBHelper(getActivity());
        db = myDBHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT QUESTION_NUMBER FROM CHECKPOINTS WHERE BOOK = " + "'"+ adapter.book_selected_byUser+"'" + " AND CHAPTER_NAME = " + "'"+adapterForChapterSelection.finalChapterSelected+"'" , new String[]{});

        if(cursor.getCount()!=0){
            cursor.moveToLast();
              j = cursor.getInt(0);
        }else{
            j = 0;
        }

        this.activity.question.setText(MainActivity.questions.get(j));
        if(i == 0){
            activity.answer1.setText("A. "+MainActivity.answers.get(j));
            activity.answer2.setText("B. "+MainActivity.wrongAnswers.get(j));
        }
        else{
            activity.answer2.setText("B. "+MainActivity.answers.get(j));
            activity.answer1.setText("A. "+MainActivity.wrongAnswers.get(j));
        }
//        int k = rn.nextInt(4);
//        this.activity.mcc_question.setText(MainActivity.mcc_questions.get(0));
//
//        if(k == 0){
//            activity.mcc_answer1.setText(MainActivity.mcc_answers1.get(0));
//            activity.mcc_answer2.setText(MainActivity.mcc_answers2.get(0));
//            activity.mcc_answer3.setText(MainActivity.mcc_answers3.get(0));
//            activity.mcc_answer4.setText(MainActivity.mcc_wrong_answers.get(0));
//        }
//        else if(k==1){
//            activity.mcc_answer1.setText(MainActivity.mcc_answers1.get(0));
//            activity.mcc_answer2.setText(MainActivity.mcc_answers2.get(0));
//            activity.mcc_answer4.setText(MainActivity.mcc_answers3.get(0));
//            activity.mcc_answer3.setText(MainActivity.mcc_wrong_answers.get(0));
//        }
//        else if(k==2){
//            activity.mcc_answer1.setText(MainActivity.mcc_answers1.get(0));
//            activity.mcc_answer4.setText(MainActivity.mcc_answers2.get(0));
//            activity.mcc_answer3.setText(MainActivity.mcc_answers3.get(0));
//            activity.mcc_answer2.setText(MainActivity.mcc_wrong_answers.get(0));
//        }
//        else if(k==3){
//            activity.mcc_answer4.setText(MainActivity.mcc_answers1.get(0));
//            activity.mcc_answer2.setText(MainActivity.mcc_answers2.get(0));
//            activity.mcc_answer3.setText(MainActivity.mcc_answers3.get(0));
//            activity.mcc_answer1.setText(MainActivity.mcc_wrong_answers.get(0));
//        }
//        int p = rn.nextInt(4);
//        int q = rn.nextInt(4);
//
//        activity.matching_heading.setText("Match");
//
//        if(p == 0){
//            activity.lane1_box1.setText(MainActivity.matching_column1.get(0));
//            activity.lane1_box2.setText(MainActivity.matching_column1.get(1));
//            activity.lane1_box3.setText(MainActivity.matching_column1.get(2));
//            activity.lane1_box4.setText(MainActivity.matching_column1.get(3));
//        }
//        else if(p==1){
//            activity.lane1_box1.setText(MainActivity.matching_column1.get(0));
//            activity.lane1_box2.setText(MainActivity.matching_column1.get(1));
//            activity.lane1_box4.setText(MainActivity.matching_column1.get(2));
//            activity.lane1_box3.setText(MainActivity.matching_column1.get(3));
//        }
//        else if(p==2){
//            activity.lane1_box1.setText(MainActivity.matching_column1.get(0));
//            activity.lane1_box4.setText(MainActivity.matching_column1.get(1));
//            activity.lane1_box3.setText(MainActivity.matching_column1.get(2));
//            activity.lane1_box2.setText(MainActivity.matching_column1.get(3));
//        }
//        else if(p==3){
//            activity.lane1_box4.setText(MainActivity.matching_column1.get(0));
//            activity.lane1_box2.setText(MainActivity.matching_column1.get(1));
//            activity.lane1_box3.setText(MainActivity.matching_column1.get(2));
//            activity.lane1_box1.setText(MainActivity.matching_column1.get(3));
//        }
//        if(q == 0){
//            activity.lane2_box1.setText(MainActivity.matching_column2.get(0));
//            activity.lane2_box2.setText(MainActivity.matching_column2.get(1));
//            activity.lane2_box3.setText(MainActivity.matching_column2.get(2));
//            activity.lane2_box4.setText(MainActivity.matching_column2.get(3));
//        }
//        else if(q==1){
//            activity.lane2_box1.setText(MainActivity.matching_column2.get(0));
//            activity.lane2_box2.setText(MainActivity.matching_column2.get(1));
//            activity.lane2_box4.setText(MainActivity.matching_column2.get(2));
//            activity.lane2_box3.setText(MainActivity.matching_column2.get(3));
//        }
//        else if(q==2){
//            activity.lane2_box1.setText(MainActivity.matching_column2.get(0));
//            activity.lane2_box4.setText(MainActivity.matching_column2.get(1));
//            activity.lane2_box3.setText(MainActivity.matching_column2.get(2));
//            activity.lane2_box2.setText(MainActivity.matching_column2.get(3));
//        }
//        else if(q==3){
//            activity.lane2_box4.setText(MainActivity.matching_column2.get(0));
//            activity.lane2_box2.setText(MainActivity.matching_column2.get(1));
//            activity.lane2_box3.setText(MainActivity.matching_column2.get(2));
//            activity.lane2_box1.setText(MainActivity.matching_column2.get(3));
//        }




        super.onLongPress(e);
    }

    public void setActivity (MainActivity activity){
        this.activity = activity;

    }




}
