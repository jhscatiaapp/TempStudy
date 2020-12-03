package com.example.test03;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class myDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "score_test01.db";
    public static final String TABLE_NAME = "Score_Table";
    public static final String COL_DATE = "DATE";
    public static final String COL_CC = "CC";
    public static final String COL_PLAYER = "PLAYER_NAME";
    public static final String COL_HOLE = "HOLE";
    public static final String COL_PAR = "PAR";
    public static final String COL_SCORE = "SCORE";

    public static final int DB_VERSION = 1;

    public myDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (HOLE String, PAR Integer, SCORE Integer" +
                ")");
        //DATE String, CC String, PLAYER_NAME String,
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }

    public boolean saveToDB(String HOLE, int PAR, int SCORE) {
//String DATE, String CC, String PLAYER_NAME,
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        /*contentValues.put(COL_DATE, DATE);
        contentValues.put(COL_CC, CC);
        contentValues.put(COL_PLAYER, PLAYER_NAME);*/
        contentValues.put(COL_HOLE, HOLE);
        contentValues.put(COL_PAR, PAR);
        contentValues.put(COL_SCORE, SCORE);

        long error = db.insert(TABLE_NAME, null, contentValues);
        if (error == -1)
            return false;
        else
            return true;
    }

/*    public Cursor readAllDataAsc() {
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_TIMESTAMP + " ASC"; // ASC or DESC
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readAllDataDesc() {
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_TIMESTAMP + " DESC"; // ASC or DESC
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }*/

    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Integer deleteData(String HOLE) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "HOLE = ?", new String[] {HOLE});
    }

    public void deleteAllData() {
        String query = "DELETE FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
}
