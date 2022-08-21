package com.example.sampleapp53.oneVone;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GestureDetectorCompat;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapter;
import com.example.sampleapp53.adapterForCardView.adapterForChapterSelection;
import com.example.sampleapp53.basicLayout.dashboard;
import com.example.sampleapp53.myDBHelper;
import com.example.sampleapp53.squadMode.result_squad_mode;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivityForOneVOne extends AppCompatActivity {
    public static TextView question;
    public static TextView answer1;
    public static TextView answer2;
    String roomKey;

    static TextView XP;
    public static TextView Coins;
    public static VideoView gameVideo;
    public FirebaseFirestore firedata;
    GestureDetectorCompat gestureDetectorCompat = null;
    public static ArrayList<String> questions;
    public static ArrayList <String> answers ;
    public static  ArrayList <String> wrongAnswers ;
    public static ArrayList<String> questionsALL;
    public static ArrayList <String> answersALL ;
    public static  ArrayList <String> wrongAnswersALL ;
    public static ArrayList<String> firestoreScoreList;
    public static ArrayList<String> firestoreScoreList2;
    public static ArrayList<String> firestoreScoreList3;
    public static ArrayList<String> playerNames;
    public static ArrayList<String> playerHealths;
    TextView opponentXP;
    ImageView opponentHealth;
    String winnerOfQuestion;
    public static String opponentName="";
    TextView opponentNameTextview;
    public static TextView timer;
    Map<String, Object> m;
    ImageView health;
    myDBHelper myDBHelper;
    String hero = "";
    String villy = "";
    TextView playerName;

    public static int j =0;
    Map<String, Object> docData;
    Map<String, Object> docData3;
    TextView compliments;
    myDBHelper helper;
    SQLiteDatabase database;
    SQLiteDatabase db;
    Cursor cursor3;
    String currentAnswer = "";
    public static int roundsWon = 0;
    public static int roundsLost = 0;
    public static int percentageOfQuestions;
    public void waitingLoop(){
        Random rn = new Random();
        int x = rn.nextInt(1);
        if(x == 0){
            gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_waiting);
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
        if(hero.equals("red")){
            Random rn = new Random();
            int x = rn.nextInt(5);
            if(x == 0){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_red_beat_white_1);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==1){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_red_beat_white_2);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {

                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==2){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_red_beat_white_3);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==3){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_red_beat_white_4);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==4){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_red_beat_white_5);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
        }
        else if(hero.equals("white") ){
            Random rn = new Random();
            int x = rn.nextInt(5);
            if(x == 0){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_white_beat_red_1);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==1){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_white_beat_red_2);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {

                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==2){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_white_beat_red_3);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==3){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_white_beat_red_4);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==4){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_white_beat_red_5);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
        }

    }
    public void villyWins(){
        if(villy.equals("red")){
            Random rn = new Random();
            int x = rn.nextInt(5);
            if(x == 0){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_red_beat_white_1);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==1){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_red_beat_white_2);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {

                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==2){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_red_beat_white_3);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==3){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_red_beat_white_4);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==4){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_red_beat_white_5);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
        }
        else if(villy.equals("white")){
            Random rn = new Random();
            int x = rn.nextInt(5);
            if(x == 0){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_white_beat_red_1);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==1){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_white_beat_red_2);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {

                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==2){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_white_beat_red_3);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==3){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_white_beat_red_4);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
            else if(x ==4){
                gameVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.black_white_beat_red_5);
                gameVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                    }
                });
                gameVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        waitingLoop();
                    }
                });
            }
        }
    }
    int i = 0;
    int startingCOunter = 3;
    @Override
    protected void onDestroy() {
        j = 0;
        roundsWon = 0;
        roundsLost = 0;
        life = 3;
        super.onDestroy();

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_one_vone);
        firestoreScoreList = new ArrayList<>();
        firestoreScoreList2 = new ArrayList<>();
        firestoreScoreList3 = new ArrayList<>();
        playerHealths = new ArrayList<>();
        for(int p = 0; p< 2; p++){
            firestoreScoreList3.add(0 + "");
            playerHealths.add(3 + "");
        };
        health= findViewById(R.id.healthovo);
        opponentHealth = findViewById(R.id.imageView4);
        opponentXP = findViewById(R.id.textView37);
        question = findViewById(R.id.questionovo);
        playerName = findViewById(R.id.textView53);
        answer1 = findViewById(R.id.answer1ovo);
        answer2 = findViewById(R.id.answer2ovo);
        compliments = findViewById(R.id.complimentsovo);
        gameVideo = findViewById(R.id.videoViewovo);
        opponentNameTextview = findViewById(R.id.textView13);
        timer = findViewById(R.id.timerovo);
        firedata  = FirebaseFirestore.getInstance();
        Coins = findViewById(R.id.Coinsovo);
        winnerOfQuestion = "";
        playerNames = new ArrayList<>();

        XP = findViewById(R.id.XPovo);
        Coins.setTextColor(getResources().getColor(R.color.gold));

        roomKey = getIntent().getStringExtra("ROOM_KEY");

        XP.setTextColor(getResources().getColor(R.color.gold));
        docData3= new HashMap<>();
        docData= new HashMap<>();
        question.bringToFront();
        answer1.bringToFront();
        answer2.bringToFront();
        XP.bringToFront();
        Coins.bringToFront();

        waitingLoop();
        docData3.put("Score", firestoreScoreList3);
        docData3.put("Health", playerHealths);
        firedata.collection("OneVOne").document(roomKey + "")
                .update(docData3)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });


        questions = new ArrayList<>();
        answers = new ArrayList<>();
        wrongAnswers = new ArrayList<>();
        questionsALL = new ArrayList<>();
        answersALL= new ArrayList<>();
        wrongAnswersALL = new ArrayList<>();

        gestureListenerOneVOne gestureListener = new gestureListenerOneVOne();
        gestureListener.setActivity(this);
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);

        helper = new myDBHelper(this);
        database = helper.getReadableDatabase();
        db = helper.getReadableDatabase();
        helper = new myDBHelper(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        database = helper.getReadableDatabase();

        Task<DocumentSnapshot> task2 = firedata.collection("Books")
                .document(adapter.book_selected_byUser.trim())
                .collection("Chapters")
                .document(adapterForChapterSelection.finalChapterSelected.trim())
                .get();
        task2.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                DocumentSnapshot d = documentSnapshot;
                m = d.getData();
                questionsALL = (ArrayList<String>) m.get("questions");
                answersALL = (ArrayList<String>) m.get("answers");
                wrongAnswersALL = (ArrayList<String>) m.get("wrong_answers");
                for(int y = 0; y< getIntent().getIntExtra("percentage",100)*0.01*questionsALL.size(); y++){
                    questions.add(questionsALL.get(y));
                    answers.add(answersALL.get(y));
                    wrongAnswers.add(wrongAnswersALL.get(y));
                }
                Toast.makeText(MainActivityForOneVOne.this, questions.size() + "", Toast.LENGTH_SHORT).show();
                answer1.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View v) {
                        if  (j < questions.size()){
                            displayMessage(0);
                        }
                    }
                });
                answer2.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View v) {
                        if( j < questions.size()){
                            displayMessage(1);
                        }
                    }
                });

            }
        });
        task2.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(MainActivityForOneVOne.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
        Task<DocumentSnapshot> task3 = firedata.collection("OneVOne")
                .document(roomKey + "")
                .get();
        task3.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                DocumentSnapshot d = documentSnapshot;
                Map<String, Object> m = d.getData();

                if(m != null){
                    playerNames = new ArrayList<>();
                    playerNames = (ArrayList<String>) m.get("Players");
                    hero = (String)m.get("Hero");
                    villy = (String)m.get("Villy");
                    if(playerNames.get(0).equals(dashboard.personName)){
                        opponentNameTextview.setText(playerNames.get(1));
                        playerName.setText(playerNames.get(0));
                    }
                    else{
                        opponentNameTextview.setText(playerNames.get(0));
                        playerName.setText(playerNames.get(1));
                    }
                }
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
                    answer1.setText("A. "+answers.get(0));
                    answer2.setText("B. "+wrongAnswers.get(0));
                }
                else{
                    answer2.setText("B. "+answers.get(0));
                    answer1.setText("A. "+wrongAnswers.get(0));
                }
                roundBegins();
            }
        }.start();
        new CountDownTimer(36000000, 2000) {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTick(long millisUntilFinished) {
                storingScore();
            }

            @Override
            public void onFinish() {

            }
        }.start();
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

    Random rn = new Random();
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void storingScore(){

        Task<DocumentSnapshot> task3 = firedata.collection("OneVOne")
                .document(roomKey + "")
                .get();
        task3.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                DocumentSnapshot d = documentSnapshot;
                Map<String, Object> m = d.getData();
                if(m != null){
                    firestoreScoreList2 = new ArrayList<>();
                    playerHealths = new ArrayList<>();
                    firestoreScoreList2 = (ArrayList<String>) m.get("Score");
                    firestoreScoreList2.set(playerNames.indexOf(dashboard.personName),score + "");
                    playerHealths = (ArrayList<String>) m.get("Health");
                    playerHealths.set(playerNames.indexOf(dashboard.personName),life + "");

                    opponentXP.setText("XP: " +firestoreScoreList2.get(playerNames.indexOf(opponentNameTextview.getText().toString())));
                    if(playerHealths.get(playerNames.indexOf(opponentNameTextview.getText().toString())).equals(3 + "")){
                        opponentHealth.setImageResource(R.drawable.full);
                    }
                    else if(playerHealths.get(playerNames.indexOf(opponentNameTextview.getText().toString())).equals(2 + "")){
                        opponentHealth.setImageResource(R.drawable.two);
                    }
                    else if(playerHealths.get(playerNames.indexOf(opponentNameTextview.getText().toString())).equals(1 + "")){
                        opponentHealth.setImageResource(R.drawable.one);
                    }
                    else if(playerHealths.get(playerNames.indexOf(opponentNameTextview.getText().toString())).equals(0 + "")){
                        opponentHealth.setImageResource(0);

                    }
                    firestoreScoreList.clear();
                    firestoreScoreList.addAll(firestoreScoreList2);
                }
                docData.put("Score", firestoreScoreList2 );
                docData.put("Players", playerNames);
                docData.put("Health", playerHealths);

                firedata.collection("OneVOne").document(roomKey + "")
                        .update(docData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });
            }
        });
    }

    public void roundBegins(){

        i = 20;
        new CountDownTimer(360000000, 20000) {
            @Override
            public void onTick(long millisUntilFinished) {
                new CountDownTimer(20000,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        timer.setText(i + "");
                        i--;
                    }

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onFinish() {
                        i = 20;
                        storingScore();
                        currentAnswer = "";
                        answer1.setBackgroundColor(80000000);
                        answer2.setBackgroundColor(80000000);
                        timer.setText( i + "");
                        j++;
                        if(j==questions.size()){
                            Intent z = new Intent(getApplicationContext(), result_squad_mode.class);
                            cancel();
                            startActivity(z);
                        }
                        else if(j<questions.size()){
                            waitingLoop();

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
                }.start();

            }

            @Override
            public void onFinish() {
                Intent i = new Intent(getApplicationContext(), dashboard.class);
                startActivity(i);
            }
        }.start();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public  void dialogueBox(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.squad_mode_death_dialogue_box,null);
        CardView revive = (CardView)mView.findViewById(R.id.squadDeath);

        CardView death = (CardView)mView.findViewById(R.id.cardviewjoinroomcopy);
        TextView coins = mView.findViewById(R.id.textView);

        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        revive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                revive.setCardBackgroundColor(Color.rgb(79,195,247));
                new CountDownTimer(1000, 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }
                    @Override
                    public void onFinish() {
                        revive.setCardBackgroundColor(0);
                        alertDialog.dismiss();
                        life = 3;
                        health.setImageResource(R.drawable.full_health);
                        right_ans_score = right_ans_score - 10;
                        Coins.setText("Coins: " + right_ans_score + "");

                    }
                }.start();
            }
        });
        death.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                death.setCardBackgroundColor(Color.rgb(79,195,247));
                new CountDownTimer(1000, 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                        death.setCardBackgroundColor(0);
                        alertDialog.dismiss();
                        Intent i = new Intent(getApplicationContext(), dashboard.class);
                        startActivity(i);
                    }
                }.start();

            }
        });
        alertDialog.show();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void displayMessage(int message){

        if(message == 0 && currentAnswer.equals("")){
            currentAnswer = "answered";
            if(answer1.getText().toString().equals("A. "+answers.get(j))  ){
                heroWins();
                right_ans_score++;

                answer1.setBackgroundColor(getResources().getColor(R.color.lightBlue));
                score = score + (Integer.parseInt(timer.getText().toString()))*50;
                XP.setText("XP: " +score);
                Coins.setText("Coins: " + right_ans_score + "");
                answer1.setTextSize(16);

            }
            else{
                villyWins();

                answer1.setBackgroundColor(getResources().getColor(R.color.red));
                wrong_ans_score++;
                score = score - (Integer.parseInt(timer.getText().toString()))*50;

                life = life -1;
                if(life == 2){
                    health.setImageResource(R.drawable.halffinal2);
                }
                else if (life == 1 ){
                    health.setImageResource(R.drawable.lowhealthfinal2);
                }
                else if(life == 0){
                    // Toast.makeText(this, "you died", Toast.LENGTH_SHORT).show();
                    dialogueBox();
                }

                XP.setText("XP: " +score);
                Coins.setText("Coins: " + right_ans_score + "");
            }
            storingScore();
        }
        else if(message ==1 && currentAnswer.equals("")){
            currentAnswer = "answered";
//            storingAnswerState();
            if(answer2.getText().toString().equals("B. "+answers.get(j)) ){
                heroWins();


                answer2.setBackgroundColor(getResources().getColor(R.color.lightBlue));
                score = score + (Integer.parseInt(timer.getText().toString()))*50;
                right_ans_score++;


                XP.setText("XP: " +score);
                Coins.setText("Coins: " + right_ans_score + "");
                answer1.setTextSize(16);

            }
            else{
                answer2.setBackgroundColor(getResources().getColor(R.color.red));
                villyWins();
                wrong_ans_score++;
                score = score - (Integer.parseInt(timer.getText().toString()))*50;

                life = life -1;
                if(life == 2){
                    health.setImageResource(R.drawable.halffinal2);
                }
                else if (life == 1 ){
                    health.setImageResource(R.drawable.lowhealthfinal2);
                }
                else if(life == 0){
                    //  Toast.makeText(this, "you died", Toast.LENGTH_SHORT).show();
                    dialogueBox();
                }
                XP.setText("XP: " +score);
                Coins.setText("Coins: " + right_ans_score + "");
            }
            storingScore();
        }
    }

}