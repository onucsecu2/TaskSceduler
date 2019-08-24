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
    ArrayAdapter<String> listAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        listView = (ListView) findViewById(R.id.listView);
        searchView = (SearchView) findViewById(R.id.searchView);
        myDb = new Database(this);


        ArrayList<String> thelist = new ArrayList<>();
        ArrayList<String> thelist1 = new ArrayList<>();

        Cursor data = myDb.getListContents();

        if (data.getCount() != 0) {
            while (data.moveToNext()) {
                final String d, m, y, h, min, format, Task;
                final String id;

                d = data.getString(1);
                m = data.getString(2);
                y = data.getString(3);
                h = data.getString(4);
                min = data.getString(5);
                format = data.getString(6);
                Task = data.getString(7);
                String time, date, info,info1;
                date = d + "-" + m + "-" + y;
                time = h + ":" + min + " " + format;

                info = date + "\n" + time + "\n" + Task;
                info1= Task + "\n" + time + "\n" + date;

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
                            if(i==0) {

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
                                myintent.putExtra("H", h);
                                myintent.putExtra("FORMAT", format);
                                myintent.putExtra("task", Task);
                                startActivityForResult(myintent, 0);
                                //}
                                //


                            }

                        return false;
                    }
                });

            }

        }

    }

}
