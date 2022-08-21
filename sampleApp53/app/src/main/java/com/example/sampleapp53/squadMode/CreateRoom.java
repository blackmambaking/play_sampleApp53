package com.example.sampleapp53.squadMode;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapterForSquadRoom;
import com.example.sampleapp53.basicLayout.dashboard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
public class CreateRoom extends AppCompatActivity {

    Map<String, Object> docData;
    Map<String, Object> docData2;
    Map<String, Object> docData3;
    FirebaseFirestore firedata;
    public  EditText playerName;
    ArrayList<String> playerNames;
    ArrayList<String> data;
    CountDownTimer timer;
    adapterForSquadRoom adapterForSquadRoom;
    public  TextView room_key;
    ArrayList<String> hostName;
    ArrayList<String> firedataPlayerList;
    public static ArrayList<String> firedataPlayerList2;
    String gameStart;
    int roomKey = 0;
    TextView instruction;
    ArrayList <String> GameState;
    TextView play;

    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squad);
        Random rn = new Random();
        room_key = findViewById(R.id.roomKey123);
        docData= new HashMap<>();
        instruction = findViewById(R.id.textView49);
        instruction.bringToFront();
        docData2= new HashMap<>();
        docData3= new HashMap<>();
        firedata  = FirebaseFirestore.getInstance();
        gameStart = "";
        GameState = new ArrayList<>();
        hostName = new ArrayList<>();
        firedataPlayerList = new ArrayList<>();
        playerNames = new ArrayList<>();
        play = findViewById(R.id.squadPlayButton);
        if(getIntent().getStringExtra("mode").equals("createRoom")){
            roomKey = rn.nextInt(9999);
            room_key.setText("Key : "+ roomKey);
            hostName.add(dashboard.personName);
            docData.put("Players", hostName );
            docData.put("GameState", "wait" );

            firedata.collection("SquadRooms").document(roomKey + "")
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
            Task<DocumentSnapshot> task = firedata.collection("SquadRooms")
                    .document(roomKey + "")
                    .get();
            task.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(@NotNull DocumentSnapshot documentSnapshot) {

                    DocumentSnapshot d = documentSnapshot;
                    Map<String, Object> m = d.getData();

                    firedataPlayerList = (ArrayList<String>) m.get("Players");
                    playerNames.addAll(firedataPlayerList);

                    adapterForSquadRoom = new adapterForSquadRoom(playerNames);
                    RecyclerView recyclerView = findViewById(R.id.recyclerView2);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(adapterForSquadRoom);
                }
            });

        }
        else if(getIntent().getStringExtra("mode").equals("joinRoom")){
            roomKey = Integer.parseInt(getIntent().getStringExtra("joinRoomKey"));
            room_key.setText("Key : "+ roomKey);
            Task<DocumentSnapshot> task2 = firedata.collection("SquadRooms")
                    .document(roomKey + "")
                    .get();
            task2.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(@NotNull DocumentSnapshot documentSnapshot) {
                    new CountDownTimer(5000, 5000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            DocumentSnapshot d = documentSnapshot;
                            Map<String, Object> m = d.getData();

                            if(m != null){

                                firedataPlayerList = (ArrayList<String>) m.get("Players");
                                playerNames.addAll(firedataPlayerList);
                                playerNames.add(dashboard.personName);
                                adapterForSquadRoom = new adapterForSquadRoom(playerNames);
                                RecyclerView recyclerView = findViewById(R.id.recyclerView2);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                recyclerView.setAdapter(adapterForSquadRoom);
                                docData2.put("Players", playerNames );
                                firedata.collection("SquadRooms").document(roomKey + "")
                                        .set(docData2)
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
                        }
                        @Override
                        public void onFinish() {
                        }
                    }.start();
                }
            });
        }
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameStart = "start";
                docData3.put("GameState", "start");
                docData3.put("Players", firedataPlayerList2);
                firedata.collection("SquadRooms").document(roomKey + "")
                        .set(docData3)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                            }
                        });
            }
        });
        i =0;
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        data = new ArrayList<>();
        new CountDownTimer(3600000, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Task<DocumentSnapshot> task4 = firedata.collection("SquadRooms")
                        .document(roomKey + "")
                        .get();

                task4.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        DocumentSnapshot d = documentSnapshot;
                        Map<String, Object> m = d.getData();
                        if(m != null){
                            firedataPlayerList2 = (ArrayList<String>) m.get("Players");

                                adapterForSquadRoom = new adapterForSquadRoom(firedataPlayerList2);
                                RecyclerView recyclerView = findViewById(R.id.recyclerView2);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                recyclerView.setAdapter(adapterForSquadRoom);
                        }
                        else{
                        }
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
                            try {
                                if(Objects.equals((String) m.get("GameState"), "start")){
                                    Intent i = new Intent(getApplicationContext(), MainActivityForSqadPlay.class);
                                    i.putExtra("ROOM_KEY", roomKey+"");
                                    i.putExtra("PLAYER_NAME", dashboard.personName);
                                    cancel();
                                    startActivity(i);
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
                Intent i = new Intent(getApplicationContext(), dashboard.class);
                startActivity(i);
            }
        }.start();
    }
}