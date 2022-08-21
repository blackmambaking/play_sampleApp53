package com.example.sampleapp53.adapterForCardView;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.GeneratingQuestions.Scanning;
import com.example.sampleapp53.R;
import com.example.sampleapp53.myDBHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class adapterForKeyWordsInnerRecycler extends RecyclerView.Adapter<adapterForKeyWordsInnerRecycler.viewHolder> {
    ArrayList<String> keyWords;
    ArrayList <String> selectedKeyWords;
    myDBHelper helper;
    SQLiteDatabase db;
    SQLiteDatabase database;
    Cursor cursor;
    int Position;

    public adapterForKeyWordsInnerRecycler(ArrayList<String> keyWords) {
        this.keyWords = keyWords;
    };
    @NonNull
    @NotNull
    @Override
    public adapterForKeyWordsInnerRecycler.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_for_row_keywords, parent,false);
        selectedKeyWords = new ArrayList<>();


        return new adapterForKeyWordsInnerRecycler.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull adapterForKeyWordsInnerRecycler.viewHolder holder, int position) {

            holder.words.setText(Scanning.listOfLists.get(adapterForKeyWords.itemPosition).get(position));
            Position = position;

    }

    @Override
    public int getItemCount() {
        return Scanning.listOfLists.get(adapterForKeyWords.itemPosition).size();
    }
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView words;
        public CardView cardView;

        Context context;
        public viewHolder( View itemView) {
            super(itemView);
            context = itemView.getContext();
            words =  itemView.findViewById(R.id.KeyWord);
            cardView = itemView.findViewById(R.id.keyWordSelectionCardView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            helper = new myDBHelper(context.getApplicationContext());
            database  = helper.getWritableDatabase();


            db = helper.getReadableDatabase();
            cursor = db.rawQuery("SELECT BLOCK_NAME, SUB_BLOCK, KEYWORDS FROM CAPTURE_INFO", new String[]{});
            if(cursor.getCount() != 0){
                cursor.moveToFirst();
                if(Scanning.blockName.getText().toString().equals(cursor.getString(0)) && Scanning.subTextBlocks.get(Scanning.listOfLists.indexOf(Scanning.listOfLists)).equals(cursor.getString(1)) && words.getText().toString().equals(cursor.getString(2))){
                    cardView.setCardBackgroundColor(Color.rgb(0,0,0));
                    String delete = "delete from "+ myDBHelper.TABLE_NAME_CAPTURE_INFO + " where KEYWORDS = " + "'" +words.getText().toString() + "'" ;
                    db.execSQL(delete);
                }
                else {
                    cardView.setCardBackgroundColor(Color.rgb(79,195,247));
                    helper.inserCaptureInfo(Scanning.blockName.getText().toString(),Scanning.subTextBlocks.get(Scanning.listOfLists.get(Position).indexOf(words.getText().toString())), words.getText().toString());
                }
            }else{
                cardView.setCardBackgroundColor(Color.rgb(79,195,247));
                helper.inserCaptureInfo(Scanning.blockName.getText().toString(),Scanning.subTextBlocks.get(Scanning.listOfLists.get(Position).indexOf(words.getText().toString())), words.getText().toString());
            }
        }
    }
}
