package com.example.antonlyngfelt.trackyourseries;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "serieDB.db";
    public static final String TABLE_NAME_SERIE = "Serie";
    public static final String COLUMN_ID_SERIE = "ID";
    public static final String COLUMN_NAME_SERIE = "SerieName";
    public static final String COLUMN_SEASON_SERIE = "SerieSeason";
    public static final String COLUMN_EPISODE_SERIE = "SerieEpisode";



    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_SERIE = "CREATE TABLE " + TABLE_NAME_SERIE + " ("+COLUMN_ID_SERIE+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUMN_NAME_SERIE +" TEXT, " +
                  COLUMN_SEASON_SERIE + " TEXT2, "+ COLUMN_EPISODE_SERIE + " TEXT3)";
        //String CREATE_TABLE_MOVIE = "CREATE TABLE " + TABLE_NAME_SERIE + " ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUMN_NAME +" TEXT, " +
            //    COLUMN_SEASON + " TEXT2, "+ COLUMN_EPISODE + " TEXT3)";
        db.execSQL(CREATE_TABLE_SERIE);
       // db.execSQL(CREATE_TABLE_MOVIE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_SERIE);
        onCreate(db);
    }
    public List<Serie> loadHandler(){
        List<Serie> wholeList = new LinkedList<>();
        String query = "SELECT * FROM "+ TABLE_NAME_SERIE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){
            int result_0 = cursor.getInt(0);
            String result_1 =cursor.getString(1);
            int result_2 = cursor.getInt(2);
            int result_3 = cursor.getInt(3);
            wholeList.add(new Serie(result_0, result_1,result_2,result_3 ));
        }
        cursor.close();
        db.close();
        return wholeList;
    }
    public void addHandler(Serie serie){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_SERIE, serie.getName());
        values.put(COLUMN_SEASON_SERIE, serie.getSeason());
        values.put(COLUMN_EPISODE_SERIE, serie.getEpisode());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME_SERIE, null, values);
        db.close();
    }
    public boolean deleteHandler(String serieName){
        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME_SERIE + " WHERE " + COLUMN_NAME_SERIE + "= '" + serieName + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Serie serie = new Serie();
        if (cursor.moveToFirst()) {
            serie.setName(cursor.getString(1));
            db.delete(TABLE_NAME_SERIE, COLUMN_NAME_SERIE + "=?",
                    new String[] {
                serie.getName()
            });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public boolean updateHandler(String serieName, int season, int episode){
        String query= "SELECT ID WHERE serieName=serieName";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        //args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME_SERIE, serieName);
        args.put(COLUMN_SEASON_SERIE, season);
        args.put(COLUMN_EPISODE_SERIE, episode);
        return db.update(TABLE_NAME_SERIE, args, COLUMN_NAME_SERIE + " = '"+serieName+"'",null)>0;
    }
}
