package com.example.sampleapp53.squadMode;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapter;
import com.example.sampleapp53.adapterForCardView.adapterForChapterSelection;
import com.example.sampleapp53.adapterForCardView.squadLeaderBoardAdapter;
import com.example.sampleapp53.basicLayout.dashboard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class MainActivityForSqadPlay extends AppCompatActivity {
    public static TextView question;
    public static TextView answer1;
    public static TextView answer2;
    static TextView XP;
    public static TextView Coins;
    public static RecyclerView recyclerView;
    public static VideoView gameVideo;
    static Cursor cursor;
    squadLeaderBoardAdapter squadLeaderBoardAdapter;
    int squadScore = 0;
    SQLiteDatabase database;
    public FirebaseFirestore firedata;
    ArrayList <String> playerNames;
    GestureDetectorCompat gestureDetectorCompat = null;
    String roomKey;
    String playerName;
    public static ArrayList <String> questions;
    public static ArrayList <String> answers ;
    public static ArrayList<String> helper;
    public static ArrayList<String> helper2;
    public static  ArrayList <String> wrongAnswers ;
    public static  ArrayList <String> scoreStore ;
    public static ArrayList<String> firestoreScoreList;
    public static ArrayList<String> firestoreScoreList2;
    public static ArrayList<String> firestoreScoreList3;
    public static ArrayList <String> peopleAnswered;
    public static ArrayList <String> peopleAnswered2;
    public static TextView timer;
    String questionChange = "no";
    Map<String, Object> docData3;
    String currentAnswer = "";
    Map<String, Object> docData;
    Map<String, Object> docData2;
    public static int j = 0;
    int i = 0;
    int startingCOunter = 3;
    ImageView health;
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

                    waitingLoop();
                }
            });
        }
    }
    @Override
    protected void onDestroy() {
        j = 0;
        super.onDestroy();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_sqad_play);
        firedata  = FirebaseFirestore.getInstance();
        health= findViewById(R.id.healthxyz);
        question = findViewById(R.id.questionxyz);
        answer1 = findViewById(R.id.answer1xyz);
        docData= new HashMap<>();
        docData3= new HashMap<>();
        answer2 = findViewById(R.id.answer2xyz);
        gameVideo = findViewById(R.id.videoViewxyz);
        timer = findViewById(R.id.timer);
        playerNames = new ArrayList<>();
        scoreStore = new ArrayList<>();
        questions = new ArrayList<>();
        answers= new ArrayList<>();
        wrongAnswers = new ArrayList<>();

        roomKey = getIntent().getStringExtra("ROOM_KEY");
        playerName = getIntent().getStringExtra("PLAYER_NAME");
        firestoreScoreList = new ArrayList<>();
        firestoreScoreList2 = new ArrayList<>();
        firestoreScoreList3 = new ArrayList<>();
        peopleAnswered = new ArrayList<>();

        for(int g = 0; g<CreateRoom.firedataPlayerList2.size(); g++){
            firestoreScoreList3.add(0 + "");
//            peopleAnswered.add("no");
        };
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        Coins = findViewById(R.id.Coinsxyz);
        XP = findViewById(R.id.XPxyz);
        Coins.setTextColor(getResources().getColor(R.color.gold));
        XP.setTextColor(getResources().getColor(R.color.gold));
        timer.setTextColor(getResources().getColor(R.color.gold));
        question.setTextColor(getResources().getColor(R.color.gold));
        question.bringToFront();
        answer1.bringToFront();
        answer2.bringToFront();
        XP.bringToFront();
        Coins.bringToFront();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
//        matching_heading = findViewById(R.id.matching_heading);
        new CountDownTimer(36000000, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {
                storingScore();
            }

            @Override
            public void onFinish() {
            }
        }.start();


        firedata  = FirebaseFirestore.getInstance();

        waitingLoop();
        docData3.put("Score", firestoreScoreList3);
//        docData3.put("People Answered", peopleAnswered);
        firedata.collection("SquadRooms").document(roomKey + "")
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

        Task<DocumentSnapshot> task2 = firedata.collection("SquadRooms")
                .document(roomKey + "")
                .get();
        task2.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                DocumentSnapshot d = documentSnapshot;
                Map<String, Object> m = d.getData();

                if(m != null){
                    playerNames = (ArrayList<String>) m.get("Players");
                    firestoreScoreList = new ArrayList<>();
                    firestoreScoreList = (ArrayList<String>) m.get("Score");
                    squadLeaderBoardAdapter = new squadLeaderBoardAdapter(playerNames);
                    recyclerView = findViewById(R.id.leaderboardSquadRecylcer);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(squadLeaderBoardAdapter);
                    squadLeaderBoardAdapter.notifyDataSetChanged();
                }
            }
        });

        detectSwipeSquad gestureListener = new detectSwipeSquad();
        gestureListener.setActivity(this);
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);



        Task<DocumentSnapshot> task = firedata.collection("Books")
                .document(adapter.book_selected_byUser)
                .collection("Chapters")
                .document(adapterForChapterSelection.finalChapterSelected)
                .get();
        task.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                DocumentSnapshot d = documentSnapshot;
                Map<String, Object> m2 = d.getData();
                if(m2 != null){
                    questions = (ArrayList<String>) m2.get("questions");
                    answers = (ArrayList<String>) m2.get("answers");
                    wrongAnswers = (ArrayList<String>) m2.get("wrong_answers");

                    answer1.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onClick(View v) {
                            if  (MainActivityForSqadPlay.j < MainActivityForSqadPlay.questions.size()){
                                displayMessage(0);
                            }
                        }
                    });
                    answer2.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onClick(View v) {
                            if( MainActivityForSqadPlay.j < MainActivityForSqadPlay.questions.size()){
                                displayMessage(1);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivityForSqadPlay.this, "No questions available!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(MainActivityForSqadPlay.this, "Error!", Toast.LENGTH_SHORT).show();
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
                MainActivityForSqadPlay.gameVideo.start();
                int i = rn.nextInt(2);
                question.setText(MainActivityForSqadPlay.questions.get(0));
                if(i == 0){
                   answer1.setText("A. "+MainActivityForSqadPlay.answers.get(0));
                   answer2.setText("B. "+MainActivityForSqadPlay.wrongAnswers.get(0));
                }
                else{
                   answer2.setText("B. "+MainActivityForSqadPlay.answers.get(0));
                   answer1.setText("A. "+MainActivityForSqadPlay.wrongAnswers.get(0));
                }
                roundBegins();
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
    public static <T extends Comparable<T>> void keySort(
            final List<T> key, List<?>... lists){
        // Create a List of indices
        List<Integer> indices = new ArrayList<Integer>();
        for(int i = 0; i < key.size(); i++)
            indices.add(i);

        // Sort the indices list based on the key
        Collections.sort(indices, new Comparator<Integer>(){
            @Override public int compare(Integer i, Integer j) {
                return key.get(i).compareTo(key.get(j));
            }
        });

        // Create a mapping that allows sorting of the List by N swaps.
        Map<Integer,Integer> swapMap = new HashMap<Integer, Integer>(indices.size());

        // Only swaps can be used b/c we cannot create a new List of type <?>
        for(int i = 0; i < indices.size(); i++){
            int k = indices.get(i);
            while(swapMap.containsKey(k))
                k = swapMap.get(k);

            swapMap.put(i, k);
        }

        // for each list, swap elements to sort according to key list
        for(Map.Entry<Integer, Integer> e : swapMap.entrySet())
            for(List<?> list : lists)
                Collections.swap(list, e.getKey(), e.getValue());
    }


    Random rn = new Random();
    public void storingScore(){

        Task<DocumentSnapshot> task3 = firedata.collection("SquadRooms")
                .document(roomKey + "")
                .get();
        task3.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                DocumentSnapshot d = documentSnapshot;
                Map<String, Object> m = d.getData();
                if(m != null){
                    firestoreScoreList2 = new ArrayList<>();
                    firestoreScoreList2 = (ArrayList<String>) m.get("Score");
                    firestoreScoreList2.set(playerNames.indexOf(playerName),squadScore + "");
                    firestoreScoreList.clear();
                    firestoreScoreList.addAll(firestoreScoreList2);
                    helper = new ArrayList<>();
                    helper2 = new ArrayList<>();


                    keySort(firestoreScoreList, playerNames);
                    for(int i = 0; i<playerNames.size();i++){
                        helper.add(playerNames.get(playerNames.size()-(1+i)));
                        helper2.add(firestoreScoreList.get(playerNames.size()-(1+i)));
                    }


                    squadLeaderBoardAdapter = new squadLeaderBoardAdapter(helper);
                    recyclerView = findViewById(R.id.leaderboardSquadRecylcer);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(squadLeaderBoardAdapter);
                    squadLeaderBoardAdapter.notifyDataSetChanged();

                }

                docData.put("Score", firestoreScoreList2 );
                docData.put("Players", playerNames);

                firedata.collection("SquadRooms").document(roomKey + "")
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
//    public void checkingTimer(){
//        new CountDownTimer(3600000,2000){
//
//            @Override
//            public void onTick(long millisUntilFinished) {
//                if(questionChange.equals("no")){
//                    Task<DocumentSnapshot> task2 = firedata.collection("SquadRooms")
//                            .document(roomKey + "")
//                            .get();
//                    task2.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                        @Override
//                        public void onSuccess(DocumentSnapshot documentSnapshot) {
//
//                            DocumentSnapshot d = documentSnapshot;
//                            Map<String, Object> m = d.getData();
//
//                            if(m != null){
//                                peopleAnswered = new ArrayList<>();
//                                peopleAnswered = (ArrayList<String>) m.get("People Answered");
//                                for(int i = 0; i<peopleAnswered.size();i++){
//                                    if(peopleAnswered.get(i).equals("yes")){
//                                        questionChange = "yes";
//                                    }
//                                    else{
//                                        questionChange = "no";
//                                    }
//                                }
//                            }
//                        }
//                    });
//                }
//                if(questionChange.equals("yes")){
//                    cancel();
//                    questionChange = "no";
//                    docData3.clear();
//                    for(int g = 0; g<peopleAnswered.size(); g++){
//                        peopleAnswered.add("no");
//                    };
//                    docData3.put("People Answered", peopleAnswered);
//                    firedata.collection("SquadRooms").document(roomKey + "")
//                            .update(docData3)
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                }
//                            });
//
//                    roundBegins();
//                }
//
//            }
//            @Override
//            public void onFinish() {
//
//            }
//        }.start();
//    }
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
    public void roundBegins(){
//        checkingTimer();
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

                    @Override
                    public void onFinish() {
                        i = 20;
                        storingScore();
                        currentAnswer = "";
                        answer1.setBackgroundColor(80000000);
                        answer2.setBackgroundColor(80000000);
                        MainActivityForSqadPlay.timer.setText( i + "");
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
//    public void storingAnswerState(){
//        docData2= new HashMap<>();
//        Task<DocumentSnapshot> task3 = firedata.collection("SquadRooms")
//                .document(roomKey + "")
//                .get();
//        task3.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//
//                DocumentSnapshot d = documentSnapshot;
//                Map<String, Object> m = d.getData();
//                if(m != null){
//                    peopleAnswered2 = new ArrayList<>();
//                    peopleAnswered2 = (ArrayList<String>) m.get("People Answered");
//                    peopleAnswered2.set(playerNames.indexOf(playerName), "yes");
//
//                }
//                docData2.put("People Answered", peopleAnswered2 );
//                firedata.collection("SquadRooms").document(roomKey + "")
//                        .update(docData2)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                            }
//                        });
//            }
//        });
//    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void displayMessage(int message){

        if(message == 0 && currentAnswer.equals("")){
            currentAnswer = "answered";
//            storingAnswerState();

            if(answer1.getText().toString().equals("A. "+answers.get(j))  ){
                heroWins();
                right_ans_score++;

                score = score + 1000;
                answer1.setBackgroundColor(getResources().getColor(R.color.lightBlue));
                squadScore = squadScore + (Integer.parseInt(timer.getText().toString()))*50;
                XP.setText("XP: " +squadScore);
                Coins.setText("Coins: " + right_ans_score + "");
                answer1.setTextSize(16);

            }
            else{
                villyWins();

                answer1.setBackgroundColor(getResources().getColor(R.color.red));
                wrong_ans_score++;
                squadScore = squadScore - (Integer.parseInt(timer.getText().toString()))*50;
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
                    dialogueBox();
                }

                XP.setText("XP: " +squadScore);
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
                squadScore = squadScore + (Integer.parseInt(timer.getText().toString()))*50;
                right_ans_score++;
                score = score + 1000;

                XP.setText("XP: " +squadScore);
                Coins.setText("Coins: " + right_ans_score + "");
                answer1.setTextSize(16);

            }
            else{
                answer2.setBackgroundColor(getResources().getColor(R.color.red));
                villyWins();
                wrong_ans_score++;
                squadScore = squadScore - (Integer.parseInt(timer.getText().toString()))*50;
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
                    dialogueBox();
                }
                XP.setText("XP: " +squadScore);
                Coins.setText("Coins: " + right_ans_score + "");
            }
           storingScore();
        }
    }
}