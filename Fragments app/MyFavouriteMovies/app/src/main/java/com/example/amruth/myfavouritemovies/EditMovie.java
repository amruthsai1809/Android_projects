package com.example.amruth.myfavouritemovies;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditMovie extends Fragment {
     EditText name, desc, imdb, year;
     SeekBar rating;
     Spinner genre;
     TextView seek;
    OnFragmentTextChange mListner;

    public EditMovie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_movie, container, false);
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
        Movie m= mListner.getMovie();







        name = (EditText) getActivity().findViewById(R.id.nametexte);
        desc = (EditText) getActivity().findViewById(R.id.descritpionedite);
        year = (EditText) getActivity().findViewById(R.id.yeardesce);
        imdb = (EditText) getActivity().findViewById(R.id.imdbdesce);
        rating = (SeekBar) getActivity().findViewById(R.id.seekBare);
        genre = (Spinner) getActivity().findViewById(R.id.spinner2e);
        seek = (TextView) getActivity().findViewById(R.id.textView2e);
        rating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seek.setText(i + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        name.setText(m.getName());
        desc.setText(m.getDescription());
        year.setText(m.getYear() + "");
        imdb.setText(m.getImdb());
        rating.setProgress(m.getRating());
        genre.setSelection(m.getGenre());
        seek.setText(m.getRating() + "");

        getActivity().findViewById(R.id.saveChanges).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verify_input()){
                    Movie m = new Movie(name.getText().toString() ,
                            desc.getText().toString(),
                            genre.getSelectedItemPosition(),
                            imdb.getText().toString(),
                            rating.getProgress(),
                            Integer.parseInt(year.getText().toString().trim()));
                    mListner.returnEditedMovie(m);
                }
            }
        });
    }


    public Boolean verify_input(){
        String var;
        var = imdb.getText().toString().trim();
        if ((var.isEmpty() || var.length() == 0 || var.equals("") || var == null)){
            Toast.makeText(getActivity(), "Please enter IMDB link", Toast.LENGTH_SHORT).show();
            return false;
        }
        //validating URL format
        String REGEX_URL = "[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";
        if (!var.matches(REGEX_URL)){
            Toast.makeText(getActivity(), "Please enter valid URL", Toast.LENGTH_SHORT).show();
            return false;
        }

        var = name.getText().toString().trim();
        if ((var.isEmpty() || var.length() == 0 || var.equals("") || var == null)){
            Toast.makeText(getActivity(), "Please enter Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        var = desc.getText().toString().trim();
        if ((var.isEmpty() || var.length() == 0 || var.equals("") || var == null)){
            Toast.makeText(getActivity(), "Please enter Description", Toast.LENGTH_SHORT).show();
            return false;
        }
        var = year.getText().toString().trim();
        if ((var.isEmpty() || var.length() == 0 || var.equals("") || var == null)){
            Toast.makeText(getActivity(), "Please enter Year"+var, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public interface OnFragmentTextChange{
        public void returnEditedMovie(Movie m);
        public Movie getMovie();
    }
}
