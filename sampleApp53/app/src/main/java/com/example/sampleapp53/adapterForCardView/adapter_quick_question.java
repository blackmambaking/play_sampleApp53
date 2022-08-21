package com.example.sampleapp53.adapterForCardView;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.soloMode.MainActivity;
import com.example.sampleapp53.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class adapter_quick_question extends RecyclerView.Adapter<adapter_quick_question.viewHolder> {
    ArrayList<String> quick_questions;
    ArrayList<String> answers;

    public adapter_quick_question(ArrayList<String> quick_questions) {
        this.quick_questions = quick_questions;
    };
    @NonNull
    @NotNull
    @Override

    public adapter_quick_question.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.question_navigator_row, parent,false);
        return new adapter_quick_question.viewHolder(view);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull @NotNull adapter_quick_question.viewHolder holder, int position) {

        holder.question.setText(quick_questions.get(position));
        holder.answer.setText(MainActivity.detailedAnswerState.get(position));


    }

    @Override
    public int getItemCount() {
         return quick_questions.size();
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

            new CountDownTimer(2000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    MainActivity.j = getAdapterPosition();
                    MainActivity.question.setText(MainActivity.questions.get(MainActivity.j));
                    MainActivity.answer1.setText("A. "+MainActivity.answers.get(MainActivity.j));
                    MainActivity.answer2.setText("B. "+MainActivity.wrongAnswers.get(MainActivity.j));
                    if (MainActivity.recyclerView.getTranslationX() == 0) {
                        MainActivity.recyclerView.setTranslationX(-2000);
                        MainActivity.question.setTranslationX(0);
                        MainActivity.answer1.setTranslationX(0);
                        MainActivity.answer2.setTranslationX(0);
                        itemView.setBackgroundColor(80000000);
                    }else{

                    }
                }
            }.start();


        }
    }
}
