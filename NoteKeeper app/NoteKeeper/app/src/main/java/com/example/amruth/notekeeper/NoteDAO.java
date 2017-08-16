package com.example.amruth.notekeeper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amruth on 27/02/17.
 */

public class NoteDAO {
    private SQLiteDatabase db;
    public NoteDAO(SQLiteDatabase db) {
        this.db = db;
    }
    public long save(Note note){
        ContentValues values= new ContentValues();
        values.put(NoteTable.COLUMN_NOTE,note.getNote());
        values.put(NoteTable.COLUMN_PRIORITY,note.getPriority());
        values.put(NoteTable.COLUMN_STATUS,note.getStatus());
        values.put(NoteTable.COLUMN_UPDATETIME,note.getUpdatetime());
        values.put(NoteTable.COLUMN_PRIORITYCODE,note.getPrioritycode());
        return db.insert(NoteTable.TABLENAME,null,values);
    }
    public boolean update(Note note){
        ContentValues values= new ContentValues();
        values.put(NoteTable.COLUMN_NOTE,note.getNote());
        values.put(NoteTable.COLUMN_PRIORITY,note.getPriority());
        values.put(NoteTable.COLUMN_STATUS,note.getStatus());
        values.put(NoteTable.COLUMN_UPDATETIME,note.getUpdatetime());
        values.put(NoteTable.COLUMN_PRIORITYCODE,note.getPrioritycode());

        return db.update(NoteTable.TABLENAME,values,NoteTable.COLUMN_ID+ "=?",new String[]{note.get_id()+""})>0;
    }
    public boolean delete(Note note){
        return db.delete(NoteTable.TABLENAME,NoteTable.COLUMN_ID+ "=?",new String[]{note.get_id()+""})>0;
    }
    public Note get(long id){
        Note note=null;
        Cursor c=db.query(true,NoteTable.TABLENAME,new String[]{NoteTable.COLUMN_ID,NoteTable.COLUMN_NOTE,NoteTable.COLUMN_PRIORITY,NoteTable.COLUMN_STATUS,NoteTable.COLUMN_UPDATETIME,NoteTable.COLUMN_PRIORITYCODE},NoteTable.COLUMN_ID+ "=?",new String[]{id+ ""},null,null,null,null,null);
        if(c!=null&&c.moveToFirst()){
            note=builNoteFromCursor(c);
            if(!c.isClosed()){
                c.close();
            }

        }
        return note;
    }
    public List<Note> getAll(){
        List<Note> notes= new ArrayList<Note>();
        Cursor c=db.query(NoteTable.TABLENAME,new String[]{NoteTable.COLUMN_ID,NoteTable.COLUMN_NOTE,NoteTable.COLUMN_PRIORITY,NoteTable.COLUMN_STATUS,NoteTable.COLUMN_UPDATETIME,NoteTable.COLUMN_PRIORITYCODE},null,null,null,null,null);
        if(c!=null&&c.moveToFirst()){
            do{
                Note note= builNoteFromCursor(c);
                if(note!=null){
                    notes.add(note);
                }

            }while (c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }
        return notes;
    }
    private Note builNoteFromCursor(Cursor c){
        Note note=null;
        if(c!=null){
            note=new Note();
            note.set_id(c.getLong(0));
            note.setNote(c.getString(1));
            note.setPriority(c.getString(2));
            note.setStatus(c.getInt(3));
            note.setUpdatetime(c.getLong(4));
            note.setPrioritycode(c.getInt(5));

        }
        return note;
    }
}
