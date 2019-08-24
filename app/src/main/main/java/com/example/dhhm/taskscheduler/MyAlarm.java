package com.example.dhhm.taskscheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by Ibrahim Khalil on 11/8/2017.
 */

public class MyAlarm extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm Triggered!!!!", Toast.LENGTH_SHORT).show();
        Vibrator v =(Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        v.vibrate(2000);
        MediaPlayer mediaplayer = MediaPlayer.create(context, Settings.System.DEFAULT_NOTIFICATION_URI);
        mediaplayer.start();
    }
}
