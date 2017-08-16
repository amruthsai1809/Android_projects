package com.example.amruth.homework2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Showbyrating extends AppCompatActivity {
    int index=0;
    Movies movie1;
    TextView titleans,descans,genreans,ratingans,yearans,imdbans;
    ImageButton first,previous, next, last;
    Button finish;
    ArrayList<Movies> m= new ArrayList<Movies>();
    String[] genres= {"ACTION", "ANIMATION","COMEDY","DOCUMENTARY","FAMILY","HORROR","CRIME","OHTERS"};
    public void display(Movies movie){
        titleans.setText(movie.getName());
        descans.setText(movie.getDescription());
        genreans.setText(genres[movie.getGenre()]);
        ratingans.setText(movie.getRating()+"/5");
        yearans.setText(movie.getYear()+"");
        imdbans.setText(movie.getImdb());

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showbyrating);
        m= (ArrayList<Movies>) getIntent().getExtras().getSerializable("ratingkey");
        titleans = (TextView) findViewById(R.id.titleans9r);
        descans= (TextView) findViewById(R.id.descans9r);
        genreans=(TextView) findViewById(R.id.genreans9r);
        ratingans=(TextView) findViewById(R.id.ratingans9r);
        yearans= (TextView) findViewById(R.id.yearans9r);
        imdbans=(TextView) findViewById(R.id.link9r);
        Collections.sort(m, new Comparator<Movies>() {
            @Override
            public int compare(Movies movies, Movies t1) {
                if(movies.getRating()>t1.getRating()){
                    return -1;
                }else if(movies.getRating()<t1.getRating()){
                    return 1;
                }
                return 0;
            }
        });
        movie1= m.get(index);
        this.display(movie1);
        findViewById(R.id.imageButtonr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movies m1= m.get(0);
                index=0;
                Showbyrating.this.display(m1);
            }
        });
        findViewById(R.id.imageButton2r).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index<=0){
                    index=0;
                    Movies m1=m.get(0);
                    Showbyrating.this.display(m1);
                }else {
                    Movies m1 = m.get(index - 1);
                    index = index - 1;
                    Showbyrating.this.display(m1);
                }
            }
        });
        findViewById(R.id.imageButton3r).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index>=m.size()-1){
                    index=m.size()-1;
                    Movies m1=m.get(m.size()-1);
                    Showbyrating.this.display(m1);
                }else {
                    Movies m1 = m.get(index + 1);
                    index = index + 1;
                    Showbyrating.this.display(m1);
                }

            }
        });
        findViewById(R.id.imageButton4r).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movies m1= m.get(m.size()-1);
                index=m.size()-1;
                Showbyrating.this.display(m1);
            }
        });
        findViewById(R.id.buttonr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
