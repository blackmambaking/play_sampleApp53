package com.example.sampleapp53.squadMode;

import android.os.Build;
import android.os.CountDownTimer;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Random;

public class detectSwipeSquad extends GestureDetector.SimpleOnGestureListener {
    private static int MIN_SWIPE_DISTANCE_X= 100;
    private static int MIN_SWIPE_DISTANCE_Y= 100;

    private static int MAX_SWIPE_DISTANCE_X= 1000;
    private static int MAX_SWIPE_DISTANCE_Y= 1000;
    com.example.sampleapp53.myDBHelper myDBHelper;
    private MainActivityForSqadPlay activity = null;
    public MainActivityForSqadPlay getActivity(){
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
                if(MainActivityForSqadPlay.recyclerView.getTranslationX() == (10000)){
                    MainActivityForSqadPlay.recyclerView.setTranslationX(0);
                }
                else {

                }
            }
            else{
                Toast.makeText(activity, "Right Swipe", Toast.LENGTH_SHORT).show();
                if(MainActivityForSqadPlay.recyclerView.getTranslationX() == (0)){
                    MainActivityForSqadPlay.recyclerView.setTranslationX(10000);
                }
                else {

                }

            }
        }

        if (deltaYabs >= MIN_SWIPE_DISTANCE_Y && deltaYabs <= MAX_SWIPE_DISTANCE_Y){
            if (deltaY>0 && MainActivityForSqadPlay.j < MainActivityForSqadPlay.questions.size()){
                this.activity.displayMessage(0);

            }
            else if (deltaY>0 && MainActivityForSqadPlay.j >= MainActivityForSqadPlay.questions.size()){

            }
            else if(deltaY<0 && MainActivityForSqadPlay.j < MainActivityForSqadPlay.questions.size()){
                this.activity.displayMessage(1);
            }
            else if(deltaY<0 && MainActivityForSqadPlay.j >= MainActivityForSqadPlay.questions.size()){

            }
        }

        return true;
    }


    @Override
    public boolean onDoubleTap(MotionEvent e) {
//        myDBHelper = new myDBHelper(getActivity());
//        if(MainActivityForSqadPlay.s< MainActivityForSqadPlay.questions.size()){
//            myDBHelper.insertData(adapterForChapterSelection.finalChapterSelected, MainActivity.s);
//            Toast.makeText(activity, "marked question number is " + (MainActivity.s+1), Toast.LENGTH_SHORT).show();
//        }
//        else{
//            myDBHelper.insertData(adapterForChapterSelection.finalChapterSelected, MainActivity.cursor.getInt(1));
//            Toast.makeText(activity, "marked question number is" + (MainActivity.cursor.getInt(1) + 1), Toast.LENGTH_SHORT).show();
//        }
        return true;
    }
int i = 30;
    @Override
    public void onLongPress(MotionEvent e) {
        Random rn = new Random();
        MainActivityForSqadPlay.gameVideo.start();
        MainActivityForSqadPlay.timer.setText(i + "");
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                MainActivityForSqadPlay.timer.setText( i + "");
                i--;
            }

            @Override
            public void onFinish() {
                i = 30;
                MainActivityForSqadPlay.timer.setText(0 + "");
                Toast.makeText(activity, "you died!", Toast.LENGTH_SHORT).show();

            }
        }.start();

        int i = rn.nextInt(2);

        this.activity.question.setText(MainActivityForSqadPlay.questions.get(0));
        if(i == 0){
            activity.answer1.setText("A. "+MainActivityForSqadPlay.answers.get(0));
            activity.answer2.setText("B. "+MainActivityForSqadPlay.wrongAnswers.get(0));
        }
        else{
            activity.answer2.setText("B. "+MainActivityForSqadPlay.answers.get(0));
            activity.answer1.setText("A. "+MainActivityForSqadPlay.wrongAnswers.get(0));
        }

        super.onLongPress(e);
    }

    public void setActivity (MainActivityForSqadPlay activity){
        this.activity = activity;

    }


}
