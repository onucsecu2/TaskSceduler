package com.example.dhhm.taskscheduler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class SetTask extends AppCompatActivity {

    EditText d,m,y,h,mi,format,task;
    Button submit;

    Database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_task);
        d= (EditText) findViewById(R.id.dd);
        m= (EditText) findViewById(R.id.mm);
        y= (EditText) findViewById(R.id.yyyy);
        h= (EditText) findViewById(R.id.hh);
        mi= (EditText) findViewById(R.id.min);
        format = (EditText) findViewById(R.id.format);
        task= (EditText) findViewById(R.id.task);

        submit = (Button) findViewById(R.id.submit);
        myDb = new Database(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day=d.getText().toString();
                String month=m.getText().toString();
                String year=y.getText().toString();
                String hour=h.getText().toString();
                String minute=mi.getText().toString();
                String format1=format.getText().toString();
                String Task=task.getText().toString();

                Calendar calendar = Calendar.getInstance();
                calendar.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day),Integer.parseInt(hour),Integer.parseInt(minute),0);
                //setAlarm();
               // long time =;
                setAlarm(calendar.getTimeInMillis()-System.currentTimeMillis());


                /*int time = Integer.parseInt(d.getText().toString());
                Intent intent=new Intent(SetTask.this, MyAlarm.class);
                PendingIntent p1=PendingIntent.getBroadcast(getApplicationContext(),0, intent,0);
                AlarmManager am=(AlarmManager)getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC,System.currentTimeMillis() + time*1000,p1);*/

               // if(day.length() !=0 && month.length() !=0 && year.length() !=0 && hour.length() !=0 && minute.length() !=0 && format1.length() !=0 && Task.length() !=0)
               // {
                //    boolean msg = myDb.adddata(day,month,year,hour,minute,format1,Task);

                 //   if(msg==true) {
                 //       Toast.makeText(SetTask.this, "Successfully Added!", Toast.LENGTH_SHORT).show();
                //    }
                //    else {
                        Toast.makeText(SetTask.this, "Please Try Again", Toast.LENGTH_SHORT).show();
               //     }




                    /*d.setText("");
                    m.setText("");
                    y.setText("");
                    h.setText("");
                    mi.setText("");
                    format.setText("");
                    task.setText("");*/

               //}
               // else{
              //      Toast.makeText(SetTask.this,"FIll THE EMPTY FIELD",Toast.LENGTH_SHORT).show();
             //  }

            }

        });



    }
    private void setAlarm(long timeInMillis) {


        Intent intent = new Intent(this, MyAlarm.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        alarmManager.setRepeating(AlarmManager.RTC,System.currentTimeMillis()+timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(SetTask.this, "Alarm set", Toast.LENGTH_SHORT).show();
    }

}
