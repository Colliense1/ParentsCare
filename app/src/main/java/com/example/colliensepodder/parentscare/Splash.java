package com.example.colliensepodder.parentscare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    private TextView tc;
    private ImageView imageViewNavItemIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tc = (TextView)findViewById(R.id.tc);
        imageViewNavItemIcon = findViewById(R.id.imageViewNavItemIcon);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Selectmode.class));
            }
        },4000);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tc.startAnimation(myanim);
        imageViewNavItemIcon.startAnimation(myanim);

    }
}
