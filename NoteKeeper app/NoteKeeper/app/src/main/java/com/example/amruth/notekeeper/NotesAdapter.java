package com.example.amruth.notekeeper;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.List;

/**
 * Created by amruth on 27/02/17.
 */

public class NotesAdapter extends ArrayAdapter<Note>{
    List<Note> myData;
    Context myContext;
    int myResource;
    PrettyTime p;

    public NotesAdapter(Context context, int resource, List<Note> objects) {
        super(context, resource, objects);
        this.myContext=context;
        this.myData=objects;
        this.myResource=resource;
       // MainActivity activity= myContext;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        p = new PrettyTime();
        final DatabaseDataManager dm=new DatabaseDataManager(myContext);
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(myResource,parent,false);
        }

        final Note n = myData.get(position);
        TextView tvNotes, tvPrioroty, tvTime;
        final CheckBox checkBox;

        tvNotes = (TextView) convertView.findViewById(R.id.lNote);
        tvPrioroty = (TextView) convertView.findViewById(R.id.lPriority);
        tvTime = (TextView) convertView.findViewById(R.id.lUpdateTime);
        checkBox = (CheckBox) convertView.findViewById(R.id.lStatus);

        tvNotes.setText(n.getNote());
        tvPrioroty.setText(n.getPriority());
        tvTime.setText(p.format(new Date(n.getUpdatetime())));
//vSystem.out.println(p.format(new Date(System.currentTimeMillis() + 1000*60*10)));
        if (n.getStatus() == 0)
            checkBox.setChecked(false);
        else
            checkBox.setChecked(true);

        checkBox.setFocusable(false);


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                final boolean bb = b;
                if (b == true){
//                    dm.updateNote()

                    new AlertDialog.Builder(myContext)
                            .setTitle("Do you really want to mark it as Complete??")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    n.setStatus(1);
                                    n.setUpdatetime(System.currentTimeMillis());
                                    dm.updateNote(n);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .show();
                } else {


                    new AlertDialog.Builder(myContext)
                            .setTitle("Do you really want to mark it as Pending??")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    n.setStatus(0);
                                    n.setUpdatetime(System.currentTimeMillis());
                                    dm.updateNote(n);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();


                }
            }
        });


        return convertView;
    }
}
