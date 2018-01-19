package com.example.pritish.prioritytask;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class add_new_task extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void added_new_task(View view){
        Toast.makeText(this, "Clicked the button", Toast.LENGTH_LONG).show();

        //Task description
        EditText task_description = findViewById(R.id.task_description);
        String task = task_description.getText().toString();

        //Radion Grourp Selection
        RadioGroup radioGroup = findViewById(R.id.radio_group_user);
        int getSelectedId =radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(getSelectedId);
        String priority = radioButton.getText().toString();

        Log.e("Task Name", "Added Task"+ task + "-> Priority " + priority);
    }
}
