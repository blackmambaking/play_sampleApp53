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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.basicLayout.ChapterSelection;
import com.example.sampleapp53.dashboardFragments.dashboardFragment;
import com.example.sampleapp53.myDBHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter < adapter.viewHolder>  {

    public static String book_score;
    ArrayList<String> bookNames;
    myDBHelper helper;
    SQLiteDatabase db;

    public static String book_selected_byUser;
    public adapter(ArrayList<String> bookNames) {
        this.bookNames = bookNames;
    }

    @NotNull
    @Override
    public adapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row, parent,false);

        return new adapter.viewHolder(view);
    }

    int totalBookScore =0;

    @Override
    public void onBindViewHolder(adapter.viewHolder holder, int position) {
        String bookName = bookNames.get(position);
        holder.bookName.setText(bookName);
        if(dashboardFragment.cursor3.getCount() == 0){
            book_score = "" + 0;
        }
        else{
            dashboardFragment.cursor3.moveToFirst();
            for(int i =0; i<dashboardFragment.bookNames.size();i++){
                dashboardFragment.cursor3.moveToFirst();
                 totalBookScore =0;
                for(int j = 0; j<dashboardFragment.cursor3.getCount(); j++){

                    if(dashboardFragment.bookNames.get(position).equals(dashboardFragment.cursor3.getString(0))){
                        totalBookScore = totalBookScore + dashboardFragment.cursor3.getInt(2);
                        if(dashboardFragment.cursor3.isLast()){

                        }
                        else{
                            dashboardFragment.cursor3.moveToNext();
                        }
                    }
                    else {
                        book_score = "" + 0;
                        if(dashboardFragment.cursor3.isLast()){

                        }
                        else{
                            dashboardFragment.cursor3.moveToNext();
                        }

                    }
                }
                book_score = Integer.toString(totalBookScore);
            }
        }
        holder.bookScore.setText("Score : "+ book_score);
        holder.levelImage.setImageResource(R.drawable.neetpic);
    }
    @Override
    public int getItemCount() {
        return bookNames.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Cursor cursor2;
        public TextView bookName;
        public TextView bookScore;
        public ImageView levelImage;
        public ImageView cross;
        public CardView cardView;
        Context context;
        public viewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            bookName =  itemView.findViewById(R.id.KeyWord);
            bookScore =  itemView.findViewById((R.id.bookScoreId));
            levelImage = itemView.findViewById(R.id.bookImage);
            cardView = itemView.findViewById(R.id.dashboardBookCard);
            cross = itemView.findViewById(R.id.imageView3);
            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helper = new myDBHelper(context.getApplicationContext());
                    db = helper.getReadableDatabase();
                    String delete = "delete from "+ myDBHelper.TABLE_NAME_SELECTED_BOOKS + " where BOOK_NAME = " + "'" +bookName.getText().toString() + "'" ;
                    db.execSQL(delete);
                    dashboardFragment.bookNames.clear();

                    adapterForBookSelection.selectedBooks.remove(bookName.getText().toString());
                    cursor2 = db.rawQuery("SELECT BOOK_NAME FROM DASHBOARD_BOOKS", new String[]{});

                    if(cursor2.getCount() != 0){
                        cursor2.moveToFirst();
                        for(int i =0; i< cursor2.getCount(); i++) {
                            dashboardFragment.bookNames.add(cursor2.getString(0));
                            if (cursor2.isLast()) {
                                dashboardFragment.recyclerView.setAdapter(null);
                                dashboardFragment.recyclerView.setLayoutManager(null);
                                dashboardFragment.recyclerView.invalidate();
                                dashboardFragment.recyclerView.setAdapter(dashboardFragment.adapter);
                                dashboardFragment.recyclerView.setLayoutManager(new GridLayoutManager(dashboardFragment.recyclerView.getContext(), 2));
                                dashboardFragment.adapter.notifyDataSetChanged();
                            } else {
                                cursor2.moveToNext();
                            }
                        }
                    }
                    else {
                        dashboardFragment.recyclerView.setAdapter(null);
                        dashboardFragment.recyclerView.setLayoutManager(null);
                        dashboardFragment.recyclerView.invalidate();
                        dashboardFragment.recyclerView.setAdapter(dashboardFragment.adapter);
                        dashboardFragment.recyclerView.setLayoutManager(new GridLayoutManager(dashboardFragment.recyclerView.getContext(), 2));
                        dashboardFragment.adapter.notifyDataSetChanged();
                    }
                }
            });
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            cardView.setCardBackgroundColor(Color.rgb(79,195,247));
            book_selected_byUser =bookName.getText().toString();
            new CountDownTimer(1000, 500) {
                @Override
                public void onTick(long millisUntilFinished) {
                }
                @Override
                public void onFinish() {
                    cardView.setCardBackgroundColor(Color.WHITE);
                    Intent intent;
                    intent =  new Intent(context, ChapterSelection.class);
                    intent.putExtra("bookSelected", book_selected_byUser);
                    context.startActivity(intent);
                }
            }.start();
        }
    }
}
