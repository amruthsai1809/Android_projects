package com.example.amruth.cnnnews;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ParsingAsync.makeUi,getIMage.setImage {
    TextView title,description,published;
    ImageView im;
    Button getnews,finish;
    ArrayList<News> finallist;
    ImageButton first,prev,next,last;
    int count=0;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("CNN NEWS");
        //getActionBar().setIcon(R.drawable.cnn);
        getSupportActionBar().setIcon(R.drawable.cnnn);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        title=(TextView) findViewById(R.id.thetitle);
        description=(TextView) findViewById(R.id.descripton);
        published=(TextView) findViewById(R.id.publish);
        im=(ImageView) findViewById(R.id.imageView);
        getnews= (Button) findViewById(R.id.button);
        finish=(Button) findViewById(R.id.finish);
        first= (ImageButton) findViewById(R.id.first);
        prev= (ImageButton) findViewById(R.id.prev);
        next= (ImageButton) findViewById(R.id.next);
        last= (ImageButton) findViewById(R.id.last);
        first.setEnabled(false);
        last.setEnabled(false);
        prev.setEnabled(false);
        next.setEnabled(false);
        title.setText("");
        description.setText("");
        published.setText("");


        getnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog= new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                new ParsingAsync(MainActivity.this).execute("http://rss.cnn.com/rss/cnn_tech.rss");


            }
        });
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=0;
                title.setText("Title  :"+finallist.get(0).getTitle());
                description.setText("Description  :"+finallist.get(0).getDescription());
                published.setText("published at   :"+finallist.get(0).getPubDate()+"");
                new getIMage(MainActivity.this).execute(finallist.get(0).getImageURL());


            }
        });
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=finallist.size()-1;
                title.setText("Title  :"+finallist.get(finallist.size()-1).getTitle());
                description.setText("Description  :"+finallist.get(finallist.size()-1).getDescription());
                published.setText("published at   :"+finallist.get(finallist.size()-1).getPubDate()+"");
                new getIMage(MainActivity.this).execute(finallist.get(finallist.size()-1).getImageURL());

            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>0){
                    count--;
                }
                title.setText("Title  :"+finallist.get(count).getTitle());
                description.setText("Description  :"+finallist.get(count).getDescription());
                published.setText("published at   :"+finallist.get(count).getPubDate()+"");
                new getIMage(MainActivity.this).execute(finallist.get(count).getImageURL());

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count<finallist.size()-1){
                    count++;
                }
                title.setText("Title  :"+finallist.get(count).getTitle());
                description.setText("Description  :"+finallist.get(count).getDescription());
                published.setText("published at   :"+finallist.get(count).getPubDate()+"");
                new getIMage(MainActivity.this).execute(finallist.get(count).getImageURL());


            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

    @Override
    public void setupUI(ArrayList<News> newses) {
        finallist= new ArrayList<News>();
        finallist=newses;
        title.setText("Title  :"+finallist.get(0).getTitle());
        description.setText("Description  :"+finallist.get(0).getDescription());
        published.setText("published at   :"+finallist.get(0).getPubDate()+"");
        new getIMage(MainActivity.this).execute(finallist.get(0).getImageURL());
        //Log.d("demo", finallist.get(0).getDescription()+"");
        //Log.d("demo", finallist.get(1).getDescription()+"");


    }

    @Override
    public void setImageView(Bitmap bitmap) {
        im.setImageBitmap(bitmap);
        progressDialog.dismiss();
        first.setEnabled(true);
        last.setEnabled(true);
        prev.setEnabled(true);
        next.setEnabled(true);
    }
}
