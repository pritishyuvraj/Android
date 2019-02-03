package com.example.androidversions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<String> planets = new ArrayList<>();
//        planets.add("Sun");
//        planets.add("Mercury");
//        planets.add("Venus");
//        planets.add("Earth");
//        planets.add("Mars");
//        planets.add("Jupiter");
//        planets.add("Uranus");
//        planets.add("Neptune");
//        planets.add("Pluto");
//
//        ArrayAdapter<String> planetsArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, planets);
//
//        ListView planetsListView = findViewById(R.id.list_view);
//        planetsListView.setAdapter(planetsArrayAdapter);

        ArrayList<WordImageContent> arrayOfAndroids = new ArrayList<>();

        CustomAdapter adapter = new CustomAdapter(this, arrayOfAndroids);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);


//        Populating data into ListView
        adapter.add(new WordImageContent("Donut", "1.6", R.drawable.ic_launcher_background));
        adapter.add(new WordImageContent("Eclair", "2.0-2.1", R.drawable.ic_launcher_foreground));


    }
}
