package com.example.sampleapp53.adapterForCardView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.basicLayout.ChapterSelection;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
public class adapterForChapterSelection extends RecyclerView.Adapter <adapterForChapterSelection.viewHolder2>{

    public static String ch_score;
    ArrayList<String> chapterNames;

    public adapterForChapterSelection(ArrayList<String> chapterNames) {
        this.chapterNames = chapterNames;
    };
    @NonNull
    @NotNull
    @Override
    public adapterForChapterSelection.viewHolder2 onCreateViewHolder( ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.chapter_row, parent,false);
        return new viewHolder2(view);
    }

    @Override
    public void onBindViewHolder(adapterForChapterSelection.viewHolder2 holder, int position) {
        holder.chapterName.setText(chapterNames.get(position));
//        holder.chapterLevelImage.setImageResource(R.drawable.skull);
        if(ChapterSelection.cursor3.getCount() == 0){
            ch_score = "Score : " + 0;
        }
        else{
            ChapterSelection.cursor3.moveToFirst();
            for(int i =0; i<ChapterSelection.chapterNames.size();i++){
                ChapterSelection.cursor3.moveToFirst();
                for(int j = 0; j<ChapterSelection.cursor3.getCount(); j++){

                    if(chapterNames.get(position).equals(ChapterSelection.cursor3.getString(1))){
                        ch_score = "Score : " + ChapterSelection.cursor3.getInt(2);
                    }
                    else {
                        ch_score = "Score : " + 0;
                        if(ChapterSelection.cursor3.isLast()){

                        }
                        else{
                            ChapterSelection.cursor3.moveToNext();
                        }

                    }
                }
            }
        }

        holder.chapterScore.setText(ch_score);
    }

    public static String finalChapterSelected;
    @Override
    public int getItemCount() {
        return chapterNames.size();
    }

    public class viewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public  TextView chapterName;
        public TextView chapterScore;
        public CardView cardViewChapter;
        public ImageView chapterLevelImage;
        Context context;
        public viewHolder2( View itemView) {
            super(itemView);
            context = itemView.getContext();
            chapterName =  itemView.findViewById(R.id.chapterId);
            cardViewChapter =  itemView.findViewById(R.id.chapterRowCard);
            chapterScore = itemView.findViewById(R.id.chapterScoreId);
            chapterLevelImage = itemView.findViewById(R.id.chapterImageId);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

            cardViewChapter.setCardBackgroundColor(Color.rgb(79,195,247));
            finalChapterSelected = chapterName.getText().toString();
            Toast.makeText(context, "Select the mode of gaming", Toast.LENGTH_SHORT).show();
        }
    }
}
