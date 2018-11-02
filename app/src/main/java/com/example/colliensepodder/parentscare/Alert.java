package com.example.colliensepodder.parentscare;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.colliensepodder.parentscare.R;


public class Alert extends Activity {
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

    }

    @Override

    public void onDestroy() {

        super.onDestroy();

        mp.release();

    }

}
