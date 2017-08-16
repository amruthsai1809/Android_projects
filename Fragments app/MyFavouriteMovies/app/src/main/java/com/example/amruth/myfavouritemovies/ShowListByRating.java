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
public class ShowListByRating extends Fragment {



    OnFragmentTextChange mListner;
    int index=0;
    Movie movie1;
    TextView titleans,descans,genreans,ratingans,yearans,imdbans;
    ImageButton first,previous, next, last;
    Button finish;
    ArrayList<Movie> m= new ArrayList<Movie>();
    String[] genres= {"ACTION", "ANIMATION","COMEDY","DOCUMENTARY","FAMILY","HORROR","CRIME","OHTERS"};
    public void display(Movie movie){
        titleans.setText(movie.getName());
        descans.setText(movie.getDescription());
        genreans.setText(genres[movie.getGenre()]);
        ratingans.setText(movie.getRating()+"/5");
        yearans.setText(movie.getYear()+"");
        imdbans.setText(movie.getImdb());

    }


    public ShowListByRating() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_list_by_rating, container, false);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        titleans = (TextView) getActivity().findViewById(R.id.titleans9r);
        descans= (TextView) getActivity().findViewById(R.id.descans9r);
        genreans=(TextView) getActivity().findViewById(R.id.genreans9r);
        ratingans=(TextView) getActivity().findViewById(R.id.ratingans9r);
        yearans= (TextView) getActivity().findViewById(R.id.yearans9r);
        imdbans=(TextView) getActivity().findViewById(R.id.link9r);
        m=mListner.returnlist();
        Collections.sort(m, new Comparator<Movie>() {
            @Override
            public int compare(Movie movies, Movie t1) {
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
        getActivity().findViewById(R.id.imageButtonr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie m1= m.get(0);
                index=0;
                display(m1);
            }
        });
        getActivity().findViewById(R.id.imageButton2r).setOnClickListener(new View.OnClickListener() {
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
        getActivity().findViewById(R.id.imageButton3r).setOnClickListener(new View.OnClickListener() {
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
        getActivity().findViewById(R.id.imageButton4r).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie m1= m.get(m.size()-1);
                index=m.size()-1;
                display(m1);
            }
        });
        getActivity().findViewById(R.id.buttonr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListner.changeToMain();
            }
        });


    }

    public interface OnFragmentTextChange{
        public ArrayList<Movie> returnlist();

        public void changeToMain();
    }
}
