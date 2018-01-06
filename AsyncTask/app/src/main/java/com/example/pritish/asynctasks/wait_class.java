package com.example.pritish.asynctasks;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.TextView;

/**
 * Created by priti on 1/6/2018.
 */

public class wait_class extends AsyncTask<void, void, String> {


    @Override
    protected String doInBackground(void... voids) {
        int time_to_sleep = 3;
        SystemClock.sleep(time_to_sleep*1000);
        return "Slept";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
}
