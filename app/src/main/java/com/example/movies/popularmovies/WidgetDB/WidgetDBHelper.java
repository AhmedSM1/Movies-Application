package com.example.movies.popularmovies.WidgetDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.movies.popularmovies.WidgetDB.WidgetDBContract.*;
public class WidgetDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myDatabase.db";
    private static final int DATABASE_VERSION = 1;


    public WidgetDBHelper(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_QUERY = "CREATE TABLE " +
                MovieEntry.TABLE_NAME + " (" +
                MovieEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                MovieEntry.MOVIE_TITLE_COLUMN + " TEXT NOT NULL," +
                MovieEntry.TIME_STAMP_COLUMN + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+
                ");";
        sqLiteDatabase.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
    }

}
