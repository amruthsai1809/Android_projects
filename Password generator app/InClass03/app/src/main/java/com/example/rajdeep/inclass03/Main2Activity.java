package com.example.rajdeep.inclass03;
/*
* Assignment: In class 03
* Main2Activity.Java
* Rajdeep Rao
* Amruth Sai Gandavarapu */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ScrollView scrollView=(ScrollView)findViewById(R.id.thread);
        LinearLayout threadlayout= (LinearLayout) findViewById(R.id.threadLayout);
        LinearLayout asyncLayout= (LinearLayout) findViewById(R.id.asyncLayout);
        String[] threadArray= getIntent().getExtras().getStringArray("Thread");
        String[] asyncArray=getIntent().getExtras().getStringArray("Async");
        TextView[] tv1 = new TextView[threadArray.length];
        TextView[] tv2 = new TextView[asyncArray.length];

        for(int i=0;i<threadArray.length;i++){
            tv1[i]=new TextView(this);
            tv1[i].setText(threadArray[i]);
            tv1[i].setPadding(5,5,5,5);
            threadlayout.addView(tv1[i]);
        }

        for(int i=0;i<asyncArray.length;i++){
            tv2[i]=new TextView(this);
            tv2[i].setText(asyncArray[i]);
            tv2[i].setPadding(5,5,5,5);
            asyncLayout.addView(tv2[i]);
        }
        findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
