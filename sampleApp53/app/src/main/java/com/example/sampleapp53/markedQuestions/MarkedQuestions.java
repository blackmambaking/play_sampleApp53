package com.example.sampleapp53.markedQuestions;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapterForMarkedQuestions;
import com.example.sampleapp53.myDBHelper;

import java.util.ArrayList;

public class MarkedQuestions extends AppCompatActivity {
    myDBHelper helper;
    SQLiteDatabase db;
    public static Cursor cursor;
    public static Cursor cursor2;
    public static ArrayList<String> books;
    public static ArrayList <String> chapters;
    public static ArrayList<String> questions_number;
    adapterForMarkedQuestions adapterForMarkedQuestions1;
    int p =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marked_questions);
        helper = new myDBHelper(this);
        db = helper.getReadableDatabase();
        books = new ArrayList<>();
        chapters = new ArrayList<>();
        questions_number = new ArrayList<>();
        adapterForMarkedQuestions adapterForMarkedQuestions;
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
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }


        helper = new myDBHelper(this);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

//
//        cursor2 = db.rawQuery("SELECT QUESTION, ANSWER, WRONG_ANSWER FROM MARKED_QUESTIONS_ABSOLUTE WHERE BOOK_NAME = " + "'"+adapterForMarkedQuestions.bookMarkedQuestions+"'" + " AND CHAPTER_NAME = " + "'"+adapterForMarkedQuestions.chapterMarkedQuestions+"'" , new String[]{});
//        cursor2.moveToFirst();

        if(books.size() != 0){
            adapterForMarkedQuestions = new adapterForMarkedQuestions(books);
            RecyclerView recyclerView = findViewById(R.id.recyclerViewMarkedQuestions);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(adapterForMarkedQuestions);
        }


    }
}