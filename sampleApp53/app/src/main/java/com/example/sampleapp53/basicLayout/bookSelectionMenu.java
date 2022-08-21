package com.example.sampleapp53.basicLayout;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp53.R;
import com.example.sampleapp53.adapterForCardView.adapterForBookSelection;
import com.example.sampleapp53.modelForBookSelection;
import com.example.sampleapp53.myDBHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class bookSelectionMenu extends AppCompatActivity {
    FloatingActionButton fab;
    myDBHelper helper;
    SQLiteDatabase db;
    public static RecyclerView recyclerView;
    FirebaseFirestore firedb;
    ArrayList <modelForBookSelection> dataList;
    adapterForBookSelection adapter;
    ArrayList <String>questionsStorer;
    ArrayList <String>answerStorer;
    ArrayList <String>wrongAnsStorer;
    Map<String, Object> docData;
    FirebaseFirestore firedata;
    public static ArrayList <String> answers ;
    int numberOfQuestions = 0;
    String chapterName;
    InputStream mObj;
    String bookName;
    InputStream myObj;
    ConstraintLayout layout;
    TextView heading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_selection_menu);

        helper = new myDBHelper(this);
        firedb  = FirebaseFirestore.getInstance();
        dataList = new ArrayList<>();
        layout = findViewById(R.id.addMoreBooks);
        heading = findViewById(R.id.textView20);
        adapter = new adapterForBookSelection(dataList);


        firedb.collection("Books").get()
                 .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                     @Override
                     public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                         List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                         for (DocumentSnapshot d : list){
                             modelForBookSelection obj = d.toObject(modelForBookSelection.class);
                             dataList.add(obj);
                         }

                         adapter.notifyDataSetChanged();
                         recyclerView = findViewById(R.id.recyclerViewBookSelectionId);
                         recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                         recyclerView.setAdapter(adapter);
                     }
                 })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(bookSelectionMenu.this, "Error downloading books! " +e, Toast.LENGTH_SHORT).show();
                    }
                });


        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){};
        questionsStorer = new ArrayList<>();
        answerStorer= new ArrayList<>();
        wrongAnsStorer = new ArrayList<>();
        answers = new ArrayList<>();
        firedata  = FirebaseFirestore.getInstance();
        //set metadata of chapeter
//         numberOfQuestions = numberOfQuestions + 67;
//         chapterName = "Biodiversity";
//         bookName = "Biology NEET PYQ";
//         myObj = this.getResources().openRawResource(R.raw.biodiversity);
//         mObj = this.getResources().openRawResource(R.raw.biodiversity);
         // if questions more than 100 then adjust the setting
        //new logic starts here

//            Log.d("Test1","Start Reading");
//
//            Log.d("test1","here1");
//            Scanner myReader = new Scanner(myObj);
//            docData= new HashMap<>();
//            int s = 1;
//
//
//            while(myReader.hasNextLine()){
//                String data = myReader.nextLine();
//                if (data.contains("1. (" ) ){
//                    for(int p =0; p < numberOfQuestions; p++){
//                        if(s<numberOfQuestions ){
//                            answers.add(data.substring(data.indexOf(s + ".")+3, data.indexOf(s+1 +".")).trim());
//                            s++;
//                        }
//                        else if (s==numberOfQuestions){
//                            answers.add(data.substring(data.indexOf(s + ".")+3 ).trim());
//                        }
//                        // for questions more than 100
////                        if(s<100){
////                            answers.add(data.substring(data.indexOf(s + ".")+3, data.indexOf(s+1 +".")).trim());
////                            s++;
////                        }
////                        if(s<numberOfQuestions && s >=100){
////                            answers.add(data.substring(data.indexOf(s + ".")+4, data.indexOf(s+1 +".")).trim());
////                            s++;
////                        }
////                        else if (s==numberOfQuestions){
////                            answers.add(data.substring(data.indexOf(s + ".")+4 ).trim());
////                        }
//                    }
//                }
//            }
//
//
//            Log.d("test1","here1");
//            Scanner mReader = new Scanner(mObj);
//            int i = 0;
//
//            while (mReader.hasNextLine()) {
//                String data = mReader.nextLine();
//                if(data.contains("1. (")){
//                    break;
//                }
//                    String que =  data.substring(3, data.indexOf("(a)")-3);
//                    questionsStorer.add(que);
//
//                if(answers.get(i).equals("(a)")){
//                        String answer = data.substring(data.indexOf("(a)")+3, data.indexOf("(b)"));
//                        answerStorer.add(answer);
//                        String wrongAnswer = data.substring(data.indexOf("(b)")+3, data.indexOf("(c)"));
//                        wrongAnsStorer.add(wrongAnswer);
//                        if(i<answers.size()-1){
//                            i++;
//                        }
//                        else{
//
//                        }
//                }
//                else if (answers.get(i).equals("(b)")){
//                        String answer = data.substring(data.indexOf("(b)")+3, data.indexOf("(c)"));
//                        answerStorer.add(answer);
//                        String wrongAnswer = data.substring(data.indexOf("(c)")+3, data.indexOf("(d)"));
//                        wrongAnsStorer.add(wrongAnswer);
//                        if(i<answers.size()-1){
//                            i++;
//                        }
//                        else{
//
//                        }
//
//
//                }
//                else if(answers.get(i).equals("(c)")){
//                        String answer = data.substring(data.indexOf("(c)")+3, data.indexOf("(d)"));
//                        answerStorer.add(answer);
//                        String wrongAnswer = data.substring(data.indexOf("(d)")+3);
//                        wrongAnsStorer.add(wrongAnswer);
//                        if(i<answers.size()-1){
//                            i++;
//                        }
//                        else{
//
//                        }
//                }
//                else if(answers.get(i).equals("(d)")){
//
//
//                        String wrongAnswer = data.substring(data.indexOf("(a)")+3, data.indexOf("(b)"));
//                        wrongAnsStorer.add(wrongAnswer);
//                        String answer = data.substring(data.indexOf("(d)")+3);
//                        answerStorer.add(answer);
//                        if(i<answers.size()-1){
//                            i++;
//                        }
//                        else{
//
//                        }
//
//                }
//                else{
//
//                }
//            }
//            i=0;
//            mReader.close();
//            myReader.close();
//            for(int g = 0; g <=questionsStorer.size(); g++){
//                Log.d("avi", "question number = " + i + questionsStorer.get(i));
//                Log.d("avi", "answer number = " + i + answerStorer.get(i));
//                Log.d("avi", "wrong ans number = " + i + wrongAnsStorer.get(i));
//            }
//
//            Collections.shuffle(questionsStorer, new Random(100));
//            Collections.shuffle(answerStorer, new Random(100));
//            Collections.shuffle(wrongAnsStorer, new Random(100));
//            ArrayList<String> questorerfinal = new ArrayList<>();
//            for (int c = 0; c<questionsStorer.size(); c++ ){
//                questorerfinal.add(c+1 + "." + questionsStorer.get(c));
//                Log.d("Test1" , questorerfinal.get(c));
//            }
//            docData.put("questions", questorerfinal);
//            docData.put("answers", answerStorer);
//            docData.put("wrong_answers", wrongAnsStorer);
//
//            firedata.collection("Books").document(bookName)
//                    .collection("Chapters").document(chapterName)
//                    .set(docData)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(bookSelectionMenu.this, "uploaded", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(bookSelectionMenu.this, "failed", Toast.LENGTH_SHORT).show();
//                            Log.d("check","failed" +  e );
//                        }
//                    });



//            Log.d("Test1","" + e);
//            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
//            e.printStackTrace();

//        //old logic starts here
//        try {
//            Log.d("Test1","Start Reading");
//            InputStream myObj = this.getResources().openRawResource(R.raw.anatomyoffloweringplants);
//            Log.d("test1","here1");
//            Scanner myReader = new Scanner(myObj);
//            docData= new HashMap<>();
//            int s = 1;
//
// //for individual lines  type
//            while(myReader.hasNextLine()){
//                String data = myReader.nextLine();
//                if (data.contains("1. (" ) ){
//                    for(int p =0; p < 95; p++){
//                        if(s<95 ){
//                            answers.add(data.substring(data.indexOf(s + ".")+3, data.indexOf(s+1 +".")).trim());
//                            s++;
//                        }
//                        else if (s==95){
//                            answers.add(data.substring(data.indexOf(s + ".")+3 ).trim());
//                        }
//                        // for questions more than 100
//                        if(s<100){
//                            answers.add(data.substring(data.indexOf(s + ".")+3, data.indexOf(s+1 +".")).trim());
//                            s++;
//                        }
//                        if(s<164 && s >=100){
//                            answers.add(data.substring(data.indexOf(s + ".")+4, data.indexOf(s+1 +".")).trim());
//                            s++;
//                        }
//                        else if (s==164){
//                            answers.add(data.substring(data.indexOf(s + ".")+4 ).trim());
//                        }
//                    }
//                }
//            }
//
//            InputStream mObj = this.getResources().openRawResource(R.raw.anatomyoffloweringplants);
//            Log.d("test1","here1");
//            Scanner mReader = new Scanner(mObj);
//            int i = 0;
//
//            while (mReader.hasNextLine()) {
//                String data = mReader.nextLine();
//                if(!data.contains("(a)") && !data.contains("(b)") && !data.contains("(c)") && !data.contains("(d)") ){
//                     String que =  data.substring(3);
//                    questionsStorer.add(que);
//                }
//
//                if(answers.get(i).equals("(a)")){
//                    if(mReader.hasNextLine()){
//                        data = mReader.nextLine();
//                        String answer = data.substring(4);
//                        answerStorer.add(answer);
//                        data = mReader.nextLine();
//                        String wrongAnswer = data.substring(4);
//                        wrongAnsStorer.add(wrongAnswer);
//                        data = mReader.nextLine();
//                        data = mReader.nextLine();
//                        if(i<answers.size()-1){
//                            i++;
//                        }
//                        else{
//
//                        }
//                    }
//
//                }
//                else if (answers.get(i).equals("(b)")){
//                    if(mReader.hasNextLine()){
//                        data = mReader.nextLine();
//                        data = mReader.nextLine();
//                        String answer = data.substring(4);
//                        answerStorer.add(answer);
//                        data = mReader.nextLine();
//                        String wrongAnswer = data.substring(4);
//                        wrongAnsStorer.add(wrongAnswer);
//                        data = mReader.nextLine();
//                        if(i<answers.size()-1){
//                            i++;
//                        }
//                        else{
//
//                        }
//                    }
//
//                }
//                else if(answers.get(i).equals("(c)")){
//                    if(mReader.hasNextLine()){
//                        data = mReader.nextLine();
//                        data = mReader.nextLine();
//                        data = mReader.nextLine();
//                        String answer = data.substring(4);
//                        answerStorer.add(answer);
//                        data = mReader.nextLine();
//                        String wrongAnswer = data.substring(4,data.indexOf(". (")+1);
//                        wrongAnsStorer.add(wrongAnswer);
//                        if(i<answers.size()-1){
//                            i++;
//                        }
//                        else{
//
//                        }
//                    }
//
//                }
//                else if(answers.get(i).equals("(d)")){
//                    if(mReader.hasNextLine()){
//                        data = mReader.nextLine();
//                        String wrongAnswer = data.substring(4);
//                        wrongAnsStorer.add(wrongAnswer);
//                        data = mReader.nextLine();
//                        data = mReader.nextLine();
//                        data = mReader.nextLine();
//                        String answer = data.substring(4,data.indexOf(". (")+1);
//                        answerStorer.add(answer);
//                        if(i<answers.size()-1){
//                            i++;
//                        }
//                        else{
//
//                        }
//                    }
//
//                }
//                else{
//
//                }
//            }
//            i=0;
//            mReader.close();
//            myReader.close();
//            for(int g = 0; g <=questionsStorer.size(); g++){
//                Log.d("avi", "question number = " + i + questionsStorer.get(i));
//                Log.d("avi", "answer number = " + i + answerStorer.get(i));
//                Log.d("avi", "wrong ans number = " + i + wrongAnsStorer.get(i));
//            }
//
//            Collections.shuffle(questionsStorer, new Random(100));
//            Collections.shuffle(answerStorer, new Random(100));
//            Collections.shuffle(wrongAnsStorer, new Random(100));
//            ArrayList<String> questorerfinal = new ArrayList<>();
//            for (int c = 0; c<questionsStorer.size(); c++ ){
//                questorerfinal.add(c+1 + "." + questionsStorer.get(c));
//                Log.d("Test1" , questorerfinal.get(c));
//            }
//            docData.put("questions", questorerfinal);
//            docData.put("answers", answerStorer);
//            docData.put("wrong_answers", wrongAnsStorer);
//
//           firedata.collection("Books").document("Biology NEET PYQ")
//                   .collection("Chapters").document("Anatomy of Flowering Plants")
//                   .set(docData)
//                   .addOnSuccessListener(new OnSuccessListener<Void>() {
//                       @Override
//                       public void onSuccess(Void aVoid) {
//                           Toast.makeText(bookSelectionMenu.this, "uploaded", Toast.LENGTH_SHORT).show();
//                       }
//                   })
//                   .addOnFailureListener(new OnFailureListener() {
//                       @Override
//                       public void onFailure(@NonNull Exception e) {
//                           Toast.makeText(bookSelectionMenu.this, "failed", Toast.LENGTH_SHORT).show();
//                           Log.d("check","failed" +  e );
//                       }
//                   });
//
//        }
//        catch (Exception e) {
//            Log.d("Test1","" + e);
//            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
    }


}