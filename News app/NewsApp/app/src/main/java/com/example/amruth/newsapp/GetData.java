package com.example.amruth.newsapp;

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

public class GetData extends AsyncTask<String,Void,String>{
    makeUi activity;
    public GetData(makeUi activity){
        this.activity=activity;


    }

    @Override
    protected String doInBackground(String... strings) {
        BufferedReader br=null;
        URL url= null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection hcon= (HttpURLConnection) url.openConnection();
            hcon.setRequestMethod("GET");
            br= new BufferedReader(new InputStreamReader(hcon.getInputStream()));
            String line="";
            StringBuilder sb= new StringBuilder();
            while((line=br.readLine())!=null){
                sb.append(line+"\n");

            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        activity.setUpUi(s);
        super.onPostExecute(s);
    }

    static public interface makeUi{
        public void setUpUi(String  s);
    }

}
