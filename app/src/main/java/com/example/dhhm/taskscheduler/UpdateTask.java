package com.example.dhhm.taskscheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class UpdateTask extends AppCompatActivity {
    NumberPicker d,m,y,h,mi,format;
    EditText task;
    Button submit,delete;

    Database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        d = (NumberPicker) findViewById(R.id.dd1);
        m= (NumberPicker) findViewById(R.id.mm1);
        y= (NumberPicker) findViewById(R.id.yyyy1);
        h= (NumberPicker) findViewById(R.id.hh1);
        mi= (NumberPicker) findViewById(R.id.min1);
        format = (NumberPicker) findViewById(R.id.format1);
        task= (EditText) findViewById(R.id.task1);

        submit = (Button) findViewById(R.id.submit1);
        delete = (Button) findViewById(R.id.delete);
        myDb = new Database(this);


        final int d2,m2,y2,min2,h2;
        final String Task2,format2;
        //int id;
        Intent recievedIntent= getIntent();
        //id=recievedIntent.getIntExtra("ID",0);
        d2=recievedIntent.getIntExtra("D",0);
        m2=recievedIntent.getIntExtra("M",0);
        y2=recievedIntent.getIntExtra("Y",0);
        min2=recievedIntent.getIntExtra("MIN",0);
        h2=recievedIntent.getIntExtra("H",0);
        Task2=recievedIntent.getStringExtra("task");
        format2=recievedIntent.getStringExtra("FORMAT");

        //String test;
        //test="data is "+"/"+m2+"/"+y2+" "+h2+":"+min2+"  "+Task2;
       // Toast.makeText(UpdateTask.this, test, Toast.LENGTH_SHORT).show();
        d.setMinValue(1);
       // d.setValue(d2);
        d.setMaxValue(31);

        m.setMinValue(1);
       // m.setValue(m2);
        m.setMaxValue(12);
        y.setMaxValue(2117);
        //y.setValue(y2);
        y.setMinValue(2017);

        h.setMinValue(1);
       // h.setValue(h2);
        h.setMaxValue(12);
        mi.setMinValue(00);
      //  mi.setValue(min2);
        mi.setMaxValue(59);
        final String[] values= {"AM","PM"};
        format.setMinValue(0);
        format.setMaxValue(values.length-1);
        format.setDisplayedValues(values);
        //format.setValue("format2");
        task.setText(Task2);




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day=d.getValue();
                int month=m.getValue();
                int year=y.getValue();
                int hour=h.getValue() ;
                int minute=mi.getValue();
                int formate =format.getValue();
                String Task=task.getText().toString();

                if(formate==1){
                    hour=hour+12;
                    if(hour==24)
                        hour=0;
                }
                    myDb.updateData(day,d2,month,m2,year,y2,hour,h2,minute,min2,Task,Task2);
                    boolean msg=true;/* apatoto*/
                    if(msg==true) {
                        Toast.makeText(UpdateTask.this, "Successfully Updated!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(UpdateTask.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                    }


            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String test;
                test="data is "+" "+m2+"/"+y2+" "+h2+":"+min2+"  "+Task2;
                Toast.makeText(UpdateTask.this, test, Toast.LENGTH_SHORT).show();
                myDb.deleteData(d2,m2,y2,h2,min2,Task2);
                Toast.makeText(UpdateTask.this, "Successfully Deleted!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
