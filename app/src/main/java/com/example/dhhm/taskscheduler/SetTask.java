package com.example.dhhm.taskscheduler;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SetTask extends AppCompatActivity {

    //EditText d,m,y,h,mi,format,
    EditText task;
    TimePicker timePicker;
    DatePicker datePicker;
    Button submit;

    Database myDb;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_task);
        /*d= (EditText) findViewById(R.id.dd);
        m= (EditText) findViewById(R.id.mm);
        y= (EditText) findViewById(R.id.yyyy);
        h= (EditText) findViewById(R.id.hh);
        mi= (EditText) findViewById(R.id.min);
        format = (EditText) findViewById(R.id.format);*/
        task= (EditText) findViewById(R.id.task);
        timePicker= (TimePicker)findViewById(R.id.timePicker);
        datePicker=(DatePicker)findViewById(R.id.datePicker);
        submit = (Button) findViewById(R.id.submit);
        myDb = new Database(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                /*String day=d.getText().toString();
                String month=m.getText().toString();
                String year=y.getText().toString();
                String hour=h.getText().toString();
                String minute=mi.getText().toString();
                String format1=format.getText().toString();*/
                int day=datePicker.getDayOfMonth();
                int month=datePicker.getMonth();
                int year=datePicker.getYear();
                int hour=timePicker.getCurrentHour();
                int minute=timePicker.getCurrentMinute();
                String Task=task.getText().toString();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year,month,day,hour,minute,0);


                setAlarm(calendar.getTimeInMillis()-System.currentTimeMillis(),Task);

                boolean msg = myDb.adddata(day,month,year,hour,minute,Task);
                //if(msg==true){
                  //  Toast.makeText(SetTask.this, "DB ok", Toast.LENGTH_SHORT).show();
                //}
                //addNotification();

            }

        });



    }


    private void setAlarm(long timeInMillis,String Task) {


        Intent intent = new Intent(this, MyAlarm.class);
        intent.putExtra("task", Task);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setRepeating(AlarmManager.RTC,System.currentTimeMillis()+timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(SetTask.this, "Alarm set", Toast.LENGTH_SHORT).show();



    }

}
