package com.example.movieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "MovieList.db";
    private static final int DATABASE_VERSION = 1;
    static boolean deleted = false;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createToWatchMovieListTable(db);
        createWatchedMovieListTable(db);
        createEventTable(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + MainActivity.TO_WATCH);
        db.execSQL("DROP TABLE IF EXISTS " + MainActivity.WATCHED);
        db.execSQL("DROP TABLE IF EXISTS " + MainActivity.EVENT);
        onCreate(db);
    }

    Cursor readAllData(String table_name){
        String query = "SELECT * FROM " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    void deleteAllData(String table_name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table_name);
    }

    void createToWatchMovieListTable(SQLiteDatabase db) {
        String query2 = "Create table " + MainActivity.TO_WATCH +"("+
                "id INTEGER default 0 not null,"+
                "title TEXT default '' not null,"+
                "_year INTEGER default 0 not null,"+
                "minutes INTEGER default 0 not null,"+
                "genres TEXT default '' not null, "+
                "info TEXT default '' not null,"+
                "rating REAL default 0 not null,"+
                "date_added TEXT default '' not null);";
        db.execSQL(query2);
    }

    void createWatchedMovieListTable(SQLiteDatabase db) {
        String query = "Create table "+ MainActivity.WATCHED +"("+
                "id INTEGER default 0 not null,"+
                "title TEXT default '' not null,"+
                "_year INTEGER default 0 not null,"+
                "minutes INTEGER default 0 not null,"+
                "genres TEXT default '' not null, "+
                "info TEXT default '' not null,"+
                "rating REAL default 0 not null," +
                "date_added TEXT default '' not null);";
        db.execSQL(query);
    }

    void updateDataWatchList(String table_name, int id, String title, int year, int min, String genres, String info, double rating,String dateAdded){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("title", title);
        cv.put("_year", year);
        cv.put("minutes", min);
        cv.put("genres", genres);
        cv.put("info", info);
        cv.put("rating", rating);
        cv.put("date_added", dateAdded);

        db.update(table_name, cv, "id=?", new String[]{id+""});

    }

    void addToWatchList(String table_name, int id, String title, int year, int min, String genres, String info, double rating,String dateAdded){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("id", id);
        cv.put("title", title);
        cv.put("_year", year);
        cv.put("minutes", min);
        cv.put("genres", genres);
        cv.put("info", info);
        cv.put("rating", rating);
        cv.put("date_added", dateAdded);

        db.insert(table_name,null, cv);
    }

    void deleteItem(String table_name,String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table_name, "id=?", new String[]{id});
        if(MovieInfo.delete){
            if(result == -1) {
                deleted=false;
                Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
            }else{
                deleted=true;
                Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void createEventTable(SQLiteDatabase db) {
        String query = "Create table "+ MainActivity.EVENT +"("+
                "id INTEGER default 0 not null,"+
                "name TEXT default '' not null,"+
                "time TEXT default '' not null,"+
                "date_ TEXT default '' not null);";
        db.execSQL(query);
    }

    void addEvent(String table_name, int id, String name,String date_,String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("id", id);
        cv.put("name", name);
        cv.put("date_", date_);
        cv.put("time", time);

        db.insert(table_name,null, cv);
    }

}