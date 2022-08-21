package com.example.sampleapp53.adapterForCardView;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.squadMode.MainActivityForSqadPlay;
import com.example.sampleapp53.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class squadLeaderBoardAdapter extends RecyclerView.Adapter<squadLeaderBoardAdapter.viewHolder>{
    ArrayList<String> playerNames;
    public squadLeaderBoardAdapter(ArrayList<String> playerNames) {
        this.playerNames = playerNames;
    };
    @NonNull
    @NotNull
    @Override
    public squadLeaderBoardAdapter.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.squadleaderboardrow, parent,false);
        return new squadLeaderBoardAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull squadLeaderBoardAdapter.viewHolder holder, int position) {
        holder.player.setText(playerNames.get(position));
        holder.serialNumber.setText(position+1+".");
        new CountDownTimer(60000000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(MainActivityForSqadPlay.firestoreScoreList.size() != 0){
                    holder.score.setText(MainActivityForSqadPlay.helper2.get(position) + "");
                }
            }
            @Override
            public void onFinish() {
            }
        }.start();

    }

    @Override
    public int getItemCount() {
        return playerNames.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView player;
        public TextView serialNumber;
        public TextView score;


        Context context;
        public viewHolder( View itemView) {
            super(itemView);
            context = itemView.getContext();
            player =  itemView.findViewById(R.id.textView28);
            serialNumber=  itemView.findViewById(R.id.textView26);
            score = itemView.findViewById(R.id.textView29);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
