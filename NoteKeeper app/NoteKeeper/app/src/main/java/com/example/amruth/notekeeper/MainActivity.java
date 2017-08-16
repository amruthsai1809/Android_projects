package com.example.amruth.notekeeper;

import android.content.ClipData;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DatabaseDataManager dm;
    ListView listView;
    EditText et;
    Button add;
    Spinner spinner;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        dm=new DatabaseDataManager(this);
        spinner= (Spinner) findViewById(R.id.spinner);

        //String note, String priority, int status, int prioritycode, long updatetime)
        //dm.saveNote(new Note("text","pri", 1,1, System.currentTimeMillis()));
        //dm.saveNote(new Note("stext","prio", 0,1, System.currentTimeMillis()));

        List<Note> notes= dm.getAllNotes();
        Log.d("demo",notes.toString());
        listView = (ListView) findViewById(R.id.listView);


        NotesAdapter adapter= new NotesAdapter(this, R.layout.list_row,notes);
        listView.setAdapter(adapter);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        et=(EditText) findViewById(R.id.editText);
        add= (Button) findViewById(R.id.add);

//        listView.setClickable(true);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note= new Note();
                if(et.getText().toString().trim()!=""&&et.getText().toString().trim()!=null){
                    String notes=et.getText().toString();
                    int pos=spinner.getSelectedItemPosition();
                    long time= System.currentTimeMillis();
                    note.setNote(notes);
                    note.setUpdatetime(time);
                    note.setStatus(0);
                    note.setPrioritycode(pos+1);
                    if(pos==0){
                        note.setPriority("High");
                    }else if(pos==1){
                        note.setPriority("Medium");
                    }else if(pos==2){
                        note.setPriority("Low");
                    }

                }
                dm.saveNote(note);
                List<Note> notes= dm.getAllNotes();
                NotesAdapter adapter= new NotesAdapter(MainActivity.this, R.layout.list_row,notes);
                listView.setAdapter(adapter);
                et.setText("");



            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MainActivity.this, "fs", Toast.LENGTH_LONG).show();

            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int ii, long l) {
                final int iii = ii;



                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Do you really want to delete the task??")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {



                                List<Note> notes= dm.getAllNotes();

                                dm.deleteNote(notes.get(iii));
                                notes.remove(iii);

                                NotesAdapter adapter= new NotesAdapter(MainActivity.this, R.layout.list_row,notes);
                                listView.setAdapter(adapter);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();





                return false;
            }
        });






        notes= dm.getAllNotes();
        List<Note> checked, unChecked;
        checked = new ArrayList<Note>();
        unChecked = new ArrayList<Note>();


        for (int i =0 ; i<notes.size(); i++){
            Note n = notes.get(i);
            if (n.getStatus() == 0){
                unChecked.add(n);
            } else {
                checked.add(n);
            }
        }


        Collections.sort(checked, new Comparator<Note>() {
            @Override
            public int compare(Note note, Note t1) {
                if (note.getPrioritycode() < t1.getPrioritycode()){
                    return -1;
                }
                else if(note.getPrioritycode() > t1.getPrioritycode()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        Collections.sort(unChecked, new Comparator<Note>() {
            @Override
            public int compare(Note note, Note t1) {
                if (note.getPrioritycode() < t1.getPrioritycode()){
                    return -1;
                }
                else if(note.getPrioritycode() > t1.getPrioritycode()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        List<Note> newNotes = unChecked;
        newNotes.addAll(checked);



            adapter= new NotesAdapter(MainActivity.this, R.layout.list_row,newNotes);
            listView.setAdapter(adapter);



        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        List<Note> notes= dm.getAllNotes();
        List<Note> checked, unChecked;
        checked = new ArrayList<Note>();
        unChecked = new ArrayList<Note>();


        for (int i =0 ; i<notes.size(); i++){
            Note n = notes.get(i);
            if (n.getStatus() == 0){
                unChecked.add(n);
            } else {
                checked.add(n);
            }
        }


        Collections.sort(checked, new Comparator<Note>() {
            @Override
            public int compare(Note note, Note t1) {
                if (note.getPrioritycode() < t1.getPrioritycode()){
                    return -1;
                }
                else if(note.getPrioritycode() > t1.getPrioritycode()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        Collections.sort(unChecked, new Comparator<Note>() {
            @Override
            public int compare(Note note, Note t1) {
                if (note.getPrioritycode() < t1.getPrioritycode()){
                    return -1;
                }
                else if(note.getPrioritycode() > t1.getPrioritycode()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        List<Note> newNotes = unChecked;
        newNotes.addAll(checked);


        if(item.getItemId()==R.id.all || item.getItemId() == R.id.priority){


            NotesAdapter adapter= new NotesAdapter(MainActivity.this, R.layout.list_row,newNotes);
            listView.setAdapter(adapter);

        } else if (item.getItemId() == R.id.completed){
            NotesAdapter adapter= new NotesAdapter(MainActivity.this, R.layout.list_row,checked);
            listView.setAdapter(adapter);
        } else if (item.getItemId() == R.id.pending){

            List<Note> pending = new ArrayList<Note>();
            for (int i =0 ; i<notes.size(); i++){
                Note n = notes.get(i);
                if (n.getStatus() == 0){
                    pending.add(n);
                }
            }

            NotesAdapter adapter= new NotesAdapter(MainActivity.this, R.layout.list_row,pending);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else if (item.getItemId() == R.id.time){

            Collections.sort(notes, new Comparator<Note>() {
                @Override
                public int compare(Note note, Note t1) {
                    if (note.getUpdatetime() > t1.getUpdatetime()){
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });

            NotesAdapter adapter= new NotesAdapter(MainActivity.this, R.layout.list_row,notes);
            listView.setAdapter(adapter);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        dm.close();
        super.onDestroy();
    }
}
