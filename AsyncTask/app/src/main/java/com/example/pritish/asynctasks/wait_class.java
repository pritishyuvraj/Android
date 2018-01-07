package com.example.pritish.asynctasks;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.TextView;

/**
 * Created by pritish on 1/6/2018.
 */

public class wait_class extends AsyncTask<Void, Void, String> {

    /*
    @Override
    protected String doInBackground(Void... voids) {
        int time_to_sleep = 3;
        SystemClock.sleep(time_to_sleep*1000);
        return "Slept";
    }
    */

    @Override
    protected String doInBackground(Void... voids) {
        int time_to_sleep = 3;
        SystemClock.sleep(time_to_sleep*1000);
        return "Slept";
    }

    /*
    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }
    */

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

   /*
    @Override
    protected void onPostExecute(String results) {
        super.onPostExecute(results);
    }
    */
}
