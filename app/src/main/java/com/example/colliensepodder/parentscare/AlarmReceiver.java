package com.example.colliensepodder.parentscare;

import android.Manifest;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;


public class AlarmReceiver extends BroadcastReceiver {

    Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Intent x = new Intent(context, Alert.class);
        //  x.putExtra(context.getString(R.string.titttle), Title);
        // x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //  context.startActivity(x);
        // intent = new Intent(context, MyService.class);
        //  context.startService(intent);
        this.mContext = context;
        String Title = "title";
        Uri alarmTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final Ringtone ringtoneAlarm = RingtoneManager.getRingtone(context, alarmTone);
        ringtoneAlarm.play();
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        String msg = "Time to take a medicine";
        builder.setMessage(msg).setCancelable(
                false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        ringtoneAlarm.stop();
                        sentMsgToParent();


                        // ringtoneAlarm.stop();
                        //    onDestroy();
                    }
                });
        android.app.AlertDialog alert = builder.create();
        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alert.show();
    }

    private void showNotification(Context context) {
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setContentTitle("Notification!")
                .setContentText("Alarm Received")
                .setSound(soundUri)
                .setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }

    public void sentMsgToParent(){
        MedicineManagementDatabase obj = new MedicineManagementDatabase(mContext);
        ArrayList<Contact> contacts = obj.retriveAllEmergencyContact();
        if(contacts.size()!=0) {
            if(checkPermission()) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(contacts.get(0).getContactNumber(), null, "This is Test Text", null, null);
            }
        }else {
            return;
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(mContext, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

}
