package com.example.sampleapp53.squadMode;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapterForSquadRoom;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JoinRoom extends AppCompatActivity {

public static EditText roomkey;
TextView submit;
    Map<String, Object> docData;
    ArrayList<String> data;
    ArrayList<String> playerNames;
    adapterForSquadRoom adapterForSquadRoom;
    FirebaseFirestore firedata;
    TextView saveName;
    String name;
    public static ArrayList<String> GameState;
    ArrayList <String> firedataPlayerList;
    public static EditText playerName;
    int i;
    int roomKey =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room);
        roomkey = findViewById(R.id.editTextNumber);
        submit = findViewById(R.id.textViewxyz13);
        docData= new HashMap<>();
        firedata  = FirebaseFirestore.getInstance();
        saveName = findViewById(R.id.textViewxyz22);
        playerName = findViewById(R.id.playerNamexyz);
        data = new ArrayList<>();
        GameState = new ArrayList<>();
        firedataPlayerList = new ArrayList<>();
        playerNames = new ArrayList<>();
       new CountDownTimer(3600000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(i!=0){
                    Task<DocumentSnapshot> task = firedata.collection("SquadRooms")
                            .document(roomKey + "")
                            .get();
                    task.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            DocumentSnapshot d = documentSnapshot;
                            Map<String, Object> m = d.getData();

                            if(m != null){
                                firedataPlayerList = (ArrayList<String>) m.get("Players");

                                adapterForSquadRoom = new adapterForSquadRoom(firedataPlayerList);
                                RecyclerView recyclerView = findViewById(R.id.joinRoomRecycler);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                recyclerView.setAdapter(adapterForSquadRoom);
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
                                    GameState = (ArrayList<String>) m.get("GameState");
                                    if(GameState.contains("start")){
                                        cancel();
                                        Intent i = new Intent(getApplicationContext(), MainActivityForSqadPlay.class);
                                        i.putExtra("ROOM_KEY", roomKey +"");
                                        i.putExtra("PLAYER_NAME", playerName.getText().toString());
                                        startActivity(i);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
            @Override
            public void onFinish() {

            }
        }.start();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                roomKey = Integer.parseInt(roomkey.getText().toString());
                Task<DocumentSnapshot> task = firedata.collection("SquadRooms")
                        .document(roomKey + "")
                        .get();
                task.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        new CountDownTimer(5000, 5000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                DocumentSnapshot d = documentSnapshot;
                                Map<String, Object> m = d.getData();

                                if(m != null){
                                    firedataPlayerList = (ArrayList<String>) m.get("Players");

                                    adapterForSquadRoom = new adapterForSquadRoom(firedataPlayerList);
                                    RecyclerView recyclerView = findViewById(R.id.joinRoomRecycler);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    recyclerView.setAdapter(adapterForSquadRoom);
                                }
                            }
                            @Override
                            public void onFinish() {
                            }
                        }.start();
                    }
                });
            }
        });

        saveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 name = playerName.getText().toString();
                Task<DocumentSnapshot> task = firedata.collection("SquadRooms")
                        .document(roomKey + "")
                        .get();
                task.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        DocumentSnapshot d = documentSnapshot;
                        Map<String, Object> m = d.getData();
                        if(m != null){
                            playerNames = (ArrayList<String>) m.get("Players");
                            playerNames.add(name);
                        }
                        else{
                            playerNames.add(name);
                        }
                        docData.put("Players",playerNames );
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
                                        Toast.makeText(JoinRoom.this, "Room not found!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
            }
        });
    }
}