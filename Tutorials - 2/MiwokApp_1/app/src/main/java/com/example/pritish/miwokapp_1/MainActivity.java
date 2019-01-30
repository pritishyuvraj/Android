package com.example.pritish.miwokapp_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void numbersOnClick(View v){
        Toast.makeText(this, "Numbers touched", Toast.LENGTH_LONG).show();
        Intent openNumbers = new Intent(MainActivity.this, Numbers.class);
        startActivity(openNumbers);
    }

    public void familyOnClick(View v){
        Toast.makeText(this, "Family touched", Toast.LENGTH_LONG).show();
        Intent openFamily = new Intent(MainActivity.this, FamilyNumbers.class);
        startActivity(openFamily);
    }

    public void colorsOnClick(View v){
        Toast.makeText(this, "Colors touched", Toast.LENGTH_LONG).show();
        Intent openColors = new Intent(MainActivity.this, Colors.class);
        startActivity(openColors);
    }

    public void phrasesOnClick(View v){
        Toast.makeText(this, "Phrases touched", Toast.LENGTH_LONG).show();
        Intent openPhrases = new Intent(MainActivity.this, Phrases.class);
        startActivity(openPhrases);
    }
}
