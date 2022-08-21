package com.example.sampleapp53.adapterForCardView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.dashboardFragments.Favourites;
import com.example.sampleapp53.markedQuestions.MainActivityForMarkedQuestions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class adapterForMarkedQuestions extends RecyclerView.Adapter<adapterForMarkedQuestions.viewHolder>{
    ArrayList<String> books;
    public static String bookMarkedQuestions;
    public static String chapterMarkedQuestions;
    public static Cursor cursor;
    public static SQLiteDatabase db;
    public static com.example.sampleapp53.myDBHelper myDBHelper;
    ArrayList<String> chapters;
    public adapterForMarkedQuestions(ArrayList<String> books) {
        this.books = books;

    };
    @NonNull
    @NotNull
    @Override
    public adapterForMarkedQuestions.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.markedquestion_row, parent,false);
        return new adapterForMarkedQuestions.viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull adapterForMarkedQuestions.viewHolder holder, int position) {
        holder.book.setText(books.get(position));
        holder.chapter.setText(Favourites.chapters.get(position));
//        holder.questions.setText(47 + "");

        bookMarkedQuestions = holder.book.getText().toString();
        chapterMarkedQuestions = holder.chapter.getText().toString();
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView book;
        public TextView chapter;
        public TextView questions;


        Context context;
        public viewHolder( View itemView) {
            super(itemView);
            context = itemView.getContext();
            book =  itemView.findViewById(R.id.textView23);
            chapter=  itemView.findViewById(R.id.textView24);
//            questions = itemView.findViewById(R.id.textView48);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemView.setBackgroundColor(Color.rgb(0,128,128));
            new CountDownTimer(1000, 500) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    itemView.setBackgroundResource(R.drawable.chapterback);
                    Intent intent;
                    intent =  new Intent(context, MainActivityForMarkedQuestions.class);


                    context.startActivity(intent);
                }
            }.start();





        }
    }
}
