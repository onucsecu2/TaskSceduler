package com.example.dhhm.taskscheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dhhm on 11/4/2017.
 */
public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "task_scheduler.db";
    public static final String TABLE_NAME = "task_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "DD";
    public static final String COL3 = "MM";
    public static final String COL4 = "YYYY";
    public static final String COL5 = "HH";
    public static final String COL6 = "MIN";
    public static final String COL7 = "FORMAT";
    public static final String COL8 = "TASK";

    public Database(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE  TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,DD TEXT, MM TEXT,YYYY TEXT,HH TEXT,MIN TEXT,FORMAT TEXT,TASK TEXT )" ;
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean adddata(String dd,String mm,String yyyy,String hh,String min,String format,String task)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(COL2,dd);
        values.put(COL3,mm);
        values.put(COL4,yyyy);
        values.put(COL5,hh);
        values.put(COL6,min);
        values.put(COL7,format);
        values.put(COL8,task);

        long result = db.insert(TABLE_NAME,null,values);

        if(result == -1) {
            return false;
        }
        else return true;
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return  data;
    }
    public Cursor getTaskid(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT ID FROM " + TABLE_NAME + " WHERE "+ COL8 + " = '  "+ name +"'",null);
        return  data;
    }
    public void updateData(String day,String d2,String month,String m2,String year,String y2,String hour,String h2,String minute,String min2, String format1,String format2,String Task,String Task2){
        SQLiteDatabase db = this.getWritableDatabase();
        String query= "UPDATE "+TABLE_NAME+" SET "+ COL2 + " = '"+day+ "' ,"+COL3+"= '"+month+"' , "+ COL4+" = '"+year+"' , "+COL5+"= '"+hour+"' , "+COL6+" = '"+minute+"' , "+COL7+" = '"+format1+"' , "+COL8+" = '"+Task+"' WHERE "+COL2 + "= '"+d2+"' AND "+COL3+" = '"+m2+"' AND "+ COL4+"= '"+y2+"' AND "+COL8+"= '"+Task2+"' ";
        db.execSQL(query);

    }
    public void deleteData(String day,String month,String year,String hour,String minute, String format1,String Task){
        SQLiteDatabase db = this.getWritableDatabase();
        String query= "DELETE FROM "+TABLE_NAME+" WHERE "+ COL2 + " = '"+day+"' AND "+COL3+"= '"+month+"' AND "+ COL4+" = '"+year+"' AND "+COL5+"= '"+hour+"' AND "+COL6+" = '"+minute+"' AND "+COL7+" = '"+format1+"' AND "+COL8+" = '"+Task+"'";
        //String query= "DELETE FROM "+TABLE_NAME+" WHERE "+ COL8+" = '"+Task+"'";
        db.execSQL(query);

    }

}