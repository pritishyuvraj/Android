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
        /*
        EditText temp = (EditText) findViewById(R.id.seconds);
        String temp_secs = temp.toString();
        int secs = Integer.parseInt(temp_secs);

        //secs store no of seconds
        */

        ///*
        wait_class async = new wait_class();
        //async.execute();
        ///*
        try{

            String var = async.execute().get();
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
