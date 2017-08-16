package com.example.amruth.triviaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ReadJSON.readJSONFile {
    ArrayList<Questions> questions;
    Button start;
    Button exit;
    ImageView image;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        progressDialog= new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        start=(Button) findViewById(R.id.start);
        start.setEnabled(false);
        exit=(Button) findViewById(R.id.exit);
        image=(ImageView) findViewById(R.id.loadingimage);
        new ReadJSON(MainActivity.this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void readFile(String s) {
        Log.d("demo",s);
        try {
            JSONObject response = new JSONObject(s);
            JSONArray array= response.optJSONArray("questions");
            questions=new ArrayList<Questions>();
            for(int i=0;i<array.length();i++){
                JSONObject obj= array.optJSONObject(i);
                Questions q= new Questions();
                q.setId(obj.optString("id"));
                q.setText(obj.optString("text"));
                q.setImageUrl(obj.optString("image"));
                JSONArray ar=obj.optJSONObject("choices").optJSONArray("choice");
                String[] s1=new String[ar.length()];
                for(int j=0;j<s1.length;j++){
                    s1[j]=(String) ar.opt(j);
                }
                q.setChoices(s1);
                q.setAnswer(obj.optJSONObject("choices").optString("answer"));
                questions.add(q);


            }
            /*for(int k=0;k<questions.size();k++){
                Questions q1=new Questions();
                q1=questions.get(k);
                Log.d("finl",q1.getText());
                Log.d("finl",q1.getImageUrl());
                Log.d("finl",q1.getAnswer());
                String[] s2=q1.getChoices();
                for(int l=0;l<q1.getChoices().length;l++){
                    Log.d("finl",s2[l]);
                }
            }*/

            //JSONArray ar1=obj.optJSONObject("choices").optJSONArray("choice");
            //String s1=(String) ar1.opt(1);
            //Log.d("test",s1);
            /*for(int i=0;i<array.length();i++){

                JSONObject obj= array.optJSONObject(i);

                //q.setChoices(obj.optJSONArray("choice"));
                JSONArray ar=obj.optJSONObject("choices").optJSONArray("choice");
                ar.getJSONObject(1);
                questions.add(q);

            }*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, TriviaActivity.class);
                intent.putExtra("QuestionsArray",questions);
                startActivity(intent);

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        image.setImageDrawable(getDrawable(R.drawable.trivia));
        start.setEnabled(true);
        progressDialog.dismiss();



    }
}
