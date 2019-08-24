package com.example.dhhm.taskscheduler;

import android.app.*;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;
import android.app.Notification;
import android.app.NotificationManager;
/**
 * Created by Ibrahim Khalil on 11/8/2017.
 */

public class MyAlarm extends BroadcastReceiver {

    @Override

    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.abc)
                        .setContentTitle("You have A task undone")
                        .setContentText( intent.getStringExtra("task"));
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Toast.makeText(context, "Undone Task!!!", Toast.LENGTH_LONG).show();
        //Toast.makeText(context, intent.getStringExtra("task"), Toast.LENGTH_LONG).show();
        Vibrator v = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        v.vibrate(1000);
        MediaPlayer mediaplayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaplayer.start();
        manager.notify(0, builder.build());
    }



}