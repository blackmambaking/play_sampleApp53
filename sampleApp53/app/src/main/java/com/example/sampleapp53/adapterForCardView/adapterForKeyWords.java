package com.example.sampleapp53.adapterForCardView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.GeneratingQuestions.Scanning;
import com.example.sampleapp53.GeneratingQuestions.SelectingKeyWords;
import com.example.sampleapp53.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class adapterForKeyWords extends RecyclerView.Adapter<adapterForKeyWords.viewHolder> {

    public static List<List<String>> textBlocks;
    public static int itemPosition = 0;

    adapterForKeyWordsInnerRecycler adapterForKeyWordsInnerRecycler;
    RecyclerView recyclerView;
    public adapterForKeyWords(List<List<String>>textBlocks) {
        adapterForKeyWords.textBlocks = textBlocks;
    };
    @NonNull
    @NotNull
    @Override
    public adapterForKeyWords.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_keyword_selection, parent,false);
        return new adapterForKeyWords.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull adapterForKeyWords.viewHolder holder, int position) {
        itemPosition = position;
        adapterForKeyWordsInnerRecycler = new adapterForKeyWordsInnerRecycler((ArrayList<String>) Scanning.listOfLists.get(position));
        holder.words.setLayoutManager(new GridLayoutManager(SelectingKeyWords.recyclerView.getContext(), 4));
        holder.words.setAdapter(adapterForKeyWordsInnerRecycler);
    }

    @Override
    public int getItemCount() {
        return Scanning.listOfLists.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public RecyclerView words;


        Context context;
        public viewHolder( View itemView) {
            super(itemView);
            context = itemView.getContext();
            words =  itemView.findViewById(R.id.recyclerViewForKeyWords);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
