package com.example.pritish.prioritytask;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TaskDBHelper db = new TaskDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), add_new_task.class);
                startActivity(intent);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        display();
    }
    public void display(){
        //Parent Layout
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.layout);


        //Layout Inflater
        LayoutInflater layoutInflater = getLayoutInflater();

        View view;

        Cursor cursor = db.get_task_description_priority();
        if (cursor.moveToFirst()){
            do{
                String desc = cursor.getString(1);
                String priority = cursor.getString(2);
                //TextView temp = findViewById(R.id.temp);
                //temp.setText(desc);
                try {
                    //view = layoutInflater.inflate(R.layout.text_view, parentLayout, false);
                    View myLayout = layoutInflater.inflate(R.layout.text_view, parentLayout, false);
                    TextView textView = (TextView) myLayout.findViewById(R.id.text_);
                    TextView textView1 = (TextView)myLayout.findViewById(R.id.priority);
                    textView1.setText(priority);
                    textView.setText(desc);
                    parentLayout.addView(myLayout);
                }catch (Exception e){
                    Log.e("tag", desc + "exception "+e.toString());
                }
            }while (cursor.moveToNext());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
