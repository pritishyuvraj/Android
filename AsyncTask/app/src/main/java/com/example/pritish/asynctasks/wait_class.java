package com.example.pritish.asynctasks;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.view.ViewDebug;
import android.widget.TextView;

import org.w3c.dom.Text;
import android.app.Activity;
/**
 * Created by pritish on 1/6/2018.
 */

public class wait_class extends AsyncTask<Integer, Integer, String> {
    public Activity activity;
    public static String temp_string = "";
    public wait_class( Activity _activity){

        this.activity = _activity;
//other initializations...

    }
    /*
    @Override
    protected String doInBackground(Void... voids) {
        int time_to_sleep = 3;
        SystemClock.sleep(time_to_sleep*1000);
        return "Slept";
    }
    */

    @Override
    protected String doInBackground(Integer... integers) {
        int time_to_sleep = integers[0];
        for(int i = 0; i<time_to_sleep; i++){
            try {
                publishProgress(i);
                Thread.sleep(1000);

            }
            catch (InterruptedException e){

            }

        }
        return Integer.toString(time_to_sleep);
    }

    /*
    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }
    */

    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.e("Seconds Elapsed -> ", "Seconds Elapsed" + values[0]);
        //TextView temp = (TextView) findViewById(R.id.)

        try {
            TextView temp = (TextView) this.activity.findViewById(R.id.secs_live);
            temp_string += "Seconds Elapsed -> " + values[0].toString() + '\n';
            temp.setText(temp_string);
        }
        catch (Exception e){
            Log.e("exception", "Exception -> " + e);
        }
        super.onProgressUpdate(values);
    }

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
