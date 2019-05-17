package com.example.antonlyngfelt.trackyourseries;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DataBaseHelper extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "serieDB.db";
    public static final String TABLE_NAME = "Serie";
    public static final String COLUMN_NAME = "SerieName";
    public static final String COLUMN_SEASON = "SerieSeason";
    public static final String COLUMN_EPISODE = "SerieEpisode";
    //initialize the database
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_NAME +
                "INTEGER PRIMARYKEY," + COLUMN_SEASON + "TEXT "+ COLUMN_EPISODE + "TEXT2)";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public String loadHandler() {
        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addHandler(Serie serie) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, serie.getName());
        values.put(COLUMN_SEASON, serie.getSeason());
        values.put(COLUMN_EPISODE,serie.getEpisode());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public Serie findHandler(String seriename) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "=" + "'" + seriename + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Serie serie = new Serie();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            serie.setName(cursor.getString(0));
            serie.setSeason(Integer.parseInt(cursor.getString(1)));
            serie.setEpisode(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            serie = null;
        }
        db.close();
        return serie;
    }
    public boolean deleteHandler(String name) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "= '" + String.valueOf(name) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Serie serie = new Serie();
        if (cursor.moveToFirst()) {
            serie.setName(cursor.getString(0));
            db.delete(TABLE_NAME, COLUMN_NAME + "=?",
                    new String[] {
                String.valueOf(serie.getName())
            });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public boolean updateHandler(String name, int season, int episode) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_SEASON, season);
        args.put(COLUMN_EPISODE, episode);
        return db.update(TABLE_NAME, args, COLUMN_NAME + "=" + name, null) > 0;
    }
}
