package com.example.amruth.toppaidapps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements ReadJSON.readJSONFile {
    ListView listView;
    SharedPreferences sharedPreferences;
    ArrayList<Apps> applist;
    AppsAdapter adapter;
    ProgressDialog progressdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        progressdialog  = new ProgressDialog(MainActivity.this);
        progressdialog.setMessage("Loading");
        progressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressdialog.setCancelable(false);
        progressdialog.show();

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences= getSharedPreferences("mydata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear().commit();

        new ReadJSON(MainActivity.this).execute("https://itunes.apple.com/us/rss/toppaidapplications/limit=25/json");
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==(R.id.fav)){
            Intent intent= new Intent(MainActivity.this,SimilarActivity.class);
            intent.putExtra("applist",applist);
            startActivity(intent);

        }else if(item.getItemId()==R.id.refresh){
            adapter.notifyDataSetChanged();
        }else if(item.getItemId()==R.id.inc){
            Collections.sort(applist, new Comparator<Apps>() {
                @Override
                public int compare(Apps apps, Apps t1) {
                    if (apps.getPrice() > t1.getPrice()){
                        return 1;
                    }
                    else if (apps.getPrice() < t1.getPrice()) {
                        return -1;
                    }
                    else{
                        return 0;
                    }
                }
            });
            adapter.notifyDataSetChanged();


        }else if(item.getItemId()==R.id.dec){
            Collections.sort(applist, new Comparator<Apps>() {
                @Override
                public int compare(Apps apps, Apps t1) {
                    if (apps.getPrice() > t1.getPrice()){
                        return -1;
                    }
                    else if (apps.getPrice() < t1.getPrice()) {
                        return 1;
                    }
                    else{
                        return 0;
                    }
                }
            });
            adapter.notifyDataSetChanged();




        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void readFile(ArrayList<Apps> appses) {
        applist=appses;
        Log.d("making","dfsdfdsfsdfds"+appses.toString());
        listView=(ListView) findViewById(R.id.listviewmain);
        adapter= new AppsAdapter(this,R.layout.apps_row,appses);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        progressdialog.dismiss();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear().commit();
    }
}
