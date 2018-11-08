package com.example.colliensepodder.parentscare;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;


public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        YourTask(intent, flags);

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //    Intent iuu = new Intent(this, MainActivity.class);
        //   startActivity(iuu);

        Intent broadcastIntent = new Intent("ac.in.ActivityRecognition.RestartSensor");
        sendBroadcast(broadcastIntent);


    }

    private void YourTask(final Intent intent, final int ff) {
        //showNotification();


    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
