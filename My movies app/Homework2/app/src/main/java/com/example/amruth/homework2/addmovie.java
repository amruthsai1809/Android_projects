package com.example.amruth.homework2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class addmovie extends AppCompatActivity {

    EditText name, desc, imdb, year;
    SeekBar rating;
    Spinner genre;
    Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmovie);

        name = (EditText) findViewById(R.id.nametext);
        desc = (EditText) findViewById(R.id.descritpionedit);
        year = (EditText) findViewById(R.id.yeardesc);
        imdb = (EditText) findViewById(R.id.imdbdesc);
        rating = (SeekBar) findViewById(R.id.seekBar);
        genre = (Spinner) findViewById(R.id.spinner2);

        genre.setPrompt("Select");

        rating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            TextView seek = (TextView) findViewById(R.id.textView2);
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
    }


    public void onAddMovie(View view){

        if (verify_input()){
            Movies m = new Movies(name.getText().toString() ,
                    desc.getText().toString(),
                    genre.getSelectedItemPosition(),
                    imdb.getText().toString(),
                    rating.getProgress(),
                    Integer.parseInt(year.getText().toString().trim()));

            Intent intent = new Intent();
            intent.putExtra(MainActivity.movie_key, m);
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    public Boolean verify_input(){
        String var;
        var = imdb.getText().toString().trim();
        if ((var.isEmpty() || var.length() == 0 || var.equals("") || var == null)){
            Toast.makeText(this, "Please enter IMDB link", Toast.LENGTH_SHORT).show();
            return false;
        }
        //validating URL format
        String REGEX_URL = "[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";
        if (!var.matches(REGEX_URL)){
            Toast.makeText(this, "Please enter valid URL", Toast.LENGTH_SHORT).show();
            return false;
        }

            var = name.getText().toString().trim();
        if ((var.isEmpty() || var.length() == 0 || var.equals("") || var == null)){
            Toast.makeText(this, "Please enter Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        var = desc.getText().toString().trim();
        if ((var.isEmpty() || var.length() == 0 || var.equals("") || var == null)){
            Toast.makeText(this, "Please enter Description", Toast.LENGTH_SHORT).show();
            return false;
        }
        var = year.getText().toString().trim();
        if ((var.isEmpty() || var.length() == 0 || var.equals("") || var == null)){
            Toast.makeText(this, "Please enter Year"+var, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
