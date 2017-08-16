package com.example.amruth.toppaidapps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by amruth on 25/02/17.
 */

public class AppsAdapter extends ArrayAdapter<Apps> {
    List<Apps> myData;
    Context myContext;
    int myResource;
    //Apps app;

    SharedPreferences sharedPreferences;
    public AppsAdapter(Context context, int resource, List<Apps> objects) {
        super(context, resource, objects);
        this.myContext=context;
        this.myData=objects;
        this.myResource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(myResource,parent,false);
        }
        final Apps app;
        sharedPreferences= myContext.getSharedPreferences("mydata", Context.MODE_PRIVATE);
        app= myData.get(position);
        TextView title= (TextView) convertView.findViewById(R.id.textView2);
        TextView price=(TextView) convertView.findViewById(R.id.textView);
        ImageView imageView=(ImageView) convertView.findViewById(R.id.imageView);

        final ImageButton imageButton=(ImageButton) convertView.findViewById(R.id.imageButton);
        imageButton.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        title.setText(app.getAppName());
        price.setText("$"+app.getPrice());
        if(sharedPreferences.getString(app.getAppName(),"")==""){
            Log.d("testtest",position+"");
            imageButton.setImageResource(R.drawable.whitestar);

        }else {
            Log.d("testtest",position+"wrong");
            imageButton.setImageResource(R.drawable.blackstar);
        }
        Picasso.with(myContext).load(app.getThumbURL()).into(imageView);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferences.getString(app.getAppName(),"")==""){


                      new AlertDialog.Builder(myContext)
                            .setTitle("Add to Favourites")
                            .setMessage("Are you sure that you want to add this app to favourites?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    SharedPreferences.Editor editor= sharedPreferences.edit();
                                    Gson gson= new Gson();
                                    String json= gson.toJson(app);
                                    editor.putString(app.getAppName(),json);
                                    editor.commit();
                                    imageButton.setImageResource(R.drawable.blackstar);

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();



                    /*SharedPreferences.Editor editor= sharedPreferences.edit();
                    Gson gson= new Gson();
                    String json= gson.toJson(app);
                    editor.putString(app.getAppName(),json);
                    editor.commit();
                    imageButton.setImageResource(R.drawable.blackstar);*/

                }
                else {


                    new AlertDialog.Builder(myContext)
                            .setTitle("Add to Favourites")
                            .setMessage("Are you sure that you want to remove this app from favourites?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    SharedPreferences.Editor editor= sharedPreferences.edit();

                                    editor.remove(app.getAppName());
                                    editor.commit();
                                    Log.d("stringtest","testtest");
                                    imageButton.setImageResource(R.drawable.whitestar);


                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();








                    /*SharedPreferences.Editor editor= sharedPreferences.edit();
                    Gson gson= new Gson();
                    String json=sharedPreferences.getString(app.getAppName(),"");
                    Apps a=gson.fromJson(json,Apps.class);
                    Log.d("something",a.appName);

                    editor.remove(app.getAppName());
                    editor.commit();
                    Log.d("stringtest","testtest");
                    imageButton.setImageResource(R.drawable.whitestar);*/


                }



            }
        });

        return convertView;
    }
}
