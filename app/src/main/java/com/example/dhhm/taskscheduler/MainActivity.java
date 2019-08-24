package com.example.dhhm.taskscheduler;


import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void setTask(View view){
        Intent intent = new Intent(this, SetTask.class);
        startActivity(intent);
    }
    public void viewTask(View view){
        Intent intent = new Intent(this, ViewTask.class);
        startActivity(intent);
    }
   /* public void update(View view){
        Intent intent = new Intent(this, UpdateTask.class);
        startActivity(intent);
    }*/
    public void notify(View view){
        Intent intent = new Intent(this, Notification.class);
        startActivity(intent);
    }
    public void rate(View view){
        Intent intent = new Intent(this, Rate.class);
        startActivity(intent);
    }
}
