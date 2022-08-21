package com.example.sampleapp53.adapterForCardView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class adapterForContactsList extends RecyclerView.Adapter<adapterForContactsList.viewHolder>{
    ArrayList<String> contactNames;

    public adapterForContactsList(ArrayList<String> contactNames) {
        this.contactNames = contactNames;
    };
    @NonNull
    @NotNull
    @Override
    public adapterForContactsList.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dashboardfriendsrow, parent,false);
        return new adapterForContactsList.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull adapterForContactsList.viewHolder holder, int position) {
        holder.contactImage.setImageResource(R.drawable.profile);
        holder.contactName.setText(contactNames.get(position));
      

    }

    @Override
    public int getItemCount() {
            return contactNames.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView contactImage;
        public TextView contactName;
        Context context;
        public viewHolder( View itemView) {
            super(itemView);
            context = itemView.getContext();
            contactImage =  itemView.findViewById(R.id.contactImage);
            contactName=  itemView.findViewById(R.id.friendName);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

        }
    }
}
