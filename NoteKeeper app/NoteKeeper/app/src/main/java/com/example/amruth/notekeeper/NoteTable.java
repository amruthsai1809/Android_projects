package com.example.amruth.notekeeper;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by amruth on 27/02/17.
 */

public class NoteTable {
    static final String TABLENAME="notes";
    static final String COLUMN_ID="_id";
    static final String COLUMN_NOTE="note";
    static final String COLUMN_PRIORITY="priority";
    static final String COLUMN_STATUS="status";
    static final String COLUMN_UPDATETIME="updatetime";
    static final String COLUMN_PRIORITYCODE="prioritycode";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb= new StringBuilder();
        sb.append("CREATE TABLE "+ TABLENAME+" (");
        sb.append(COLUMN_ID+" integer primary key autoincrement, ");
        sb.append(COLUMN_NOTE+" text not null, ");
        sb.append(COLUMN_PRIORITY+" text not null, ");
        sb.append(COLUMN_STATUS+" integer not null, ");
        sb.append(COLUMN_UPDATETIME+" text not null, ");
        sb.append(COLUMN_PRIORITYCODE+" integer not null); ");
        try{
            db.execSQL(sb.toString());
        }catch (SQLException ex){
            ex.printStackTrace();

        }

    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLENAME+";");
        NoteTable.onCreate(db);

    }
}
