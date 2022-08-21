package com.example.sampleapp53.adapterForCardView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class adapterForSquadRoom extends RecyclerView.Adapter<adapterForSquadRoom.viewHolder>{

    ArrayList<String> playerNames;
    public adapterForSquadRoom(ArrayList<String> playerNames) {
        this.playerNames = playerNames;
    };
    @NonNull
    @NotNull
    @Override
    public adapterForSquadRoom.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.room_row, parent,false);
        return new adapterForSquadRoom.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull adapterForSquadRoom.viewHolder holder, int position) {
        holder.squadPlayer.setText(playerNames.get(position));
        holder.serialNumber.setText(position+1+"");
    }

    @Override
    public int getItemCount() {
        return playerNames.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView squadPlayer;
        public TextView serialNumber;


        Context context;
        public viewHolder( View itemView) {
            super(itemView);
            context = itemView.getContext();
            squadPlayer =  itemView.findViewById(R.id.textView16);
            serialNumber=  itemView.findViewById(R.id.textView15);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
