package com.example.sampleapp53.basicLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapterForChapterSelection;
import com.example.sampleapp53.myDBHelper;
import com.example.sampleapp53.oneVone.oneVoneClass;
import com.example.sampleapp53.soloMode.MainActivity;
import com.example.sampleapp53.squadMode.CreateRoom;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class ChapterSelection extends AppCompatActivity {
    public static ArrayList <String>chapterNames;
    myDBHelper helper;
    SQLiteDatabase db;
    public static Cursor cursor3;
    FirebaseFirestore firedb;
    public static String bookSelected;
    ArrayList<String> dataList;
    adapterForChapterSelection mAdapter;
    TextView createRoom;
    TextView oneVone;
    TextView solo;
    String roomKey;
    public static String mode;
    public static RecyclerView recyclerView;
    FirebaseFirestore firedata;
    public  EditText playerName;
    public  TextView room_key;
    public static String joinRoomKeyTransport;

    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_selection);
        helper = new myDBHelper(this);
        createRoom = findViewById(R.id.textView18);
        firedata  = FirebaseFirestore.getInstance();
        mode = "";

        solo = findViewById(R.id.textView17);
        oneVone = findViewById(R.id.textView18copy);

        bookSelected = getIntent().getStringExtra("bookSelected");
        oneVone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneVone.setBackgroundColor(getResources().getColor(R.color.lightBlue));
                new CountDownTimer(500, 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                        oneVone.setBackgroundColor(Color.WHITE);
                        Intent i = new Intent(getApplicationContext(), oneVoneClass.class);
                        startActivity(i);
                    }
                }.start();
            }
        });
        solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solo.setBackgroundColor(getResources().getColor(R.color.lightBlue));
                new CountDownTimer(500, 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                        solo.setBackgroundColor(Color.WHITE);
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                }.start();
            }
        });

        createRoom.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                createRoom.setBackgroundColor(getResources().getColor(R.color.lightBlue));
                new CountDownTimer(500, 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                        createRoom.setBackgroundColor(Color.WHITE);
                    }
                }.start();

//                Intent i = new Intent(getApplicationContext(), CreateRoom.class);
//                startActivity(i);
                dialogueBox();
            }
        });
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        db = helper.getReadableDatabase();
        cursor3 = db.rawQuery("SELECT BOOK, CHAPTER_NAME, SCORE FROM CHAPTER_SCORES", new String[]{});
        cursor3.moveToFirst();

        firedb  = FirebaseFirestore.getInstance();
        chapterNames = new ArrayList<>();
        dataList = new ArrayList<>();

        Task<DocumentSnapshot> task = firedb.collection("Books")
               .document(bookSelected)
               .get();

               task.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                   @Override
                   public void onSuccess(DocumentSnapshot documentSnapshot) {
                       DocumentSnapshot d = documentSnapshot;
                       Map<String, Object> m = d.getData();
                       dataList = (ArrayList<String>) m.get("Index");
                       chapterNames.addAll(dataList);
                       mAdapter = new adapterForChapterSelection(chapterNames);
                       recyclerView = findViewById(R.id.recyclerViewChapterId);
                       recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                       recyclerView.setAdapter(mAdapter);
                   }
               });
    }
  @RequiresApi(api = Build.VERSION_CODES.O)
  public  void dialogueBox(){
      final AlertDialog.Builder alert = new AlertDialog.Builder(this);
      View mView = getLayoutInflater().inflate(R.layout.squad_mode_dialogue_box,null);
      EditText joinRoomKey = (EditText) mView.findViewById(R.id.editTextNumber2copy);

      CardView createRoom = (CardView)mView.findViewById(R.id.createroomcardview);
      SeekBar percentageOfQuestions = (SeekBar)mView.findViewById(R.id.seekBar2);
      CardView joinRoom = (CardView)mView.findViewById(R.id.cardviewjoinroom);
      TextView questions = mView.findViewById(R.id.percentageOfQuestionsDialogue);
      percentageOfQuestions.setMin(1);
      alert.setView(mView);
      final AlertDialog alertDialog = alert.create();
      alertDialog.setCanceledOnTouchOutside(true);
      percentageOfQuestions.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
          @Override
          public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              questions.setText(progress *10 + "%");
          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {

          }

          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {

          }
      });
      createRoom.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              createRoom.setCardBackgroundColor(Color.rgb(79,195,247));
              new CountDownTimer(1000, 500) {
                  @Override
                  public void onTick(long millisUntilFinished) {

                  }
                  @Override
                  public void onFinish() {
                      createRoom.setCardBackgroundColor(0);
                      alertDialog.dismiss();
                      mode = "createRoom";
                      Intent i = new Intent(getApplicationContext(), CreateRoom.class);
                      i.putExtra("mode", mode);
                      startActivity(i);
                  }
              }.start();
          }
      });
      joinRoom.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              joinRoom.setCardBackgroundColor(Color.rgb(79,195,247));
              new CountDownTimer(1000, 500) {
                  @Override
                  public void onTick(long millisUntilFinished) {

                  }
                  @Override
                  public void onFinish() {
                      joinRoom.setCardBackgroundColor(0);
                      i++;
                      roomKey = joinRoomKey.getText().toString();
                      Task<DocumentSnapshot> task = firedata.collection("SquadRooms")
                              .document(roomKey + "")
                              .get();
                      task.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                          @Override
                          public void onSuccess(DocumentSnapshot documentSnapshot) {
                            alertDialog.dismiss();
                            joinRoomKeyTransport = joinRoomKey.getText().toString();
                            mode = "joinRoom";
                            Intent i = new Intent(getApplicationContext(), CreateRoom.class);
                            i.putExtra("mode", mode);
                            i.putExtra("joinRoomKey", joinRoomKey.getText().toString());
                            startActivity(i);
                          }
                      });
                      task.addOnFailureListener(new OnFailureListener() {
                          @Override
                          public void onFailure(@NonNull @NotNull Exception e) {
                              Log.d("avi", e +"");
                              Toast.makeText(ChapterSelection.this, "Not a valid room", Toast.LENGTH_SHORT).show();
                          }
                      });
                  }
              }.start();

          }
      });
      alertDialog.show();
  }
}