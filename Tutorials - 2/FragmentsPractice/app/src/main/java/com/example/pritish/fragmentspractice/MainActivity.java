package com.example.pritish.fragmentspractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.activity_main);
        } catch (Exception e){
            Log.e("TAG Pritish", "Exceptions is from Main Activity-> " + e.getMessage());
        }

    }
}
