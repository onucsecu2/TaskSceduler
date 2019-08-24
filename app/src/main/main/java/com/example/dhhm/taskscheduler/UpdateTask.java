package com.example.dhhm.taskscheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateTask extends AppCompatActivity {

    EditText d,m,y,h,mi,format,task;
    Button submit,delete;

    Database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        d = (EditText) findViewById(R.id.dd1);
        m= (EditText) findViewById(R.id.mm1);
        y= (EditText) findViewById(R.id.yyyy1);
        h= (EditText) findViewById(R.id.hh1);
        mi= (EditText) findViewById(R.id.min1);
        format = (EditText) findViewById(R.id.format1);
        task= (EditText) findViewById(R.id.task1);

        submit = (Button) findViewById(R.id.submit1);
        delete = (Button) findViewById(R.id.delete);
        myDb = new Database(this);

        final String d2,m2,y2,min2,Task2,h2,format2;
        //int id;
        Intent recievedIntent= getIntent();
        //id=recievedIntent.getIntExtra("ID",0);

        d2=recievedIntent.getStringExtra("D");
        m2=recievedIntent.getStringExtra("M");
        y2=recievedIntent.getStringExtra("Y");
        min2=recievedIntent.getStringExtra("MIN");
        h2=recievedIntent.getStringExtra("H");
        Task2=recievedIntent.getStringExtra("task");
        format2=recievedIntent.getStringExtra("FORMAT");
        d.setText(d2);
        m.setText(m2);
        y.setText(y2);
        h.setText(h2);
        mi.setText(min2);
        format.setText(format2);
        task.setText(Task2);



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

                if(day.length() !=0 && month.length() !=0 && year.length() !=0 && hour.length() !=0 && minute.length() !=0 && format1.length() !=0 && Task.length() !=0)
                {
                    myDb.updateData(day,d2,month,m2,year,y2,hour,h2,minute,min2,format1,format2,Task,Task2);
                    boolean msg=true;/* apatoto*/
                    if(msg==true) {
                        Toast.makeText(UpdateTask.this, "Successfully Updated!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(UpdateTask.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                    }
                    d.setText("");
                    m.setText("");
                    y.setText("");
                    h.setText("");
                    mi.setText("");
                    format.setText("");
                    task.setText("");

                }
                else{
                    Toast.makeText(UpdateTask.this,"FIll THE EMPTY FIELD",Toast.LENGTH_SHORT).show();
                }

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb.deleteData(d2,m2,y2,h2,min2,format2,Task2);
                Toast.makeText(UpdateTask.this, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
                d.setText("");
                m.setText("");
                y.setText("");
                h.setText("");
                mi.setText("");
                format.setText("");
                task.setText("");
            }
        });
    }
}
