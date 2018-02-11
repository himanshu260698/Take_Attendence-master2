package com.technical.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by hamuj on 2/8/2018.
 */

public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "AttendancMaster.db";
    public static String Range_TABLE_NAME ;
    public static String Rollno_TABLE_NAME;
    public static final String Section_TABLE_NAME="NameOfSections";


    public static final String Range_COLUMN_ID = "id";
    public static final String Range_COLUMN_1 = "Range_No";
    public static final String Range_COLUMN_2 = "From";
    public static final String Range_COLUMN_3 = "To";


    public static final String Rollno_COLUMN_ID = "id";
    public static final String Rollno_COLUMN_Rollno = "RollNo";



    public static final String Section_COLUMN_ID = "id";
    public static final String Section_COLUMN_Section_Name = "SectionName";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table " + Section_TABLE_NAME  +
                        " (" + Section_COLUMN_ID + " integer primary key autoincrement, " +
                        Section_COLUMN_Section_Name+ " text )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }


    // Add methods here which will return data you want  like getAllSubjects , getAllRollnumbers

    // Use final string and define it in the top of file

    public  void addSection(String SectionName){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(Section_COLUMN_Section_Name ,SectionName);
        db.insert(Section_TABLE_NAME, null, contentValues1);


    }

    public void CreateRange(String TableName){

        Range_TABLE_NAME=TableName;

        Log.i("TABLE NAMES",Range_TABLE_NAME);

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(
                "create table " + Range_TABLE_NAME  +
                        " (" + Range_COLUMN_ID + " integer primary key autoincrement, " +
                        Range_COLUMN_1+ " integer, "+
                        Range_COLUMN_2+ " iteger, "+
                        Range_COLUMN_3+ " integer)"
        );



    }


    public void addRange(String TableName,String range_no,String From,String To){
        Log.i("TABLE NAMES", TableName);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(Range_COLUMN_1 , range_no);
        db.insert(TableName, null, contentValues1);

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(Range_COLUMN_2 , From);
        db.insert(TableName, null, contentValues2);

        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(Range_COLUMN_3 , To);
        db.insert(TableName, null, contentValues3);


    }

    public void deleteRange(String Tablename,String range_no,String From,String To){
        Log.i("TABLE NAMES", Tablename);

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Tablename,Range_COLUMN_1+"="+range_no,null);
        db.delete(Tablename,Range_COLUMN_2+"="+From,null);
        db.delete(Tablename,Range_COLUMN_3+"="+To,null);

    }



    public void SetRollno(String TableName,String From,String To){

        Rollno_TABLE_NAME=TableName;

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(
                "create table " + Rollno_TABLE_NAME  +
                        " (" + Rollno_COLUMN_ID + " integer primary key autoincrement, " +
                        Rollno_COLUMN_Rollno+ " integer)"
        );

        try {



            for (int i = Integer.parseInt(From); i<=Integer.parseInt(To); i++) {
           // SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues1 = new ContentValues();
            contentValues1.put(Rollno_COLUMN_Rollno, i);
            db.insert(Rollno_TABLE_NAME, null, contentValues1);
             }

            }catch (NumberFormatException e) {

            Log.i("EXCEPTION_INFO", e.toString());


        }
    }

    public void TakeAttendence(String roll_no,String Day){

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("ALTER TABLE Rollno_TABLE_NAME ADD COLUMN Day INTEGER DEFAULT 0");


    }


    public void Present(String roll_no,String Day){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Day ,1);

        db.update(Rollno_TABLE_NAME,contentValues,Rollno_COLUMN_Rollno + "=" + roll_no ,null);


    }

    public void Absent(String roll_no,String Day){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Day ,0);

        db.update(Rollno_TABLE_NAME,contentValues,Rollno_COLUMN_Rollno + "=" + roll_no ,null);



    }

//    public void updateSubject(String old_sub_name  ,  String new_sub_name){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(SUBJECTS_COLUMN_SUBJECT_NAME,new_sub_name);
//
//        db.update(SUBJECTS_TABLE_NAME,contentValues,SUBJECTS_COLUMN_SUBJECT_NAME + "=" + old_sub_name ,null);
//
//
//    }




}
