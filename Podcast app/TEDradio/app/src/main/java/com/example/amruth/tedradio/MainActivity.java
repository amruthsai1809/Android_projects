package com.example.amruth.tedradio;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.MediaController;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ParsingAsync.makeUi,LinearAdapter.ItemClickCallback,GridAdapter.ItemClickCallback, MediaPlayer.OnPreparedListener, MediaController.MediaPlayerControl {
    Toolbar toolbar;
    static ArrayList<Item> itemslist;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    LinearAdapter adapter;
    GridAdapter gridAdapter;
    int flag=1;
    ProgressDialog progressDialog;
    MediaPlayer mediaPlayer;
    MediaController mediaController;
    int hideFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        recyclerView= (RecyclerView) findViewById(R.id.rec);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        new ParsingAsync(MainActivity.this).execute("https://www.npr.org/rss/podcast.php?id=510298");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(flag==0){
            flag=1;
            recyclerView.setLayoutManager(layoutManager);
            adapter=new LinearAdapter(itemslist,this);
            recyclerView.setAdapter(adapter);

        }else if(flag==1){
            flag=0;
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
            gridAdapter=new GridAdapter(itemslist,this);
            recyclerView.setAdapter(gridAdapter);

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void setupUI(ArrayList<Item> items) {
        Log.d("demo",items.get(0).toString());
        itemslist=items;
        recyclerView.setLayoutManager(layoutManager);
        adapter=new LinearAdapter(itemslist,this);
        recyclerView.setAdapter(adapter);
        progressDialog.dismiss();


    }

    @Override
    public void onItemClick(int p) {
        Log.d("demo","clicked container "+p);
        openPodcast(p);
    }

    @Override
    public void onSeconddaryclick(int p) {
        Log.d("demo","clicked button "+p);
        playPodcast(p);
    }

    @Override
    public void onItemClickgrid(int p) {
        Log.d("demo","clicked grid container "+p);
        openPodcast(p);
    }

    @Override
    public void onSeconddaryclickgrid(int p) {
        Log.d("demo","clicked grid button "+p);
        playPodcast(p);
    }


    public void openPodcast(int pos){
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaController.hide();
        }
        Intent i = new Intent(MainActivity.this, PlayActivity.class);
        i.putExtra("POSITION", pos);
        startActivity(i);
    }

    public void playPodcast(int pos){

        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaController.hide();
            Log.d("demo", "Called yeahh");
            hideFlag = 0;
        }


        mediaPlayer = new MediaPlayer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mediaController = new MediaController(MainActivity.this, false){
                @Override
                public void hide() {
                    Log.d("demo", "yeahh");
                    if (hideFlag == 1)
                        super.hide();
                    else
                        hideFlag = 1;
                }
            };
            Log.d("demo", "Yes, it is lollipop");
        }
        mediaPlayer.setOnPreparedListener(MainActivity.this);

        String url = itemslist.get(pos).getMp3url();

        try {
            mediaPlayer.setDataSource(MainActivity.this, Uri.parse(url));
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaController.setMediaPlayer(MainActivity.this);
        mediaController.setAnchorView(findViewById(R.id.main));
        mediaController.setEnabled(true);
        mediaController.show();

    }

    @Override
    public void start() {
        mediaPlayer.start();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int i) {
        mediaPlayer.seekTo(i);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }


    @Override
    public void onBackPressed() {
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaController.hide();
        }
        super.onBackPressed();
    }
}

