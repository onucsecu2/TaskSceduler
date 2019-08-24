package com.example.dhhm.taskscheduler;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import java.util.ArrayList;

public class ViewTask extends AppCompatActivity {
    ListView listView;
    SearchView searchView;
    Database myDb;
    ArrayAdapter<String> listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        listView = (ListView) findViewById(R.id.listView);
        searchView = (SearchView) findViewById(R.id.searchView);
        myDb = new Database(this);


        ArrayList<String> thelist = new ArrayList<>();
        Cursor data = myDb.getListContents();

        if (data.getCount() != 0) {
            while (data.moveToNext()) {
                final int d,m, y, h, min;
                final int hh;
                final String Task,format;

                d = data.getInt(1);
                m = data.getInt(2);
                y = data.getInt(3);
                h = data.getInt(4);
                min = data.getInt(5);
                Task = data.getString(6);
                String time, date, info;

                if(h==0){
                    hh=12;
                    format="AM";
                }
                else if(h>12){
                    hh=h-12;
                    format="PM";
                }
                else{
                    hh=h;
                    format="AM";
                }
                date = d + "-" + m + "-" + y;
                time = hh + ":" + min + " "+format;
                info = date + "\n" + time + "\n" + Task;

                thelist.add(info);
                //thelist1.add(info1);

                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thelist);

                listView.setAdapter(listAdapter);

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String text) {

                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                            listAdapter.getFilter().filter(newText);
                            return false;



                    }
                });


                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                            //for(i=0;i<10;i++) {

                                // Cursor iddata;
                                //  iddata=myDb.getTaskid(Task);
                                //int ID=-1;
                                // while(iddata.moveToNext()){
                                //     ID=iddata.getInt(0);
                                //  }
                                // if(ID<0){
                                // Toast.makeText(ViewTask.this,"not Found!!",Toast.LENGTH_SHORT).show();
                                //}
                                //else{
                                Intent myintent = new Intent(view.getContext(), UpdateTask.class);
                                //myintent.putExtra("ID",ID);
                                myintent.putExtra("D", d);
                                myintent.putExtra("M", m);
                                myintent.putExtra("Y", y);
                                myintent.putExtra("MIN", min);
                                myintent.putExtra("H", hh);
                                myintent.putExtra("task", Task);
                                myintent.putExtra("FORMAT", format);
                                startActivityForResult(myintent, i);
                                //}
                                //


                           // }

                        return false;
                    }
                });

            }

        }
    }

}
