package com.example.rajdeep.inclass03;
/*
* Assignment: In class 03
* MainActivity.Java
* Rajdeep Rao
* Amruth Sai Gandavarapu */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    int threadCountValue,threadLengthValue, asyncCountValue,asyncLengthValue;
    int progressValue=0;
    Handler handler;
    ExecutorService threadpool;
    ProgressDialog progressDialog;

    String[] array,arrayAsync;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        threadCountValue=1;
        threadLengthValue=7;
        asyncCountValue=1;
        asyncLengthValue=7;
        threadpool= Executors.newFixedThreadPool(2);
        handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case threadComputation.MSG_START:
                        progressDialog.show();
                        break;
                    case threadComputation.MSG_STEP:
                        int p = progressDialog.getProgress();
                        progressDialog.setProgress(p+1);
                        Log.d("Handler", "Entered STEP");
                        break;
                    case threadComputation.MSG_END:
                        if(progressDialog.getProgress()==threadCountValue+asyncCountValue){
                            progressDialog.dismiss();
                            Intent intent=new Intent(MainActivity.this, Main2Activity.class);
                            intent.putExtra("Thread",array);
                            intent.putExtra("Async",arrayAsync);
                            startActivity(intent);

                        }

                        Log.d("Handler", "Entered END");

                        break;

                }

                return false;
            }
        });

        SeekBar threadCount=(SeekBar) findViewById(R.id.threadCount);
        final TextView threadCountVal=(TextView)findViewById(R.id.threadCountVal);
        threadCountVal.setText("1");
        final TextView threadLengthVal=(TextView)findViewById(R.id.threadLengthVal);
        threadLengthVal.setText("7");
        SeekBar threadLength=(SeekBar) findViewById(R.id.threadLength);

        SeekBar asyncCount=(SeekBar) findViewById(R.id.asyncCount);
        SeekBar asyncLength=(SeekBar)findViewById(R.id.asyncPasswordLength);
        final TextView asyncCountVal=(TextView)findViewById(R.id.asyncCountVal);
        asyncCountVal.setText("1");
        final TextView asyncLengthVal=(TextView)findViewById(R.id.asyncLengthVal);
        asyncLengthVal.setText("7");


        threadCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                threadCountVal.setText(progress+ 1+"");
                threadCountValue=progress+1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        threadLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                threadLengthVal.setText(progress+7+ "");
                threadLengthValue=progress+7;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        asyncCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                asyncCountVal.setText(progress+1+ "");
                asyncCountValue=progress+1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        asyncLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                asyncLengthVal.setText(progress+7+ "");
                asyncLengthValue=progress+7;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog=new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Computing progress");
                progressDialog.setMax(threadCountValue+asyncCountValue);
                progressDialog.setCancelable(false);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                array=new String[threadCountValue];
                arrayAsync=new String[asyncCountValue];
                new asyncComputation().execute();
                threadpool.execute(new threadComputation());

                //startActivity(intent);
            }
        });

    }
    class threadComputation implements Runnable{
        static final int MSG_START=0x00;
        static final int MSG_STEP=0x01;
        static final int MSG_END=0x02;

        @Override
        public void run() {
            Message msg=new Message();
            msg.what=MSG_START;
            handler.sendMessage(msg);

            for(int i=0; i<threadCountValue;i++){
                msg=new Message();
                msg.what=MSG_STEP;
                Log.d("password length",String.valueOf(threadLengthValue));
                Log.d("password ",Util.getPassword(threadLengthValue));
                array[i]=Util.getPassword(threadLengthValue);
                msg.obj=i;
                handler.sendMessage(msg);
            }

            msg=new Message();
            msg.what=MSG_END;
            handler.sendMessage(msg);


        }
    }

    class asyncComputation extends AsyncTask<Void, Integer, Void>{

        @Override
        protected void onPreExecute() {
            Log.d("PreExec","PreExec");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(progressDialog.getProgress()==threadCountValue+asyncCountValue){
                progressDialog.dismiss();
                Intent intent=new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("Thread",array);
                intent.putExtra("Async",arrayAsync);
                startActivity(intent);
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int p=progressDialog.getProgress()+1;
            progressDialog.setProgress(p);
        }

        @Override
        protected Void doInBackground(Void... params) {
            for(int i=0;i<asyncCountValue;i++){
                arrayAsync[i]=Util.getPassword(asyncLengthValue);
                Log.d("Async",arrayAsync[i]);
                int val=progressDialog.getProgress();
                publishProgress(val++);
            }
                return null;
        }
    }
}
