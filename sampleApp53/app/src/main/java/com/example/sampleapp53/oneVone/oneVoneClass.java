package com.example.sampleapp53.oneVone;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.basicLayout.dashboard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class oneVoneClass extends AppCompatActivity {
TextView oneVoneKey;
    public static int key;
    Map<String, Object> docData;
    int num = 0;
    ArrayList <String> players;
    CountDownTimer timerForNewActivity;
    ArrayList <String> playersFirestore;
    Map<String, Object> docData2;
    ArrayList<String>ChallengeState;
    SeekBar seekBar;
    TextView percentage;
    FirebaseFirestore firestore;
    public static ArrayList<String> playerNames;
    CardView declare;
    CardView accept;
    String hero = "";
    String villy = "";
    EditText key2;
    ArrayList<Integer> questions;
    @Override
    protected void onDestroy() {
        docData2.put("","" );
        firestore.collection("OneVOne").document(key + "")
                .set(docData)
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
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_vone);
        oneVoneKey = findViewById(R.id.oneVoneKey);
        Random rn = new Random();
        percentage = findViewById(R.id.textView27);
        key2 = findViewById(R.id.editTextNumber2);
        ChallengeState = new ArrayList<>();
        ChallengeState.add("unaccepted");
        declare = findViewById(R.id.declareChallengeCard);
        accept = findViewById(R.id.acceptChallengeCard);
        key = rn.nextInt(99999);
        seekBar = findViewById(R.id.seekBar);
        questions = new ArrayList<Integer>();
        docData= new HashMap<>();
        docData2= new HashMap<>();
        oneVoneKey.setText(key +"");
        firestore  = FirebaseFirestore.getInstance();
        players = new ArrayList<>();
        playersFirestore = new ArrayList<>();
        playersFirestore.add(dashboard.personName);
        playerNames = new ArrayList<>();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept.setCardBackgroundColor(Color.rgb(72,202,228));
                Random rn3 = new Random();
                int p = rn3.nextInt(2);
                if(p == 0){
                    hero = "red";
                    villy = "white";
                }else if(p == 1){
                    hero = "white";
                    villy = "red";
                }
                Task<DocumentSnapshot> task = firestore.collection("OneVOne")
                        .document(key2.getText().toString() + "")
                        .get();
                task.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        DocumentSnapshot d = documentSnapshot;
                        Map<String, Object> m = d.getData();
                        if(m != null){
                            try {
                                players = (ArrayList<String>) m.get("Challenger");
                                if(!players.contains(dashboard.personName)){
                                    playerNames.add(dashboard.personName);
                                    playerNames.add(players.get(0));
                                    docData.put("ChallengeAccepter", playersFirestore);
                                    docData.put("Players", playerNames);
                                    docData.put("Hero", hero);
                                    docData.put("Villy", villy);
                                    ChallengeState.add("accepted");
                                    docData.put("ChallengeState", ChallengeState);
                                    docData.put("winnerOfQuestion", "");
                                    firestore.collection("OneVOne").document(key2.getText().toString() + "")
                                            .update(docData)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                     timerForNewActivity = new CountDownTimer(3600000, 100) {
                                                        @Override
                                                        public void onTick(long millisUntilFinished) {
                                                            Task<DocumentSnapshot> task3 = firestore.collection("OneVOne")
                                                                    .document(key2.getText().toString() + "")
                                                                    .get();
                                                            task3.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {

                                                                    DocumentSnapshot d = documentSnapshot;
                                                                    Map<String, Object> m = d.getData();
                                                                    if(m != null){
                                                                        try {
                                                                            ChallengeState = (ArrayList<String>) m.get("ChallengeState");
                                                                            assert ChallengeState != null;
                                                                            if(ChallengeState.contains("accepted")){
                                                                                timerForNewActivity.cancel();
                                                                                Intent i = new Intent(getApplicationContext(), MainActivityForOneVOne.class);
                                                                                i.putExtra("ROOM_KEY", key2.getText().toString() +"");
                                                                                i.putExtra("percentage", seekBar.getProgress()*10);

                                                                                startActivity(i);
                                                                            }

                                                                        } catch (Exception e) {
                                                                            e.printStackTrace();
                                                                            Toast.makeText(oneVoneClass.this, "error", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }
                                                                }
                                                            });
                                                        }

                                                        @Override
                                                        public void onFinish() {

                                                        }
                                                    }.start();


                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(oneVoneClass.this, "No Challenge Declared", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                                else{
                                    Toast.makeText(oneVoneClass.this, "Challenge from different ID!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(oneVoneClass.this, "No challenge for this key", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

        declare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                declare.setCardBackgroundColor(Color.rgb(72,202,228));
                docData2.put("Challenger", playersFirestore);
                docData2.put("ChallengeState", ChallengeState);
                docData2.put("Percentage of Questions", seekBar.getProgress()*10);

                firestore.collection("OneVOne").document(key + "")
                        .set(docData2)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(oneVoneClass.this, "Give your friend the key!", Toast.LENGTH_SHORT).show();
                                timerForNewActivity = new CountDownTimer(3600000, 1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        Task<DocumentSnapshot> task2 = firestore.collection("OneVOne")
                                                .document(key + "")
                                                .get();
                                        task2.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                                DocumentSnapshot d = documentSnapshot;
                                                Map<String, Object> m = d.getData();
                                                if(m != null){
                                                    try {
                                                        ChallengeState = (ArrayList<String>) m.get("ChallengeState");
                                                        assert ChallengeState != null;
                                                        if(ChallengeState.contains("accepted")){
                                                            Intent i = new Intent(getApplicationContext(), MainActivityForOneVOne.class);
                                                            i.putExtra("ROOM_KEY", key +"");
                                                            startActivity(i);
                                                            cancel();
                                                        }
                                                        else{
                                                            Toast.makeText(oneVoneClass.this, "Waiting for acceptance", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } catch (Exception e) {

                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        });
                                    }

                                    @Override
                                    public void onFinish() {

                                    }
                                }.start();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(oneVoneClass.this, "No Challenge Declared", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
        seekBar.setMin(1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                num = seekBar.getProgress();
                percentage.setText(num*10 + "%");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });





        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
    }

}