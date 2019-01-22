package com.example.pritish.eventhandling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {
    Button button_larger, button_smaller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_larger = (Button) findViewById(R.id.button_larger);
        button_smaller = (Button) findViewById(R.id.button_smaller);

        button_larger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) findViewById(R.id.text2);
                float currentTextSize = text.getTextSize();
                Toast.makeText(getApplicationContext(), "Current font size -> " + currentTextSize, Toast.LENGTH_LONG).show();
                Log.d("Text size -> ",Float.toString(currentTextSize) + " <-> " +  Float.toString(currentTextSize + 5));
                text.setTextSize(TypedValue.COMPLEX_UNIT_PX, currentTextSize + 5);
            }
        });

        button_smaller.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView text2 = (TextView) findViewById(R.id.text2);
                float text2Size = text2.getTextSize();
                Toast.makeText(getApplicationContext(), "Current font size -> " + text2Size, Toast.LENGTH_LONG).show();
                Log.d("Text size -> ", Float.toString(text2Size) + " <-> "  + Float.toString(text2Size - 5));
                text2.setTextSize(TypedValue.COMPLEX_UNIT_PX, text2Size - 5);
            }
        });
    }
}
