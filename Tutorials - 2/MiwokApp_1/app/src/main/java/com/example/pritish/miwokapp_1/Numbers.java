package com.example.pritish.miwokapp_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numbers);
        ArrayList<CustomModel> customListOfUsers = new ArrayList<>();
        customAdapter customAdapterUsers = new customAdapter(this, customListOfUsers);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(customAdapterUsers);

        CustomModel newmodel = new CustomModel("Pritish", "Yuvraj", R.drawable.ic_launcher_background, R.raw.guitar);
        customAdapterUsers.add(newmodel);
        CustomModel newmodel2 = new CustomModel("Kate", "Upton", R.drawable.ic_launcher_foreground, R.raw.piano);
        customAdapterUsers.add(newmodel2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Item was clicked", Toast.LENGTH_SHORT).show();
                Log.e("TAG PRITISH", "On Item Click was pressed");
            }
        });
    }

}
