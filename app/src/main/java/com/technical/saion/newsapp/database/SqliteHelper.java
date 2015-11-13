package com.technical.saion.newsapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.technical.saion.newsapp.database.table.NewsTable;

/**
 * Created by saion on 09.11.2015.
 */
public class SqliteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "news.db";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final int DATABASE_VERSION = 4;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NewsTable.Requests.CREATION_REQUEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(NewsTable.Requests.DROP_REQUEST);
        onCreate(db);
    }
}
