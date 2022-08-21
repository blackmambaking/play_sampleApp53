package com.example.sampleapp53.adapterForCardView;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.sampleapp53.dashboardFragments.dashboardFragment;
import com.example.sampleapp53.modelForBookSelection;
import com.example.sampleapp53.myDBHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class adapterForBookSelection extends RecyclerView.Adapter < adapterForBookSelection.viewHolder3>{

    static myDBHelper helper;
    static SQLiteDatabase db;
    ArrayList<modelForBookSelection> dataList;
    public static Cursor cursor;
    public static ArrayList<String> selectedBooks;


    public adapterForBookSelection(ArrayList<modelForBookSelection> dataList) {
        this.dataList = dataList;
    };
    @NonNull
    @NotNull
    @Override
    public adapterForBookSelection.viewHolder3 onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_book_selection, parent,false);
        selectedBooks = new ArrayList<>();
        return new viewHolder3(view);
    }

    @Override
    public void onBindViewHolder(adapterForBookSelection.viewHolder3 holder, int position) {

        holder.selectBookNameId.setText(dataList.get(position).getName().trim());
        holder.selectBookImageId.setImageResource(R.drawable.neetpic);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public static class viewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView selectBookNameId;
        public ImageView selectBookImageId;
        public CardView cardView;

        Context context;
        public viewHolder3( View itemView) {
            super(itemView);
            context = itemView.getContext();
            selectBookNameId =  itemView.findViewById(R.id.selectBookNameId);
            selectBookImageId = itemView.findViewById(R.id.imageView2);
            cardView = itemView.findViewById(R.id.addbookscard);
            itemView.setOnClickListener(this);

//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    helper = new myDBHelper(context.getApplicationContext());
//                    db = helper.getReadableDatabase();
//
//                    if(dashboardFragment.bookNames.contains(selectBookNameId.getText().toString()) || selectedBooks.contains(selectBookNameId.getText().toString())){
//                        Toast.makeText(context, "Book already selected", Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        cardView.setCardBackgroundColor(Color.rgb(72,202,228));
//                        selectedBooks.add(selectBookNameId.getText().toString());
//                        helper.insertBooksToDashboard(selectBookNameId.getText().toString().trim());
//                        Toast.makeText(context, "Book added to dashboard", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });

        }
        @Override
        public void onClick(View v) {
            helper = new myDBHelper(context.getApplicationContext());
            db = helper.getReadableDatabase();

            if(dashboardFragment.bookNames.contains(selectBookNameId.getText().toString()) || selectedBooks.contains(selectBookNameId.getText().toString())){
                Toast.makeText(context, "Book already selected", Toast.LENGTH_SHORT).show();
            }
            else{
                selectedBooks.add(selectBookNameId.getText().toString());
                helper.insertBooksToDashboard(selectBookNameId.getText().toString().trim());
                Toast.makeText(context, "Book added to dashboard", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
