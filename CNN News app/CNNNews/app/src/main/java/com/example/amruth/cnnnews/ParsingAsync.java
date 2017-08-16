package com.example.amruth.cnnnews;

import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by amruth on 13/02/17.
 */

public class ParsingAsync extends AsyncTask<String,Void,ArrayList<News>> {
    makeUi activity;
    public ParsingAsync(makeUi activity){
        this.activity=activity;


    }
    @Override
    protected ArrayList<News> doInBackground(String... strings) {
        URL url= null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection hcon= (HttpURLConnection) url.openConnection();
            hcon.setRequestMethod("GET");
            hcon.connect();
            InputStream in=hcon.getInputStream();
            return NewsUtil.NewClass.newMethod(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<News> newses) {
        super.onPostExecute(newses);
        activity.setupUI(newses);


    }
    static public interface makeUi{
        public void setupUI(ArrayList<News> newses);
    }
}
