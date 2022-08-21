package com.example.sampleapp53.dashboardFragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.sampleapp53.R;
import com.example.sampleapp53.basicLayout.dashboard;
import com.example.sampleapp53.myDBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class userProfile extends Fragment {
    FloatingActionButton fab;
    public static int coins = 0;
    public static int XP = 0;
    Cursor cursor;
    SQLiteDatabase db;
    myDBHelper helper;
    TextView Coins;
    TextView xp;
    TextView userName;
    TextView userEmail;
    ImageView userPic;
    public userProfile() {
        // Required empty public constructor
    }


    public static userProfile newInstance(String param1, String param2) {
        userProfile fragment = new userProfile();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_user_profile, container, false);
        Coins = v.findViewById(R.id.Coins_number_Profile);
        xp = v.findViewById(R.id.XP_number_Profile);
        userName = v.findViewById(R.id.userNameProfile);
        userEmail = v.findViewById(R.id.textView25);
        userPic = v.findViewById(R.id.userPic);
        userName.setText(dashboard.personName );
        userEmail.setText(dashboard.personEmail);
        Coins.setText(coins + "");
        xp.setText(XP +"");
        userPic.setImageResource(R.drawable.user);


//        fab = (FloatingActionButton) v. findViewById(R.id.floatingActionButton2);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            Intent i = new Intent(getContext(), Scanning.class);
//            startActivity(i);
//            }
//        });
        return v;
    }
}