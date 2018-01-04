package com.example.pritish.intent;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    void website(View view){
        Context context =  getApplicationContext();
        CharSequence text = "User wants to view Website";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    void map(View view){
        Context context = getApplicationContext();
        CharSequence Text = "User wants to view Map";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, Text, duration);
        toast.show();
    }
    void textContext(View view){
        Context context = getApplicationContext();
        CharSequence Text = "User wants Text Context";
        int Duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, Text, Duration);
        toast.show();
    }
    void yourOwn(View view){
        Context context = getApplicationContext();
        CharSequence Text = "Personal View";
        int Duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, Text, Duration);
        toast.show();
    }
}
