package com.example.amruth.toppaidapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by amruth on 25/02/17.
 */

public class ReadJSON extends AsyncTask<String,Void,ArrayList<Apps>> {
    ArrayList<Apps> applist;
    readJSONFile activity;
    public ReadJSON(readJSONFile activity){
        this.activity=activity;

    }
    @Override
    protected ArrayList<Apps> doInBackground(String... strings) {
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
            try {
                JSONObject response= new JSONObject(sb.toString());
                JSONObject feed= response.optJSONObject("feed");
                JSONArray array= feed.optJSONArray("entry");
                applist=new ArrayList<Apps>();
                for(int i=0;i<array.length();i++){
                    Apps tune= new Apps();
                    JSONObject obj= array.optJSONObject(i);
                    tune.setThumbURL(obj.optJSONArray("im:image").optJSONObject(0).optString("label"));
                    tune.setAppName(obj.optJSONObject("im:name").optString("label"));
                    tune.setPrice(obj.optJSONObject("im:price").optString("label"));
                    applist.add(tune);
                }
                return applist;
            } catch (JSONException e) {
                Log.d("demo","caught");
                e.printStackTrace();

            }

        } catch (MalformedURLException e) {
            Log.d("making","some1");
            e.printStackTrace();
        } catch (ProtocolException e) {
            Log.d("making","some2");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("making","some3");
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    Log.d("making","some4");
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Apps> appses) {
        Log.d("making","some");
        activity.readFile(appses);
        super.onPostExecute(appses);
    }

    static public interface readJSONFile{
        public void readFile(ArrayList<Apps> appses);
    }
}
