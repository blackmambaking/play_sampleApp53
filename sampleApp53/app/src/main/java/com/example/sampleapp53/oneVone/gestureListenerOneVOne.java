package com.example.sampleapp53.oneVone;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;

import com.example.sampleapp53.adapterForCardView.adapter;
import com.example.sampleapp53.adapterForCardView.adapterForChapterSelection;
import com.example.sampleapp53.myDBHelper;

import java.util.Random;

public class gestureListenerOneVOne extends GestureDetector.SimpleOnGestureListener{
    private static int MIN_SWIPE_DISTANCE_X= 100;
    private static int MIN_SWIPE_DISTANCE_Y= 100;
    int j = 0;
    private static int MAX_SWIPE_DISTANCE_X= 1000;
    private static int MAX_SWIPE_DISTANCE_Y= 1000;
    com.example.sampleapp53.myDBHelper myDBHelper;
    SQLiteDatabase db;
    Cursor cursor;
    private MainActivityForOneVOne activity = null;
    public MainActivityForOneVOne getActivity(){
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
                Log.d("swipe","Left swipe");
            }
            else{
                Log.d("swipe","Right swipe");
            }
        }

        if (deltaYabs >= MIN_SWIPE_DISTANCE_Y && deltaYabs <= MAX_SWIPE_DISTANCE_Y){
            if (deltaY>0 && MainActivityForOneVOne.j < MainActivityForOneVOne.questions.size()){
                this.activity.displayMessage(0);
            }
            else if(deltaY<0 && MainActivityForOneVOne.j < MainActivityForOneVOne.questions.size()){
                this.activity.displayMessage(1);
            }
        }

        return true;
    }
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        myDBHelper = new myDBHelper(getActivity());
        myDBHelper.insertDataToAbsoluteMarkedQuestions(adapter.book_selected_byUser, adapterForChapterSelection.finalChapterSelected, MainActivityForOneVOne.questions.get(MainActivityForOneVOne.j), MainActivityForOneVOne.answers.get(MainActivityForOneVOne.j), MainActivityForOneVOne.wrongAnswers.get(MainActivityForOneVOne.j));
        return true;
    }
    @Override
    public void onLongPress(MotionEvent e) {
        Random rn = new Random();
        MainActivityForOneVOne.gameVideo.start();
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

        this.activity.question.setText(MainActivityForOneVOne.questions.get(j));
        if(i == 0){
            activity.answer1.setText("A. "+MainActivityForOneVOne.answers.get(j));
            activity.answer2.setText("B. "+MainActivityForOneVOne.wrongAnswers.get(j));
        }
        else{
            activity.answer2.setText("B. "+MainActivityForOneVOne.answers.get(j));
            activity.answer1.setText("A. "+MainActivityForOneVOne.wrongAnswers.get(j));
        }

        super.onLongPress(e);
    }

    public void setActivity (MainActivityForOneVOne activity){
        this.activity = activity;

    }

}
