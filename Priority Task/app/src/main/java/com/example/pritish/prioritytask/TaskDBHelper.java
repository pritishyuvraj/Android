package com.example.pritish.prioritytask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pritish on 1/18/2018.
 */

public class TaskDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "task.db";
    public static final String TABLE_NAME ="task_queue";
    public static final int Version = 1;

    public static final String KEY_ID = "id";
    public static final String Description = "descrption";
    public static final String Priority = "priority";

    TaskDBHelper(Context context){
        super(context, DATABASE_NAME, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String Create_table = "CREATE TABLE "+ TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Description + " TEXT,"
                + Priority + " TEXT);";
        sqLiteDatabase.execSQL(Create_table);
        Log.e("bla bla bla", "sql command ->" + Create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void add_task_description_priority(String task_description, String priority){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Description, task_description);
        values.put(Priority, priority);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor get_task_description_priority(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return cursor;
        /*
        if (cursor.moveToFirst()){
            do{
                Log.e("bla bla bla", "cursor ->" + cursor.getString(1));
                Log.e("bla bla bla", "cursor ->" + cursor.getString(2));
            }while (cursor.moveToNext());
        }
        */
        //Log.e("bla bla bla", "cursor" + cursor.getString(1));
        //Log.e("bla bla bla", "cursor" + cursor.getString(2));

    }
}
