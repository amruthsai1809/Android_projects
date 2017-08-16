package com.example.amruth.homework3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ArrayList<String> list=new ArrayList<String>();
    TextView[] tv=new TextView[20];
    LinearLayout scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        list=getIntent().getStringArrayListExtra("array");
        scroll=new LinearLayout(this);
        scroll=(LinearLayout) findViewById(R.id.resultlayout);
        for(int i=0;i<list.size();i++){
            tv[i]=new TextView(this);
            tv[i].setText(list.get(i));
            scroll.addView(tv[i]);
            //i++;
            //Log.d("demo",s+"demo");

        }
        findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                Intent i = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
