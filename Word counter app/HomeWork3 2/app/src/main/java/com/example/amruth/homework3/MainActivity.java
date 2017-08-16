package com.example.amruth.homework3;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> result= new ArrayList<String>();
    LinearLayout mainLayout;
    ArrayList<LinearLayout> subLayout= new ArrayList<LinearLayout>();
    //LinearLayout[] subLayout= new LinearLayout[20];
    ArrayList<EditText> editText= new ArrayList<EditText>();
    //EditText[] editText = new EditText[20];
    ArrayList<FloatingActionButton> fab=new ArrayList<FloatingActionButton>();
    //FloatingActionButton[] fab= new FloatingActionButton[20];
    int count=0,end=-1;
    ProgressDialog progressDialog;
    CheckBox check;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mainLayout= new LinearLayout(this);
        check=(CheckBox) findViewById(R.id.check);
        mainLayout=(LinearLayout) findViewById(R.id.layout_main);
        subLayout.add(new LinearLayout(this));
        subLayout.get(count).setOrientation(LinearLayout.HORIZONTAL);
        editText.add(new EditText(this));
        editText.get(count).setLayoutParams(new ViewGroup.LayoutParams(500, ViewGroup.LayoutParams.WRAP_CONTENT));
        fab.add(new FloatingActionButton(this));
        fab.get(count).setId(View.generateViewId());
        fab.get(count).setImageDrawable(getDrawable(R.drawable.plus));
        //fab.get(count).setBackground(Drawable.createFromPath("@drawable/plus.PNG"));
        subLayout.get(count).addView(editText.get(count));
        subLayout.get(count).addView(fab.get(count));
        fab.get(count).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {
                if(view.getId()==fab.get(count).getId()){
                    if(count<=18){
                        if(editText.get(count).getText().toString().trim().isEmpty()||editText.get(count).getText().toString().trim().length()==0||editText.get(count).getText().toString().trim()==null||editText.get(count).getText().toString().equals("")){
                            Toast.makeText(MainActivity.this,"Please enter text",Toast.LENGTH_SHORT).show();
                        }
                        else{

                            addRow();
                        }

                    }else{
                        Toast.makeText(MainActivity.this,"Not more than 20 entries",Toast.LENGTH_SHORT).show();
                    }



                }else{
                    deleteRow(view);

                }

            }
        });
        mainLayout.addView(subLayout.get(count));
        Button search= (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.get(count).getText().toString().trim().isEmpty()||editText.get(count).getText().toString().trim().length()==0||editText.get(count).getText().toString().trim()==null||editText.get(count).getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please enter Key Word to search", Toast.LENGTH_SHORT).show();
                }
                else{
                    String str="";
                    progressDialog = new ProgressDialog(MainActivity.this);
                    final ArrayList<String> result= new ArrayList<String>();
                    progressDialog.setMessage("Counting...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    if(check.isChecked()){
                        str="1";
                    }else {
                        str="2";
                    }
                    for(int temp=0; temp<=count;temp++){
                        new DoWork().execute(editText.get(temp).getText().toString(),str);
                        //String tct;
                        //tct= editText.get(temp).getText().toString();
                        //Log.d("demo",tct);
                    }
                    //new DoWork().execute("Book");
                    //Log.d("demo",text);
                }

            }
        });

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void addRow(){


            fab.get(count).setImageDrawable(getDrawable(R.drawable.minus));
            //fab.get(count).setImageDrawable(Drawable.createFromPath("@drawable/minus.PNG"));
            //fab.get(count).setBackground(Drawable.createFromPath("@drawable/minus.PNG"));
            count++;
            subLayout.add(new LinearLayout(this));
            subLayout.get(count).setOrientation(LinearLayout.HORIZONTAL);
            editText.add(new EditText(this));
            editText.get(count).setLayoutParams(new ViewGroup.LayoutParams(500, ViewGroup.LayoutParams.WRAP_CONTENT));
            fab.add(new FloatingActionButton(this));
            fab.get(count).setId(View.generateViewId());
            fab.get(count).setImageDrawable(getDrawable(R.drawable.plus));
            subLayout.get(count).addView(editText.get(count));
            subLayout.get(count).addView(fab.get(count));
            mainLayout.addView(subLayout.get(count));
            fab.get(count).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(view.getId()==fab.get(count).getId()){
                        if(count<=18){
                            if(editText.get(count).getText().toString().trim().isEmpty()||editText.get(count).getText().toString().trim().length()==0||editText.get(count).getText().toString().trim()==null||editText.get(count).getText().toString().equals("")){
                                Toast.makeText(MainActivity.this,"Please enter text",Toast.LENGTH_SHORT).show();
                            }
                            else{

                                addRow();
                            }

                        }else{
                            Toast.makeText(MainActivity.this,"Not more than 20 entries",Toast.LENGTH_SHORT).show();
                        }



                    }else{
                        deleteRow(view);

                    }
                }
            });
    }
    public void deleteRow(View v){
        int i;
        for(i=0;i<count;i++){
            if(v.getId()==fab.get(i).getId())
                break;
        }
        count--;
        mainLayout.removeView(subLayout.get(i));
        subLayout.remove(i);
        editText.remove(i);
        fab.remove(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        result.clear();
    }

    public class DoWork extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            int lastIndex=0,finds=0;
            InputStream is;
            int size;
            byte[] buffer;
            String text,tem;
            try {
                is=getAssets().open("textfile.txt");
                size=is.available();
                buffer=new byte[size];
                is.read(buffer);
                is.close();
                text=new String(buffer);
                if(strings[1]=="2"){
                    text=text.toLowerCase();
                    strings[0]=strings[0].toLowerCase();
                }
                while(lastIndex!=-1){
                    lastIndex=text.indexOf(strings[0],lastIndex);
                    if(lastIndex!=-1){
                        finds++;
                        lastIndex+=strings[0].length();

                    }
                }



            } catch (IOException e) {
                e.printStackTrace();
            }
            tem=strings[0]+":"+finds;
           // Log.d("demo",tem);

            //Log.d("demo","text");
            result.add(tem);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            end++;
            if(end==count){
                Log.d("demo",count+"");
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("array",result);
                for(String r:result){
                    Log.d("demo",r)
;                }
                progressDialog.dismiss();
                startActivity(intent);


            }

        }
    }

}
