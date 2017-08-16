package com.example.amruth.triviaapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Intent i = getIntent();

        ArrayList<Questions> wrongAns = (ArrayList<Questions>) i.getExtras().getSerializable("wrong");
        ArrayList<Questions> correctAns = (ArrayList<Questions>) i.getExtras().getSerializable("correct");
        int[] answers = i.getIntArrayExtra("myans");

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear);
        int ind = 0;
        for (Questions q : wrongAns){
            TextView ques, myAns, Ans;
            ques = new TextView(this);
            myAns = new TextView(this);
            myAns.setBackgroundColor(0xFF0000);
            Ans = new TextView(this);
            ques.setText(q.getText());
            Ans.setText(q.getChoices()[q.getAnswer() - 1]);
            if (answers[Integer.parseInt(q.getId())] == -1)
                myAns.setText("");
            else
                myAns.setText(q.getChoices()[answers[Integer.parseInt(q.getId())]]);
            linearLayout.addView(ques);
            myAns.setBackgroundColor(Color.parseColor("#ff0000"));
            linearLayout.addView(myAns);
            linearLayout.addView(Ans);



            //for a line
            ImageView l = new ImageView(this);
            int res = getResources().getIdentifier("line", "drawable", this.getPackageName());
            l.setImageResource(res);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
            l.setLayoutParams(layoutParams);
            linearLayout.addView(l);
        }




        TextView percentage = (TextView) findViewById(R.id.textView2);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        double quo = correctAns.size();
        double percent = (quo/16)*100;
        final int p = (int) percent;
        percentage.setText(p + "%");
        seekBar.setProgress(p);
        //seekBar.isClickable();
        //seekBar.setClickable(false);
        //seekBar.setEnabled(false);
        seekBar.setClickable(false);
        seekBar.setFocusableInTouchMode(false);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBar.setProgress(p);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //Toast.makeText(this, "Correct:"+correctAns.size()+" wrong:"+wrongAns.size(),Toast.LENGTH_LONG).show();






        //Toast.makeText(this, wrongAns.get(0).getAnswer() + " " + answers[0], Toast.LENGTH_SHORT).show();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(Stats.this, MainActivity.class);
                startActivity(I);
            }
        });
    }
}
