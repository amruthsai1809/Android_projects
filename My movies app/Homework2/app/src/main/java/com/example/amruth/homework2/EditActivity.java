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

public class EditActivity extends AppCompatActivity {

    EditText name, desc, imdb, year;
    SeekBar rating;
    Spinner genre;
    TextView seek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent ii =  getIntent();
        Movies m = (Movies) ii.getSerializableExtra(MainActivity.edit_key);

        //Toast.makeText(this, m.getDescription(), Toast.LENGTH_SHORT).show();


        name = (EditText) findViewById(R.id.nametexte);
        desc = (EditText) findViewById(R.id.descritpionedite);
        year = (EditText) findViewById(R.id.yeardesce);
        imdb = (EditText) findViewById(R.id.imdbdesce);
        rating = (SeekBar) findViewById(R.id.seekBare);
        genre = (Spinner) findViewById(R.id.spinner2e);
        seek = (TextView) findViewById(R.id.textView2e);
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

    }


    public void onSaveMovie(View view){
        if (verify_input()){
            Movies m = new Movies(name.getText().toString() ,
                    desc.getText().toString(),
                    genre.getSelectedItemPosition(),
                    imdb.getText().toString(),
                    rating.getProgress(),
                    Integer.parseInt(year.getText().toString().trim()));

            Intent intent = new Intent();
            intent.putExtra(MainActivity.edit_key, m);
            intent.putExtra("index2", getIntent().getExtras().getInt("index"));
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
