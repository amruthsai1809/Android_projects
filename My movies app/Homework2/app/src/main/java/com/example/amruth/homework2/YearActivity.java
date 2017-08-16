package com.example.amruth.homework2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YearActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_year);

        m= (ArrayList<Movies>) getIntent().getExtras().getSerializable("yearkey");
        titleans = (TextView) findViewById(R.id.titleans9);
        descans= (TextView) findViewById(R.id.descans9);
        genreans=(TextView) findViewById(R.id.genreans9);
        ratingans=(TextView) findViewById(R.id.ratingans9);
        yearans= (TextView) findViewById(R.id.yearans9);
        imdbans=(TextView) findViewById(R.id.link9);
        Collections.sort(m);
        movie1= m.get(index);
        this.display(movie1);


        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movies m1= m.get(0);
                index=0;
                YearActivity.this.display(m1);
            }
        });
        findViewById(R.id.imageButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index<=0){
                    index=0;
                    Movies m1=m.get(0);
                    YearActivity.this.display(m1);
                }else {
                    Movies m1 = m.get(index - 1);
                    index = index - 1;
                    YearActivity.this.display(m1);
                }

            }
        });
        findViewById(R.id.imageButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index>=m.size()-1){
                    index=m.size()-1;
                    Movies m1=m.get(m.size()-1);
                    YearActivity.this.display(m1);
                }else {
                    Movies m1 = m.get(index + 1);
                    index = index + 1;
                    YearActivity.this.display(m1);
                }

            }
        });
        findViewById(R.id.imageButton4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movies m1= m.get(m.size()-1);
                index=m.size()-1;
                YearActivity.this.display(m1);
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }
}
