package com.example.sampleapp53;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDBHelper extends SQLiteOpenHelper {
    static SQLiteDatabase db;
    static SQLiteDatabase db2;
    Cursor cursor;

    public static final String TABLE_NAME_MARKEDQUESTIONS = "MARKED_QUESTIONS";
    public static final String TABLE_NAME_BOOKSCORE = "BOOK_SCORES";
    public static final String TABLE_NAME_SELECTED_BOOKS = "DASHBOARD_BOOKS";
    public static final String TABLE_NAME_CHAPTERSCORE = "CHAPTER_SCORES";
    public static final String TABLE_NAME_MARKEDQUESTIONSABSOLUTE = "MARKED_QUESTIONS_ABSOLUTE";
    public static final String TABLE_NAME_CHECKPOINTS = "CHECKPOINTS";
    public static final String TABLE_NAME_DETAILEDANALYSIS = "DETAILED_ANALYSIS_OF_CHAPTER";
    public static final String TABLE_NAME_COINSANDXP = "COINS_AND_XP";
    public static final String TABLE_NAME_CAPTURE_INFO = "CAPTURE_INFO";


    public myDBHelper(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME_MARKEDQUESTIONS+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, CHAPTER TEXT, ARRAY_INDEX INTEGER)";
        String sql2 = "CREATE TABLE " + TABLE_NAME_BOOKSCORE+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT, BOOK TEXT, SCORE INTEGER)";
        String sql3 = "CREATE TABLE " + TABLE_NAME_SELECTED_BOOKS+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, BOOK_NAME TEXT)";
        String sql4 = "CREATE TABLE " + TABLE_NAME_CHAPTERSCORE+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, BOOK TEXT, CHAPTER_NAME TEXT, SCORE INTEGER)";
        String sql5 = "CREATE TABLE " + TABLE_NAME_MARKEDQUESTIONSABSOLUTE+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, BOOK_NAME TEXT, CHAPTER_NAME TEXT, QUESTION TEXT, ANSWER TEXT, WRONG_ANSWER TEXT)";
        String sql6 = "CREATE TABLE " + TABLE_NAME_CHECKPOINTS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, BOOK TEXT, CHAPTER_NAME TEXT, QUESTION_NUMBER INTEGER)";
        String sql7 = "CREATE TABLE " + TABLE_NAME_DETAILEDANALYSIS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, BOOK TEXT, CHAPTER TEXT, QUESTION INTEGER, ANSWER TEXT)";
        String sql8 = "CREATE TABLE " + TABLE_NAME_COINSANDXP + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, COINS INTEGER, XP INTEGER)";
        String sql9 = "CREATE TABLE " + TABLE_NAME_CAPTURE_INFO + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, BLOCK_NAME TEXT, SUB_BLOCK TEXT, KEYWORDS TEXT)";
        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
        db.execSQL(sql6);
        db.execSQL(sql9);
        db.execSQL(sql7);
        db.execSQL(sql8);


    }
    public  void inserCaptureInfo(String BlockName, String SubBlockText, String Keyword){
        db = this.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        values1.put("BLOCK_NAME", BlockName);

        values1.put("SUB_BLOCK", SubBlockText);
        values1.put("KEYWORDS", Keyword);
        db.insert(TABLE_NAME_CAPTURE_INFO  , null, values1);
        db.close();

    }
    public  void insertCoinsAndXP(int coins, int XP){
        db = this.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        values1.put("COINS", coins);
        values1.put("XP", XP);
        db.insert(TABLE_NAME_COINSANDXP  , null, values1);
        db.close();

    }
    public void updateCoinsAndXp (int coins, int XP){
        db = this.getWritableDatabase();
        db2 = this.getReadableDatabase();
        cursor = db2.rawQuery("SELECT COINS, XP FROM COINS_AND_XP", new String[]{});
        cursor.moveToFirst();
        int existingCoins = cursor.getInt(0);
        int existingXP = cursor.getInt(1);
        ContentValues values1 = new ContentValues();
        values1.put("COINS", coins+existingCoins);
        values1.put("XP", XP+existingXP);
        db.update(TABLE_NAME_COINSANDXP,values1,null,null);
        db.close();
    }
    public  void insertQuestionDetiled(String book, String chapter, int question_number, String answer){
        db = this.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        values1.put("BOOK",book);
        values1.put("CHAPTER", chapter);
        values1.put("QUESTION", question_number);
        values1.put("ANSWER", answer);
        db.insert(TABLE_NAME_DETAILEDANALYSIS  , null, values1);
        db.close();

    }
    public void updateDetailedQuestions (String bookName, String chapterName, int question, String answer){
        db = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();

        values1.put("ANSWER", answer);
        db.update(TABLE_NAME_DETAILEDANALYSIS,values1,"CHAPTER = " + "'"+ chapterName+"'" + " AND " +  "BOOK = " + "'"+ bookName+"'" + " AND " + "QUESTION = " + question,null);
        db.close();
    }
    public  void makeCheckpoint(String book, String chapter, int question_number){
        db = this.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        values1.put("BOOK",book);
        values1.put("CHAPTER_NAME", chapter);
        values1.put("QUESTION_NUMBER", question_number);
        db.insert(TABLE_NAME_CHECKPOINTS  , null, values1);
        db.close();

    }
    public  void insertData(String chapter, int array_index){
        db = this.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        values1.put("chapter",chapter);
        values1.put("array_index", array_index);
        db.insert(TABLE_NAME_MARKEDQUESTIONS  , null, values1);
        db.close();

    }
    public  void insertDataToAbsoluteMarkedQuestions(String book, String chapter, String question, String ans, String wrong_ans){
        db = this.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        values1.put("BOOK_NAME",book);
        values1.put("CHAPTER_NAME", chapter);
        values1.put("QUESTION",question);
        values1.put("ANSWER", ans);
        values1.put("WRONG_ANSWER", wrong_ans);
        db.insert(TABLE_NAME_MARKEDQUESTIONSABSOLUTE  , null, values1);
        db.close();

    }
    public void addBookScore(String bookName, int bookScore){
        db = this.getWritableDatabase();
        ContentValues values2 = new ContentValues();
        values2.put("Book", bookName);
        values2.put("Score", bookScore);
        db.insert(TABLE_NAME_BOOKSCORE , null, values2);
        db.close();
    }
    public void updateBookScore(String bookName, int bookScore){
        db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("Score", bookScore);
        db.update(TABLE_NAME_BOOKSCORE , newValues,"BOOK = " + "'"+ bookName+"'",null );
        db.close();
    }


    public void insertBooksToDashboard(String book){
        db = this.getWritableDatabase();

        ContentValues values3 = new ContentValues();
        values3.put("BOOK_NAME", book);

        db.insert(TABLE_NAME_SELECTED_BOOKS , null, values3);
        db.close();
    }

    public void addChapterScore(String bookName, String chapterName, int chapterScore){
        db = this.getWritableDatabase();
        ContentValues values4 = new ContentValues();
        values4.put("Book", bookName);
        values4.put("CHAPTER_NAME", chapterName);
        values4.put("SCORE", chapterScore);
        db.insert(TABLE_NAME_CHAPTERSCORE, null, values4);
        db.close();
    }
    public void updateChapterScore(String bookName, String chapterName, int chapterScore){
        db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("SCORE", chapterScore);
        db.update(TABLE_NAME_CHAPTERSCORE,newValues,"CHAPTER_NAME = " + "'"+ chapterName+"'",null);
        db.close();
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
