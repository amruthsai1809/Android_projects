package com.example.amruth.tedradio;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener, MediaController.MediaPlayerControl {

    Item displayItem;
    MediaPlayer mediaPlayer;
    MediaController mediaController;
    int hideFlag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        int index = getIntent().getExtras().getInt("POSITION");
        displayItem = MainActivity.itemslist.get(index);

        TextView title, description, date, duration;
        ImageView imageView;
        title = (TextView) findViewById(R.id.textView);
        description = (TextView) findViewById(R.id.textView2);
        date = (TextView) findViewById(R.id.textView3);
        duration = (TextView) findViewById(R.id.textView4);
        imageView = (ImageView) findViewById(R.id.imageView);

        title.setText(displayItem.getTitle());
        description.setText("Description: "+displayItem.getDescription());
//        android.text.format.DateFormat df = new android.text.format.DateFormat();
//        String dateS = df.format("MM/dd/yyyy", displayItem.getDate());
//        Date datess = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//        try {
//            datess = format.parse(displayItem.getDate().toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        SimpleDateFormat fmt = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        String dateString = displayItem.getDate().toString();
        Date datee = new Date();
        try {
            datee = fmt.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");
//        return fmtOut.format(datee);


        date.setText("Publication Date:" + displayItem.getDate()+"");
        duration.setText("Duration:" + displayItem.getDuration()+"");
        Picasso.with(this).load(displayItem.getImageurl()).into(imageView);


        if (mediaPlayer != null){
            mediaPlayer.stop();
            Log.d("demo", "Called yeahh");
            hideFlag = 0;
        }


        mediaPlayer = new MediaPlayer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mediaController = new MediaController(PlayActivity.this, false){
                @Override
                public void hide() {
                    Log.d("demo", "yeahh");
                    if (hideFlag == 1) {
                        Log.d("demo", "Hiding");
                        super.hide();
                    }
                    else{
                        Log.d("demo", "Not Hiding");
                        mediaController.show();
                        hideFlag = 1;
                    }
                }
            };
            Log.d("demo", "Yes, it is lollipop");
        }
        mediaPlayer.setOnPreparedListener(PlayActivity.this);

        String url = displayItem.getMp3url();

        try {
            mediaPlayer.setDataSource(PlayActivity.this, Uri.parse(url));
            mediaPlayer.prepare();
            mediaPlayer.start();


        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaController.show();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaController.setMediaPlayer(PlayActivity.this);
        mediaController.setAnchorView(findViewById(R.id.activity_play));
        mediaController.setEnabled(true);
//        mediaController.show();
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
//        if (mediaPlayer != null){
            Log.d("demo", "Pressed Back");
            hideFlag = 1;
        mediaPlayer.stop();
        //mediaController.hide();
//        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        mediaController.show();
        super.onResume();
    }
}
