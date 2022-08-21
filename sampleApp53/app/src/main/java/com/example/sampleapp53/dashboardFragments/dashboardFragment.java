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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapter;
import com.example.sampleapp53.myDBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link dashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class dashboardFragment extends Fragment {
com.example.sampleapp53.adapterForCardView.adapterForContactsList adapterForContactsList;
   public static RecyclerView recyclerView;

    public static ArrayList<String> bookNames;
    myDBHelper helper;
    public static Cursor cursor;
    SQLiteDatabase db;
    public static Cursor cursor2;
    public static String book;
    public static adapter adapter;
    ImageView img;
    FloatingActionButton fab;
    TextView txt;

    public static Cursor cursor3;

    public static String personName;



    public dashboardFragment() {
        // Required empty public constructor
    }

    public static dashboardFragment newInstance(String param1, String param2) {
        dashboardFragment fragment = new dashboardFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new myDBHelper(getContext());
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT BOOK, SCORE FROM BOOK_SCORES WHERE BOOK = " + "'"+ adapter.book_selected_byUser+"'", new String[]{});
        cursor.moveToFirst();
        cursor2 = db.rawQuery("SELECT BOOK_NAME FROM DASHBOARD_BOOKS", new String[]{});
        cursor3 = db.rawQuery("SELECT BOOK, CHAPTER_NAME, SCORE FROM CHAPTER_SCORES", new String[]{});
        if(cursor3.getCount() != 0){
            cursor3.moveToFirst();
        }
        bookNames = new ArrayList<>();
        adapter = new adapter(bookNames);

    }

    @Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        fab = (FloatingActionButton) v. findViewById(R.id.floatingActionButton8);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedFragment = null;
                selectedFragment = new addBooksFragment();
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, new addBooksFragment());
                fragmentTransaction.commit();
            }
        });

        if(cursor2.getCount() != 0){
            img = (ImageView) v.findViewById(R.id.imageView15);
            img.setImageResource(0);
            txt = (TextView) v.findViewById(R.id.textView50);
            txt.setText("");
            cursor2.moveToFirst();
            for(int i =0; i< cursor2.getCount(); i++) {
                bookNames.add(cursor2.getString(0));
                if (cursor2.isLast()) {
                    recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewdashboard);
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    recyclerView.setAdapter(adapter);
                } else {
                    cursor2.moveToNext();
                }
            }
        }

        return v;
    }
}