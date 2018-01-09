package com.example.pritish.asynctaskloader;
//References: https://medium.com/@sanjeevy133/an-idiots-guide-to-android-asynctaskloader-76f8bfb0a0c0
//import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
//import android.content.Loader;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
implements LoaderManager.LoaderCallbacks<String>{

    public static final int OPERATION_SEARCH_LOADER = 22;
    public static final String OPERATION_URL_EXTRA = "url_that_return_json_data";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportLoaderManager().initLoader(OPERATION_SEARCH_LOADER, null, this);
        //makeOperationSearchQuery("asdasd");
    }
    public void begin_delay(View view){
        EditText secs = (EditText) findViewById(R.id.enter_seconds);
        String final_seconds = secs.getText().toString();
        Toast.makeText(this, "Button clicked", Toast.LENGTH_LONG).show();
        //getSupportLoaderManager().initLoader(OPERATION_SEARCH_LOADER, null, this);

        makeOperationSearchQuery(final_seconds);
    }
    @Override
    public Loader<String> onCreateLoader(int i, final Bundle bundle) {
        return new android.support.v4.content.AsyncTaskLoader<String>(this) {

            String resultFromHttp;

            @Override
            public String loadInBackground() {
                String temp = bundle.getString(OPERATION_URL_EXTRA);
                int temp_secs = Integer.parseInt(temp);
                SystemClock.sleep(temp_secs*1000);
                return temp;

            }

            @Override
            protected void onStartLoading() {
                //super.onStartLoading();

                if (resultFromHttp != null){
                    deliverResult(resultFromHttp);
                }
                else{
                    forceLoad();
                }

            }

            @Override
            public void deliverResult(String data) {
                resultFromHttp = data;
                super.deliverResult(data);
            }
        };


    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {
        Log.e("Lets see", "See whats inside" + s);
        TextView temp = (TextView) findViewById(R.id.output);
        temp.setText("Slept for -> " + s + " seconds");
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    private void makeOperationSearchQuery(String url){

        Bundle queryBundle = new Bundle();

        queryBundle.putString(OPERATION_URL_EXTRA, url);

        LoaderManager loaderManager = getSupportLoaderManager();

        Loader<String> loader = loaderManager.getLoader(OPERATION_SEARCH_LOADER);

        if (loader == null){
            loaderManager.initLoader(OPERATION_SEARCH_LOADER, queryBundle, this);
        }
        else{
            loaderManager.restartLoader(OPERATION_SEARCH_LOADER, queryBundle, this);
        }
    }
}
