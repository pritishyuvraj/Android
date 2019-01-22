package com.example.pritish.countryflag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String country[] = {"India", "Pakistan"};
    int flag[] = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customAdapter CustomAdapter = new customAdapter(country, flag, getApplicationContext());
        ListView listView = findViewById(R.id.list_item);
        listView.setAdapter(CustomAdapter);
    }
}
