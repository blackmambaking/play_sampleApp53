package com.example.sampleapp53.adapterForCardView;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.dashboardFragments.GlobalLeaderboard;
import com.example.sampleapp53.modelForGloballeaderboard;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class adapterForGlobalLeaderBoard extends RecyclerView.Adapter < adapterForGlobalLeaderBoard.viewHolder>{

    ArrayList<modelForGloballeaderboard> players;
    public adapterForGlobalLeaderBoard(ArrayList<modelForGloballeaderboard> players) {
        this.players = players;
    }
    @NonNull
    @NotNull
    @Override
    public adapterForGlobalLeaderBoard.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.global_leaderboard_row, parent,false);

        return new adapterForGlobalLeaderBoard.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull adapterForGlobalLeaderBoard.viewHolder holder, int position) {
        holder.player.setText(GlobalLeaderboard.players.get(position).getEmail());
        holder.score.setText(GlobalLeaderboard.players.get(position).getXP().toString());

    }

    @Override
    public int getItemCount() {
        return players.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Cursor cursor2;
        public TextView serial;
        public TextView player;
        public TextView score;
        Context context;
        public viewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            serial =  itemView.findViewById(R.id.textView26copy);
            player =  itemView.findViewById((R.id.textView28copy));
            score = itemView.findViewById(R.id.textView29copy);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

        }
    }
}
