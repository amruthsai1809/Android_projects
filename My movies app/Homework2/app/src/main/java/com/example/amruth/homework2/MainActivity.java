package com.example.amruth.homework2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int addmovie_code=100;
    public static final int editmovie_code=200;
    public static final String movie_key = "add";
    public static final String edit_key = "edit";


    ArrayList<Movies> movies= new ArrayList<Movies>();
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.addmovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this, addmovie.class);
                startActivityForResult(i, addmovie_code );
            }
        });
        findViewById(R.id.Showbyyear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (movies.size() == 0){
                    Toast.makeText(MainActivity.this, "Please add a movie before displaying", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent yintent= new Intent(MainActivity.this, YearActivity.class);
                yintent.putExtra("yearkey",movies);
                startActivity(yintent);

            }
        });
        findViewById(R.id.showbyrating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (movies.size() == 0){
                    Toast.makeText(MainActivity.this, "Please add a movie before displaying", Toast.LENGTH_SHORT).show();
                    return;
                }



                Intent yintent= new Intent(MainActivity.this, Showbyrating.class);
                yintent.putExtra("ratingkey",movies);
                startActivity(yintent);

            }
        });








    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == addmovie_code){
            if (resultCode == RESULT_OK){

               movies.add((Movies) data.getExtras().getSerializable(movie_key));
                //Toast.makeText(this, data.getExtras().getSerializable(movie_key).toString(), Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == editmovie_code){
            if (resultCode == RESULT_OK){

                movies.remove(data.getExtras().getInt("index2"));
                movies.add(data.getExtras().getInt("index2"), (Movies) data.getExtras().getSerializable(edit_key));
            }
        }
    }

    public void onEdit(View view){


        CharSequence[] movie_names;

        if (movies.size() == 0){
            Toast.makeText(this, "Please add a movie before editing", Toast.LENGTH_SHORT).show();
            return;
        }

        movie_names= new String[movies.size()];
        int i=0;
        for (Movies mo : movies){

            movie_names[i++]= mo.getName().toString();
            //Toast.makeText(MainActivity.this, movie_names[i-1], Toast.LENGTH_SHORT).show();

        }

        builder = new AlertDialog.Builder(this);

        builder.setTitle("Pick a color")
                .setItems(movie_names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(MainActivity.this, "asd", Toast.LENGTH_SHORT).show();
                        Movies selectedMovie = movies.get(i);
                        Intent editIntent = new Intent(MainActivity.this, EditActivity.class);
                        editIntent.putExtra(edit_key, selectedMovie);
                        editIntent.putExtra("index", i);
                        startActivityForResult(editIntent, editmovie_code);
                    }
                });


        final AlertDialog alert = builder.create();
        alert.show();
    }





    public void onDelete(View view){
        CharSequence[] movie_names;


        if (movies.size() == 0){
            Toast.makeText(MainActivity.this, "Please add a movie before deleting", Toast.LENGTH_SHORT).show();
            return;
        }




        movie_names= new String[movies.size()];
        int i=0;
        for (Movies mo : movies){

            movie_names[i++]= mo.getName().toString();
            //Toast.makeText(MainActivity.this, movie_names[i-1], Toast.LENGTH_SHORT).show();

        }

        builder = new AlertDialog.Builder(this);

        builder.setTitle("Pick a color")
                .setItems(movie_names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(MainActivity.this, "asd", Toast.LENGTH_SHORT).show();
                        Movies selectedMovie = movies.get(i);
                        Toast.makeText(MainActivity.this, "Movie: "+selectedMovie.getName() + " is deleted", Toast.LENGTH_SHORT).show();
                        movies.remove(i);
                    }
                });


        final AlertDialog alert = builder.create();
        alert.show();

    }




    public  void onYear(View view){

    }
}
