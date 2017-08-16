package com.example.amruth.notekeeper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by amruth on 27/02/17.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    static final String DB_NAME="mynotes_db";
    static final int DB_VERSION=6;

    public DatabaseOpenHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        NoteTable.onCreate(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        NoteTable.onUpgrade(sqLiteDatabase,i,i1);

    }
}
