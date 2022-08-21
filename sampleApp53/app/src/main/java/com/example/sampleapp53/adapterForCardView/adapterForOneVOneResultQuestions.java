package com.example.sampleapp53.adapterForCardView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class adapterForOneVOneResultQuestions extends RecyclerView.Adapter<adapterForOneVOneResultQuestions.viewHolder>{
    ArrayList<String> questions;
    public adapterForOneVOneResultQuestions(ArrayList<String> questions) {
        this.questions = questions;
    };
    @NonNull
    @NotNull
    @Override
    public adapterForOneVOneResultQuestions.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.question_navigator_row, parent,false);
        return new adapterForOneVOneResultQuestions.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull adapterForOneVOneResultQuestions.viewHolder holder, int position) {
        holder.question.setText(questions.get(position));
        holder.answer.setText("");
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView question;
        public TextView answer;
        public ConstraintLayout layout;


        Context context;
        public viewHolder( View itemView) {
            super(itemView);
            context = itemView.getContext();
            question =  itemView.findViewById(R.id.quickQuestion);
            answer = itemView.findViewById(R.id.textView5);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

    }
}
}

