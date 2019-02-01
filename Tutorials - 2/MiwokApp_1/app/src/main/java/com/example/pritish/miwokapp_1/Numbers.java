package com.example.pritish.miwokapp_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numbers);

//        ArrayList<String> planets = new ArrayList<>();
//        planets.add("Sun");
//        planets.add("Mercury");
//        planets.add("Venus");
//        planets.add("Earth");
//        planets.add("Mars");
//        planets.add("Jupiter");
//        planets.add("Saturn");
//        planets.add("Uranus");
//        planets.add("Neptune");
//        planets.add("Pluto");
//
//        ArrayAdapter<String> planets_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, planets);
//
//        GridView planets_list_view = findViewById(R.id.list);
//        planets_list_view.setAdapter(planets_adapter);

        ArrayList<CustomModel> customListOfUsers = new ArrayList<>();


        customAdapter customAdapterUsers = new customAdapter(this, customListOfUsers);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(customAdapterUsers);

        CustomModel newmodel = new CustomModel("Pritish", "Yuvraj");
        customAdapterUsers.add(newmodel);
        CustomModel newmodel2 = new CustomModel("Kate", "Upton");
        customAdapterUsers.add(newmodel2);


    }
}
