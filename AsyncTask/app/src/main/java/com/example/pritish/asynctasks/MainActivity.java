package com.example.pritish.asynctasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void wait_in_secs(View view){
        Toast.makeText(this, "Clicken on the button", Toast.LENGTH_LONG).show();
        int secs;
        /*
        try {
            EditText temp = (EditText) findViewById(R.id.seconds);
            String temp_secs = temp.getText().toString();
            //Log.e("Exception", "Exception is -> " + temp_secs);
            secs = Integer.parseInt(temp_secs);
            TextView temp2 = (TextView) findViewById(R.id.secs_demanded);
            temp2.setText("User's demand " + temp_secs);
        }
        catch (Exception e){
            Log.e("Exception", "Exception is -> error " + e);
            //Log.e("Exception", "Exception is -> " + temp_secs);
        }
        //secs store no of seconds
        */

        ///*
        wait_class async = new wait_class(this);
        //async.execute();
        ///*
        try{
            EditText temp = (EditText) findViewById(R.id.seconds);
            String temp_secs = temp.getText().toString();
            //Log.e("Exception", "Exception is -> " + temp_secs);
            secs = Integer.parseInt(temp_secs);
            TextView temp2 = (TextView) findViewById(R.id.secs_demanded);
            temp2.setText("User's demand " + temp_secs);

            String var = async.execute(secs).get();
            //String var2 = async.execute(secs).getStatus().toString();
            //String var = async.execute(secs).getStatus().toString();
            TextView temp_string = (TextView) findViewById(R.id.show_text);
            temp_string.setText("Output is -> " + var);
            Log.e("Tag", "final output is " + var);
        }
        catch (InterruptedException e){

        }
        catch (ExecutionException e){

        }
        //*/
        //*/
    }



}
