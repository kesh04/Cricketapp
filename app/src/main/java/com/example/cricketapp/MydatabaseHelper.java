package com.example.cricketapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MydatabaseHelper  extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME ="cricket.db";
    private  static  final  int DATABASE_VERSION =1;

    public   static  final  String COLUMN_ID = "_id";
    public static final String TABLE_NAME = "cricket_ply";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MATCH = "macth";
    public static final String  COLUMN_RUNS ="ply_runs";
    public static final String  COLUMN_50 ="ply_50";
    public static final String  COLUMN_100 ="ply_100";
    public static final String  COLUMN_RATE ="ply_RATE";




     MydatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_MATCH + " INTEGER, "
                + COLUMN_RUNS + " INTEGER, "
                + COLUMN_50 + " INTEGER, "
                + COLUMN_100 + " INTEGER, "
                + COLUMN_RATE + " TEXT) ";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void addplayer(String name, int match, int runs, int fifty, int hundred, String rate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_MATCH,match);
        cv.put(COLUMN_RUNS, runs);
        cv.put(COLUMN_50, fifty);
        cv.put(COLUMN_100, hundred);
        double strikeRate = calculateStrikeRate(runs, match);
        cv.put(COLUMN_RATE, String.valueOf(strikeRate));


        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Add okay", Toast.LENGTH_SHORT).show();
        }
    }


    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = null;
        if (db!= null){
          cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

     void updateData( String row_id,String name, int match, int runs, int fifty, int hundred, String rate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_MATCH, match);
        cv.put(COLUMN_RUNS, runs);
        cv.put(COLUMN_50, fifty);
        cv.put(COLUMN_100, hundred);
         String formattedStrikeRate = calculateFormattedStrikeRate(runs, match);
         cv.put(COLUMN_RATE, formattedStrikeRate);

        long result = db.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Update failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteoneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Delete failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Delete successful", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
    private double calculateStrikeRate(int runs, int match) {
        // Avoid division by zero
        if (match == 0) {
            return 0;
        }
        // Calculate and return the strike rate
        return ((double) runs / match) ;
    }
    public static String calculateFormattedStrikeRate(int runs, int match) {
        // Avoid division by zero
        if (match == 0) {
            return "0.00";
        }
        // Calculate the strike rate
        double strikeRate = ((double) runs / match);
        // Format the strike rate to two decimal places
        return String.format("%.2f", strikeRate);
    }
}
