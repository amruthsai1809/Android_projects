package com.example.amruth.newsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements GetData.makeUi,GetImaged.makeImage {
    TextView title,author,publishedOn,content,description;
    Spinner category;
    Button getNews,finish;
    ImageView imageView;
    ImageButton first,prev,next,last;
    String[] sources={"bbc-news","cnn","buzzfeed","espn","sky-news"};
    News[] news;
    int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        title= (TextView) findViewById(R.id.title);
        author=(TextView) findViewById(R.id.author);
        content=(TextView) findViewById(R.id.content);
        publishedOn=(TextView) findViewById(R.id.publishedOn);
        category=(Spinner) findViewById(R.id.spinner);
        getNews=(Button) findViewById(R.id.getNews);
        finish=(Button) findViewById(R.id.button2);
        imageView=(ImageView) findViewById(R.id.imageView);
        first=(ImageButton) findViewById(R.id.imageButton);
        prev=(ImageButton) findViewById(R.id.imageButton2);
        next=(ImageButton) findViewById(R.id.imageButton3);
        last=(ImageButton) findViewById(R.id.imageButton4);
        author.setText("");
        //Toast.makeText(this,finalUrl,Toast.LENGTH_SHORT).show();
        finish.setEnabled(false);
        first.setEnabled(false);
        prev.setEnabled(false);
        next.setEnabled(false);
        last.setEnabled(false);
        description= (TextView) findViewById(R.id.descrption);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 0)
                Toast.makeText(MainActivity.this,"you reached starting of the list",Toast.LENGTH_SHORT).show();
                index = 0;
                title.setText("TITLE: "+news[index].getTitle());
                author.setText("AUTHOR: "+news[index].getAuthor());
                content.setText(news[index].getDescription());
                publishedOn.setText("PUBLISHED ON: "+news[index].getPublishedAt());
                new GetImaged(MainActivity.this).execute(news[index].getUrlImage());

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(index==news.length-1){
                    Toast.makeText(MainActivity.this,"you reached the end of list",Toast.LENGTH_SHORT).show();
                }else{
                    index++;
                    title.setText("TITLE: "+news[index].getTitle());
                    author.setText("AUTHOR: "+news[index].getAuthor());
                    content.setText(news[index].getDescription());
                    publishedOn.setText("PUBLISHED ON: "+news[index].getPublishedAt());
                    new GetImaged(MainActivity.this).execute(news[index].getUrlImage());

                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index==0){
                    Toast.makeText(MainActivity.this,"you reached starting of the list",Toast.LENGTH_SHORT).show();
                }else{
                    index--;
                    title.setText("TITLE: "+news[index].getTitle());
                    author.setText("AUTHOR: "+news[index].getAuthor());
                    content.setText(news[index].getDescription());
                    publishedOn.setText("PUBLISHED ON: "+news[index].getPublishedAt());
                    new GetImaged(MainActivity.this).execute(news[index].getUrlImage());

                }
            }
        });
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == news.length-1)
                    Toast.makeText(MainActivity.this,"you reached starting of the list",Toast.LENGTH_SHORT).show();
                index=news.length-1;
                title.setText("TITLE: "+news[index].getTitle());
                author.setText("AUTHOR: "+news[index].getAuthor());
                content.setText(news[index].getDescription());
                publishedOn.setText("PUBLISHED ON: "+news[index].getPublishedAt());
                new GetImaged(MainActivity.this).execute(news[index].getUrlImage());

            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index=0;
                String finalUrl="https://newsapi.org/v1/articles?apiKey=44c2164dcd284629b0173100e8a5d0a4&source=";
                switch (category.getSelectedItemPosition()){
                    case 0:
                        finalUrl=finalUrl+sources[0];
                        break;
                    case 1:
                        finalUrl=finalUrl+sources[1];
                        break;
                    case 2:
                        finalUrl=finalUrl+sources[2];
                        break;
                    case 3:
                        finalUrl=finalUrl+sources[3];
                        break;
                    case 4:
                        finalUrl=finalUrl+sources[4];
                        break;
                }
               // new getData().execute(finalUrl);
                if(isConnectedOnline()){
                    new GetData(MainActivity.this).execute(finalUrl);
                }else{
                    Toast.makeText(MainActivity.this,"Please connect to the internet",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    @Override
    public void setUpUi(String s) {
        Log.d("demo",s);
        finish.setEnabled(true);
        first.setEnabled(true);
        prev.setEnabled(true);
        next.setEnabled(true);
        last.setEnabled(true);
        description.setText("Description:");
        try {
            JSONObject response= new JSONObject(s);
            JSONArray array= response.optJSONArray("articles");
            news= new News[array.length()];
            for(int i=0;i<array.length();i++){
                news[i] = new News();
                JSONObject obj = array.optJSONObject(i);
                news[i].setAuthor(obj.optString("author"));
                news[i].setDescription(obj.optString("description"));
                news[i].setPublishedAt(obj.optString("publishedAt"));
                news[i].setTitle(obj.optString("title"));
                news[i].setUrlImage(obj.optString("urlToImage"));



            }
            title.setText("TITLE: "+news[index].getTitle());
            author.setText("AUTHOR: "+news[index].getAuthor());
            content.setText(news[index].getDescription());
            publishedOn.setText("PUBLISHED ON: "+news[index].getPublishedAt());
            new GetImaged(MainActivity.this).execute(news[index].getUrlImage());



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
    public boolean isConnectedOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();
        if (nf != null && nf.isConnected()) {
            return true;
        } else {
            return false;
        }
    }


}
