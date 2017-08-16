package com.example.amruth.triviaapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity implements GetImage.setImage {
    int count=0,end;
    ArrayList<Questions> q;
    TextView qnumber,question,timerTextView;
    Button next,prev;
    ImageView im;
    RadioGroup rg;
    int[] index;
   // Questions ques;

    long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = 120 - (int) (millis / 1000);
            //int minutes = seconds / 60;
            //seconds = seconds % 60;

            if (seconds != 0)
                timerTextView.setText("Time Left: "+seconds + "  seconds");
            else{
                Toast.makeText(TriviaActivity.this, "Times upp!!", Toast.LENGTH_SHORT).show();
                timerHandler.removeCallbacks(timerRunnable);
                Intent i= new Intent(TriviaActivity.this,Stats.class);

                int checked= rg.getCheckedRadioButtonId();
                View radioButton= rg.findViewById(checked);
                int indexOfChecked= rg.indexOfChild(radioButton);
                index[count]=indexOfChecked;
                ArrayList<Questions> wrongAns;
                ArrayList<Questions> correctAns;
                correctAns = new ArrayList<Questions>();

                wrongAns = new ArrayList<Questions>();
                for (int k = 0; k < q.size(); k++ ){
                    Questions qq = q.get(k);
                    if (qq.getAnswer() != index[k]+1){
                        wrongAns.add(qq);
                    }
                    else
                        correctAns.add(qq);
                }
                i.putExtra("wrong", wrongAns);
                i.putExtra("correct", correctAns);
                i.putExtra("myans", index);



                startActivity(i);

            }

            timerHandler.postDelayed(this, 500);
        }
    };


    @Override
    protected void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        qnumber= (TextView) findViewById(R.id.questionNumber);
        question=(TextView) findViewById(R.id.question);
        next= (Button) findViewById(R.id.next);
        prev=(Button) findViewById(R.id.prev);
        im=(ImageView) findViewById(R.id.imageView);
        rg= (RadioGroup) findViewById(R.id.radioGroup);

        prev.setEnabled(false);




        timerTextView = (TextView) findViewById(R.id.timer);
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);





        q=new ArrayList<Questions>();
        q=(ArrayList<Questions>) getIntent().getExtras().getSerializable("QuestionsArray");
        end=q.size();
        index= new int[q.size()];
        for(int temp=0;temp<index.length;temp++){
            index[temp]=-1;
        }
        new GetImage(TriviaActivity.this).execute(q.get(count).getImageUrl());
        qnumber.setText("Q"+(count+1));
        question.setText(q.get(count).getText());
        RadioButton[] radios;
        String[] questionChoices=q.get(count).getChoices();
        radios=new RadioButton[questionChoices.length];
        for(int i=0;i<questionChoices.length;i++){
            radios[i]= new RadioButton(TriviaActivity.this);
            radios[i].setText(questionChoices[i]);
            rg.addView(radios[i]);
        }

        next.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                im.setImageDrawable(getDrawable(R.drawable.loadingimage));
                prev.setEnabled(true);
                if(count<q.size()-1){
                    count++;
                }else{
                    //Toast.makeText(TriviaActivity.this, "Start new activity", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(TriviaActivity.this, "Times upp!!", Toast.LENGTH_SHORT).show();
                    timerHandler.removeCallbacks(timerRunnable);
                    Intent i= new Intent(TriviaActivity.this,Stats.class);
                    ArrayList<Questions> wrongAns;
                    ArrayList<Questions> correctAns;
                    int checked= rg.getCheckedRadioButtonId();
                    View radioButton= rg.findViewById(checked);
                    int indexOfChecked= rg.indexOfChild(radioButton);
                    index[count]=indexOfChecked;

                    correctAns = new ArrayList<Questions>();

                    wrongAns = new ArrayList<Questions>();
                    for (int k = 0; k < q.size(); k++ ){
                        Questions qq = q.get(k);
                        if (qq.getAnswer() != index[k]+1){
                            wrongAns.add(qq);
                        }
                        else
                            correctAns.add(qq);
                    }
                    i.putExtra("wrong", wrongAns);
                    i.putExtra("correct", correctAns);
                    i.putExtra("myans", index);



                    startActivity(i);


                }
                int checked= rg.getCheckedRadioButtonId();
                View radioButton= rg.findViewById(checked);
                int indexOfChecked= rg.indexOfChild(radioButton);
                index[count-1]=indexOfChecked;
                //Toast.makeText(TriviaActivity.this, index[count-1]+"",Toast.LENGTH_SHORT).show();
                Picasso.with(TriviaActivity.this).load(q.get(count).getImageUrl()).into(im);
                //new GetImage(TriviaActivity.this).execute(q.get(count).getImageUrl());
                //new GetImage(TriviaActivity.this).execute(q.get(count).getImageUrl());
                //Toast.makeText(TriviaActivity.this,q.get(count).getImageUrl()+"",Toast.LENGTH_SHORT).show();
                rg.removeAllViews();
                qnumber.setText("Q"+(count+1));
                question.setText(q.get(count).getText());
                RadioButton[] radios;
                String[] questionChoices=q.get(count).getChoices();
                radios=new RadioButton[questionChoices.length];
                for(int i=0;i<questionChoices.length;i++){
                    radios[i]= new RadioButton(TriviaActivity.this);
                    radios[i].setText(questionChoices[i]);
                    rg.addView(radios[i]);
                }
                if(index[count]>-1){
                    RadioButton some= (RadioButton) rg.getChildAt(index[count]);
                    some.setChecked(true);
                }
                if(count==q.size()-1){
                    next.setText("Finish");
                }


            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>0){
                    count--;
                }
                int checked= rg.getCheckedRadioButtonId();
                View radioButton= rg.findViewById(checked);
                int indexOfChecked= rg.indexOfChild(radioButton);
                index[count+1]=indexOfChecked;

                //Toast.makeText(TriviaActivity.this, index[count+1]+"",Toast.LENGTH_SHORT).show();
                //new GetImage(TriviaActivity.this).execute(q.get(count).getImageUrl());
                Picasso.with(TriviaActivity.this).load(q.get(count).getImageUrl()).into(im);
                //Toast.makeText(TriviaActivity.this,q.get(count).getImageUrl()+"",Toast.LENGTH_SHORT).show();
                rg.removeAllViews();
                qnumber.setText("Q"+(count+1));
                question.setText(q.get(count).getText());
                RadioButton[] radios;
                String[] questionChoices=q.get(count).getChoices();
                radios=new RadioButton[questionChoices.length];
                for(int i=0;i<questionChoices.length;i++){
                    radios[i]= new RadioButton(TriviaActivity.this);
                    radios[i].setText(questionChoices[i]);
                    rg.addView(radios[i]);
                }
                if(index[count]>-1){
                    RadioButton some= (RadioButton) rg.getChildAt(index[count]);
                    some.setChecked(true);
                }

            }
        });


            /*for(int k=0;k<q.size();k++){
                Questions q1=new Questions();
                q1=q.get(k);
                Log.d("some",q1.getText());
                Log.d("some",q1.getImageUrl());
                Log.d("some",q1.getAnswer());
                String[] s2=q1.getChoices();
                for(int l=0;l<q1.getChoices().length;l++){
                    Log.d("some",s2[l]);
                }
            }*/
    }

    @Override
    public void setImageView(Bitmap bitmap) {
        /*rg.removeAllViews();
                qnumber.setText("Q"+(count+1));
                question.setText(q.get(count).getText());
                RadioButton[] radios;
                String[] questionChoices=q.get(count).getChoices();
                radios=new RadioButton[questionChoices.length];
                for(int i=0;i<questionChoices.length;i++){
                    radios[i]= new RadioButton(TriviaActivity.this);
                    radios[i].setText(questionChoices[i]);
                    rg.addView(radios[i]);
                }
        */

        im.setImageBitmap(bitmap);
    }
}
