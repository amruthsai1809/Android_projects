package com.example.amruth.triviaapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by amruth on 08/02/17.
 */

public class ReadJSON extends AsyncTask<String,Void,String> {
    readJSONFile activity;
    public ReadJSON(readJSONFile activity){
        this.activity=activity;

    }

    @Override
    protected String doInBackground(String... strings) {
        BufferedReader br=null;
        try {
            URL url= new URL(strings[0]);
            HttpURLConnection hcon= (HttpURLConnection) url.openConnection();
            hcon.setRequestMethod("GET");
            String line="";
            StringBuilder sb= new StringBuilder();
            br=new BufferedReader(new InputStreamReader(hcon.getInputStream()));
            while((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            //Log.d("demo","sb.toString()+"ghfhghfcfgcfgch");
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
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
        super.onPostExecute(s);
        activity.readFile(s);
    }

    static public interface readJSONFile{
        public void readFile(String s);
    }
}
