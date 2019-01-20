package com.example.colliensepodder.parentscare.MoreNavigation.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import com.example.colliensepodder.parentscare.AddMyAppointsmentsActivity;
import com.example.colliensepodder.parentscare.R;

public class AlarmReceiverd extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //int notificationId= intent.getIntExtra("notificationId",0);
        //String message = intent.getStringExtra("todo");

        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        Notification notification = new Notification.Builder(context)
                .setContentTitle("Its Time")
                .setContentText("Appointment")
                .setSmallIcon(R.mipmap.ic_launcher_pcare).build();

        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0,notification);

        Uri notific = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone R = RingtoneManager.getRingtone(context,notific);
        R.play();



    }
}
