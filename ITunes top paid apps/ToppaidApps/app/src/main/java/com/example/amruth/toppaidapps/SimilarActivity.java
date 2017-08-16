package com.example.amruth.toppaidapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class SimilarActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Apps> applist;
    ArrayList<Apps> favlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.similar);
        listView= (ListView) findViewById(R.id.listviewSimilar);
        applist= (ArrayList<Apps>) getIntent().getSerializableExtra("applist");
        Log.d("stup",applist.toString());
        favlist= new ArrayList<Apps>();
        SharedPreferences sharedPreferences=getSharedPreferences("mydata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        for(int i=0;i<applist.size();i++){
            if(sharedPreferences.getString(applist.get(i).getAppName(),"")!=""){
                favlist.add(applist.get(i));
            }
        }
        Log.d("favlist",favlist.toString());
        SimilarAdapter adapter= new SimilarAdapter(this,R.layout.apps_row,favlist);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);



    }

}
