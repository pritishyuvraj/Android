package com.example.pritish.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddName(View view){
        Log.d("TAG", "onClickAddName: " + ((EditText)findViewById(R.id.editText2)).getText().toString());
        Log.d("TAG2", "onClickAddName:" + ((EditText)findViewById(R.id.editText3)).getText().toString() );
//        Add a new student record
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME,
                ((EditText)findViewById(R.id.editText2)).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText)findViewById(R.id.editText3)).getText().toString());
        Log.d("TAG3", StudentsProvider.CONTENT_URI.toString() );
        Log.d("TAG4", values.toString());
        try{
            Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI, values);
            Log.d("TAG5", uri.toString());
            Toast.makeText(getBaseContext(),
                    uri.toString(), Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Log.d("TAG6", "The exception is -> " + e);
        }


    }

    public void onClickRetrieveStudents(View view){
//        Retrieve student records
        String URL = "content://com.example.MyApplication.StudentsProvider";

        Uri students = Uri.parse(URL);
        Log.d("TAG8", students.toString());
        Cursor c = managedQuery(students, null, null, null, "name");
        Log.d("TAG9", c.toString());
        if(c.moveToFirst()){
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentsProvider._ID)) + ", "
                        + c.getString(c.getColumnIndex(StudentsProvider.NAME)) + ", "
                        + c.getString(c.getColumnIndex(StudentsProvider.GRADE)),
                        Toast.LENGTH_LONG).show();

            }while(c.moveToNext());
        }
    }
}
