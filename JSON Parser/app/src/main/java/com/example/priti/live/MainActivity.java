package com.example.priti.live;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import android.util.Log;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity {

    private TextView mSearchResultsTextView;
    private String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchResultsTextView = (TextView) findViewById(R.id.base_string);
        try {
            //URL githubSearchUrl = new URL("https://jsonplaceholder.typicode.com");
            //URL githubSearchUrl = new URL("http://api.androidhive.info/contacts/");
            URL githubSearchUrl = new URL("https://api.github.com/search/repositories?" +
                    "q=hello&sort=stars");
            Log.e(TAG, "Response from the url:" + githubSearchUrl);
            new GetContacts().execute(githubSearchUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public class GetContacts extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];

            String githubSearchResults = null;
            try {
                githubSearchResults = networkUtils.getResponseFromHttpUrl(searchUrl);
                /*
                try {
                    JSONObject jsonObj = new JSONObject(githubSearchResults);
                    Log.e(TAG, "Running the program"+ jsonObj);
                }
                catch (final JSONException e){
                    Log.e(TAG, "JSON parsing error:" + e.getMessage());
                }
                */

            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                mSearchResultsTextView.setText(githubSearchResults);
            }
        }
    }
}