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

public class MainActivity extends AppCompatActivity
implements LoaderManager.LoaderCallbacks<String>{

    public static final int OPERATION_SEARCH_LOADER = 22;
    public static final String OPERATION_URL_EXTRA = "url_that_return_json_data";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportLoaderManager().initLoader(OPERATION_SEARCH_LOADER, null, this);
        makeOperationSearchQuery("asdasd");
    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        return new android.support.v4.content.AsyncTaskLoader<String>(this) {

            String resultFromHttp;

            @Override
            public String loadInBackground() {
                SystemClock.sleep(4*1000);
                return null;
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
