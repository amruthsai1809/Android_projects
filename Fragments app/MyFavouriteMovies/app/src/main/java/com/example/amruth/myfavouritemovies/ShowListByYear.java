package com.example.amruth.myfavouritemovies;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowListByYear extends Fragment {


    OnFragmentTextChange mListner;
    int index=0;
    Movie movie1;
    TextView titleans,descans,genreans,ratingans,yearans,imdbans;
    ImageButton first,previous, next, last;
    Button finish;
    ArrayList<Movie> m= new ArrayList<Movie>();
    String[] genres= {"ACTION", "ANIMATION","COMEDY","DOCUMENTARY","FAMILY","HORROR","CRIME","OHTERS"};


    public ShowListByYear() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListner= (OnFragmentTextChange) context;

        }catch(ClassCastException e){
            throw new ClassCastException(context.toString()+"should implement onfragmenttextchanged");

        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_list_by_year, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        m= mListner.returnlist2();
        titleans = (TextView) getActivity().findViewById(R.id.titleans9);
        descans= (TextView) getActivity().findViewById(R.id.descans9);
        genreans=(TextView) getActivity().findViewById(R.id.genreans9);
        ratingans=(TextView) getActivity().findViewById(R.id.ratingans9);
        yearans= (TextView) getActivity().findViewById(R.id.yearans9);
        imdbans=(TextView) getActivity().findViewById(R.id.link9);


        Collections.sort(m, new Comparator<Movie>() {
            @Override
            public int compare(Movie movies, Movie t1) {
                if(movies.getYear()>t1.getYear()){
                    return 1;
                }else if(movies.getYear()<t1.getYear()){
                    return -1;
                }
                return 0;
            }
        });


        movie1= m.get(index);
        this.display(movie1);

        getActivity().findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie m1= m.get(0);
                index=0;
                display(m1);
            }
        });
        getActivity().findViewById(R.id.imageButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index<=0){
                    index=0;
                    Movie m1=m.get(0);
                    display(m1);
                }else {
                    Movie m1 = m.get(index - 1);
                    index = index - 1;
                    display(m1);
                }
            }
        });
        getActivity().findViewById(R.id.imageButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index>=m.size()-1){
                    index=m.size()-1;
                    Movie m1=m.get(m.size()-1);
                    display(m1);
                }else {
                    Movie m1 = m.get(index + 1);
                    index = index + 1;
                    display(m1);
                }

            }
        });
        getActivity().findViewById(R.id.imageButton4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie m1= m.get(m.size()-1);
                index=m.size()-1;
                display(m1);
            }
        });
        getActivity().findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListner.changeToMain2();
            }
        });



    }

    public void display(Movie movie){
        titleans.setText(movie.getName());
        descans.setText(movie.getDescription());
        genreans.setText(genres[movie.getGenre()]);
        ratingans.setText(movie.getRating()+"/5");
        yearans.setText(movie.getYear()+"");
        imdbans.setText(movie.getImdb());

    }





    public interface OnFragmentTextChange{
        public ArrayList<Movie> returnlist2();
        public void changeToMain2();
    }

}
