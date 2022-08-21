package com.example.sampleapp53.dashboardFragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapterForMarkedQuestions;
import com.example.sampleapp53.myDBHelper;

import java.util.ArrayList;


public class Favourites extends Fragment {
    myDBHelper helper;
    SQLiteDatabase db;
    public static Cursor cursor;
    RecyclerView recyclerView;

    public static Cursor cursor2;
    public static ArrayList<String> books;
    public static ArrayList <String> chapters;
    public static ArrayList<String> questions_number;
    adapterForMarkedQuestions adapterForMarkedQuestions;
    adapterForMarkedQuestions adapterForMarkedQuestions1;
    int p =1;

    public Favourites() {
        // Required empty public constructor
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper = new myDBHelper(getActivity());
        db = helper.getReadableDatabase();
        books = new ArrayList<>();
        chapters = new ArrayList<>();
        questions_number = new ArrayList<>();

        cursor = db.rawQuery("SELECT BOOK_NAME, CHAPTER_NAME FROM MARKED_QUESTIONS_ABSOLUTE", new String[]{});
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            for(int i = 0; i <= cursor.getCount();i++){
                if(!books.contains(cursor.getString(0))){
                    books.add(cursor.getString(0));
                    chapters.add(cursor.getString(1));

                }
                else if(!chapters.contains(cursor.getString(1))&&books.contains(cursor.getString(0)) ){
                    books.add(cursor.getString(0));
                    chapters.add(cursor.getString(1));
                }
                if(cursor.isLast()){

                }else{
                    cursor.moveToNext();
                }
            }
        }
        else{
           
        }
        helper = new myDBHelper(getActivity());

//
//        cursor2 = db.rawQuery("SELECT QUESTION, ANSWER, WRONG_ANSWER FROM MARKED_QUESTIONS_ABSOLUTE WHERE BOOK_NAME = " + "'"+adapterForMarkedQuestions.bookMarkedQuestions+"'" + " AND CHAPTER_NAME = " + "'"+adapterForMarkedQuestions.chapterMarkedQuestions+"'" , new String[]{});
//        cursor2.moveToFirst();


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_marked_questions, container, false);
        // Inflate the layout for this fragment
        if(books.size() != 0){
            adapterForMarkedQuestions = new adapterForMarkedQuestions(books);
            recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewMarkedQuestions);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapterForMarkedQuestions);
        }
        return v;
    }
}