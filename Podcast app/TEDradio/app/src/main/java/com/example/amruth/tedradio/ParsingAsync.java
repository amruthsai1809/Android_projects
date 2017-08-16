package com.example.amruth.tedradio;

import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by amruth on 11/03/17.
 */

public class ParsingAsync extends AsyncTask<String, Void, ArrayList<Item>> {
    makeUi activity;
    public ParsingAsync(makeUi activity){
        this.activity=activity;


    }
    @Override
    protected ArrayList<Item> doInBackground(String... strings) {
        URL url= null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection hcon= (HttpURLConnection) url.openConnection();
            hcon.setRequestMethod("GET");
            hcon.connect();
            InputStream in=hcon.getInputStream();
            return ItemsUtil.NewClass.newMethod(in);
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
    protected void onPostExecute(ArrayList<Item> items) {
        super.onPostExecute(items);
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item, Item t1) {
                return t1.getDate().compareTo(item.getDate());
            }
        });

        activity.setupUI(items);
    }

    static public interface makeUi{
        public void setupUI(ArrayList<Item> items);
    }
}
