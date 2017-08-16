package com.example.amruth.newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by amruth on 06/02/17.
 */

public class GetImaged extends AsyncTask<String,Void,Bitmap> {
    makeImage activity;
    public GetImaged(makeImage activity){
        this.activity=activity;


    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        URL url= null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection hcon= (HttpURLConnection) url.openConnection();
            hcon.setRequestMethod("GET");
            Bitmap bitmap= BitmapFactory.decodeStream(hcon.getInputStream());
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        activity.setImage(bitmap);
        super.onPostExecute(bitmap);
    }

    static public interface makeImage{
        public void setImage(Bitmap bitmap);
    }
}
