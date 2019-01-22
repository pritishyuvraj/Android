package com.example.pritish.handlers;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView text_;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_ = (TextView) findViewById(R.id.text);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    public void onButtonClick(View v){
        Toast.makeText(this, "Button Click ", Toast.LENGTH_LONG).show();
        progressBar_();
    }

    public void progressBar_(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i<11; i++){
                    final int value = i;
                    wasteComputationCycle();
                    progressBar.post(new Runnable() {
                        @Override
                        public void run() {
                            text_.setText("Progress: " + Integer.toString(value));
                            progressBar.setProgress(value);
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }

    void wasteComputationCycle(){
        SystemClock.sleep(1000);
    }
}
