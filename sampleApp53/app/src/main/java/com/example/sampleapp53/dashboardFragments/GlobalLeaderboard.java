package com.example.sampleapp53.dashboardFragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapterForGlobalLeaderBoard;
import com.example.sampleapp53.basicLayout.dashboard;
import com.example.sampleapp53.modelForGloballeaderboard;
import com.example.sampleapp53.myDBHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalLeaderboard extends Fragment {
    public static ArrayList<modelForGloballeaderboard>players;
    public static ArrayList<modelForGloballeaderboard>playersSorted;
    FirebaseFirestore firedata;
    adapterForGlobalLeaderBoard adapterForGlobalLeaderBoard;
    RecyclerView recyclerView;
    Map<String, Object> docData;
    Cursor cursor;
    SQLiteDatabase db;
    ArrayList<Long> help;
    ArrayList<String> help2;

    myDBHelper helper;
    int coins = 0;
    int XP = 0;


    public GlobalLeaderboard() {
        // Required empty public constructor
    }

    public static GlobalLeaderboard newInstance(String param1, String param2) {
        GlobalLeaderboard fragment = new GlobalLeaderboard();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        players = new ArrayList<>();
        playersSorted = new ArrayList<>();
        firedata  = FirebaseFirestore.getInstance();
        adapterForGlobalLeaderBoard = new adapterForGlobalLeaderBoard(players);
        docData= new HashMap<>();
        help = new ArrayList<>();
        help2 = new ArrayList<>();

        helper = new myDBHelper(getContext());
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT COINS, XP FROM COINS_AND_XP", new String[]{});
        if(cursor.getCount() !=0){
            cursor.moveToFirst();
            coins = cursor.getInt(0);
            XP = cursor.getInt(1);
        }
        else{
            coins = 0;
            XP = 0;
        }
        docData.put("Email",dashboard.personEmail );
        docData.put("XP", XP);
        firedata.collection("Global Leaderboard").document(dashboard.personEmail + "")
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
        firedata.collection("Global Leaderboard").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list){
                            modelForGloballeaderboard obj = d.toObject(modelForGloballeaderboard.class);
                            players.add(obj);
                        }
                        for(int i = 0; i< players.size(); i++){
                            help.add(players.get(i).getXP());
                            help2.add(players.get(i).getEmail());
                        }

                        help.sort(null);
                        Collections.reverse(help);

                        View v = getView();
                        recyclerView = (RecyclerView) v.findViewById(R.id.globalLeaderRecycler);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(adapterForGlobalLeaderBoard);

                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_global_leaderboard, container, false);
        if(players.size() != 0){
            adapterForGlobalLeaderBoard = new adapterForGlobalLeaderBoard(players);
            recyclerView = (RecyclerView) v.findViewById(R.id.globalLeaderRecycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapterForGlobalLeaderBoard);
        }
        return v;
    }
}