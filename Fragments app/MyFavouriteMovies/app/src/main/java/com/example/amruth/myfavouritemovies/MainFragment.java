package com.example.amruth.myfavouritemovies;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    OnFragmentTextChange mListener;
    ArrayList<Movie> movies;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener= (OnFragmentTextChange) context;

        }catch(ClassCastException e){
            throw new ClassCastException(context.toString()+"should implement onfragmenttextchanged");

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button addmovie= (Button) getActivity().findViewById(R.id.addmovie);
        Button editmovie= (Button) getActivity().findViewById(R.id.edit);
        Button delete= (Button) getActivity().findViewById(R.id.delete_movie);
        Button showbyyear= (Button) getActivity().findViewById(R.id.Showbyyear);
        Button showbyrating= (Button) getActivity().findViewById(R.id.showbyrating);
        movies=new ArrayList<Movie>();
        addmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movies=mListener.getData();

                mListener.addmovie();

            }
        });
        editmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.edtimovie();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mListener.delete();

            }
        });
        showbyyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.showbyyear();

            }
        });
        showbyrating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.showbyrating();

            }
        });
    }

    public interface OnFragmentTextChange{
        public void addmovie();
        public void edtimovie();
        public void delete();
        public void showbyrating();
        public void showbyyear();
        public ArrayList<Movie> getData();

    }


}
