package com.example.amruth.myfavouritemovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentTextChange ,AddMovie.OnFragmentTextChange,EditMovie.OnFragmentTextChange,ShowListByRating.OnFragmentTextChange, ShowListByYear.OnFragmentTextChange{
    ArrayList<Movie> moviesList;
    Movie editedmovie;
    int editedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        moviesList=new ArrayList<Movie>();
        getSupportFragmentManager().beginTransaction().add(R.id.maincontainer,new MainFragment(),"mainfragment").commit();
    }

    @Override
    public void addmovie() {
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer,new AddMovie(),"addmovie").addToBackStack(null).commit();

    }

    @Override
    public void edtimovie() {
        AlertDialog.Builder builder;

        CharSequence[] movie_names;


        if (moviesList.size() == 0){
            Toast.makeText(MainActivity.this, "Please add a movie before deleting", Toast.LENGTH_SHORT).show();
            return;
        }




        movie_names= new String[moviesList.size()];
        editedIndex=0;
        for (Movie mo : moviesList){

            movie_names[editedIndex++]= mo.getName().toString();
            //Toast.makeText(MainActivity.this, movie_names[i-1], Toast.LENGTH_SHORT).show();

        }

        builder = new AlertDialog.Builder(this);

        builder.setTitle("Pick a color")
                .setItems(movie_names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(MainActivity.this, "asd", Toast.LENGTH_SHORT).show();
                        Movie selectedMovie = moviesList.get(i);
                        editedIndex = i;
                        editedmovie=selectedMovie;
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer,new EditMovie(),"editmovie").addToBackStack(null).commit();



//                        Movies selectedMovie = movies.get(i);


                    }
                });


        final AlertDialog alert = builder.create();
        alert.show();


    }

    @Override
    public void delete() {
        AlertDialog.Builder builder;

        CharSequence[] movie_names;


        if (moviesList.size() == 0){
            Toast.makeText(MainActivity.this, "Please add a movie before deleting", Toast.LENGTH_SHORT).show();
            return;
        }




        movie_names= new String[moviesList.size()];
        int i=0;
        for (Movie mo : moviesList){

            movie_names[i++]= mo.getName().toString();
            //Toast.makeText(MainActivity.this, movie_names[i-1], Toast.LENGTH_SHORT).show();

        }

        builder = new AlertDialog.Builder(this);

        builder.setTitle("Pick a color")
                .setItems(movie_names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(MainActivity.this, "asd", Toast.LENGTH_SHORT).show();
                        Movie selectedMovie = moviesList.get(i);
                        Toast.makeText(MainActivity.this, "Movie: "+selectedMovie.getName() + " is deleted", Toast.LENGTH_SHORT).show();
                        moviesList.remove(i);
                        //AFragment f= (AFragment) getFragmentManager().findFragmentByTag("tag_afragement");


                    }
                });


        final AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void showbyrating() {
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer,new ShowListByRating(),"rating").addToBackStack(null).commit();


    }

    @Override
    public void showbyyear() {
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer,new ShowListByYear(),"year").addToBackStack(null).commit();

    }

    @Override
    public ArrayList<Movie> getData() {
        return moviesList;
    }

    @Override
    public void returnAddedMovie(Movie m) {
        moviesList.add(m);
        onBackPressed();
//        getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer,new AddMovie(),"addmovie").addToBackStack(null).commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void returnEditedMovie(Movie m) {
        moviesList.remove(editedIndex);
        moviesList.add(editedIndex,m);
        onBackPressed();
    }

    @Override
    public Movie getMovie() {
        return editedmovie;
    }

    @Override
    public ArrayList<Movie> returnlist() {
        return moviesList;
    }

    @Override
    public void changeToMain() {
        onBackPressed();
    }
    @Override
    public ArrayList<Movie> returnlist2() {
        return moviesList;
    }

    @Override
    public void changeToMain2() {
        onBackPressed();
    }
}
