package com.example.pritish.asynctasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wait_class async = new wait_class();
        try{
            String var = async.execute().get();
            Log.e("Tag", "final output is " + var);
        }
        catch (InterruptedException e){

        }
        catch (ExecutionException e){

        }




    }



}
