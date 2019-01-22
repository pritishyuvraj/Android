package com.example.pritish.baseadapter_image;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    int animals[] ={R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView simpleGrid = (GridView) findViewById(R.id.simpleGridView);
        customAdapter CustomAdapter = new customAdapter(getApplicationContext(), animals);
        simpleGrid.setAdapter(CustomAdapter);
    }
}
