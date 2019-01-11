package com.example.tharindu_prasad.parkinghandler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv,tv1;
    private ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView) findViewById(R.id.tv);
        tv1=(TextView) findViewById(R.id.tv1);
        iv=(ImageView)findViewById(R.id.iv);
        Animation anim1= AnimationUtils.loadAnimation(this,R.anim.anim1);
        tv.startAnimation(anim1);
        tv1.startAnimation(anim1);
        iv.startAnimation(anim1 );
        final Intent i = new Intent(this,Main2Activity.class);
        Thread timer=new Thread(){
            public void run(){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
