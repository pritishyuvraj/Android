package com.example.priti.live;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Text;
import android.util.Log;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private TextView mSearchResultsTextView;
    private String TAG = MainActivity.class.getSimpleName();
    private int userid;
    private String title;
    ArrayList<HashMap<String, String>> database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //                Recycler View
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void on_call_for_REST(View view){
//        do Nothing

        EditText temp_input = (EditText) findViewById(R.id.foodMessage);
        String user_input = temp_input.getText().toString();
        Log.e("Nothing", "Called us" + user_input );

        database = new ArrayList<>();
//        mSearchResultsTextView = (TextView) findViewById(R.id.base_string);
        try {
            //URL githubSearchUrl = new URL("https://jsonplaceholder.typicode.com/");
//            URL githubSearchUrl = new URL("https://jsonplaceholder.typicode.com/posts");
            String temp_url = "http://148.85.189.171:8000/posts/"+user_input;
            Log.e("URl", "temp URL " + temp_url);
//            URL githubSearchUrl = new URL("http://148.85.189.245/posts/"+user_input);
            URL githubSearchUrl = new URL("http://148.85.189.245:8080/posts/"+user_input);
//            URL githubSearc   hUrl = new URL("https://api.androidhive.info/contacts/");
            //URL githubSearchUrl = new URL("http://api.androidhive.info/contacts/contacts/0/33");
            //URL githubSearchUrl = new URL("https://api.github.com/search/repositories?" +
            //        "q=hello&sort=stars");
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
                Log.e(TAG, "Running the program: Title " + githubSearchResults);

                ///*
                try {
                    JSONArray jsonObj = new JSONArray(githubSearchResults);
                    //JSONObject object = (JSONObject) new JSONTokener(githubSearchResults).nextValue();
                    Log.e(TAG, "Running the program: Temp " + jsonObj);
                    for(int i =0; i<jsonObj.length(); i++){
                        Log.e(TAG, "Key value " + jsonObj.optJSONObject(i));
                        JSONObject temp_temp = jsonObj.optJSONObject(i);

//                        int id = temp_temp.getInt("id");
//                        int userId = temp_temp.getInt("userId");
//                        String title = temp_temp.getString("title");
//                        String body = temp_temp.getString("body");
//                        Log.e("Tag1", "See the message " + title);
//                        HashMap<String, String> temp = new HashMap<>();
//                        temp.put("id", Integer.toString(id));
//                        temp.put("userid", Integer.toString(userId));
//                        temp.put("title", title);
//                        temp.put("body", body);

                        String food_name = temp_temp.getString("food_name");
                        String Calorie = temp_temp.getString("calorie");
                        String time = temp_temp.getString("time");
                        String day = temp_temp.getString("day");
                        Log.e("Tag1", "See the message " + title);
                        HashMap<String, String> temp = new HashMap<>();
                        temp.put("food_name", food_name);
                        temp.put("MealTime", time);
                        temp.put("Calories", Calorie);
                        temp.put("Day", day);


                        Log.e(TAG, "temp temp this" + title);
                        database.add(temp);
                        Log.e(TAG, "Adding to database"+ temp);
                    }

                }
                catch (final JSONException e){
                    Log.e(TAG, "JSON parsing error:" + e.getMessage());
                }
                //*/

            } catch (IOException e) {
                e.printStackTrace();
            }
            return "string";
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {

            if (githubSearchResults != null && !githubSearchResults.equals("")) {


                List<String> input = new ArrayList<>();
//                for (int i = 0; i < 100; i++) {
//                    input.add("Test" + i);
//                }// define an adapter



//                LinearLayout root=(LinearLayout)findViewById(R.id.FoodNames_appear_here);
//                TextView[] t=new TextView[database.size()];
//                LinearLayout.LayoutParams dim=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                for(int i = 0; i<database.size(); i++){
                    HashMap<String, String> temp = new HashMap<>();
                    temp = database.get(i);
//                    t[i]=new TextView(MainActivity.this);
//                    t[i] = (TextView) findViewById(R.id.test_string);
//                    t[i] =
//                t[i].setLayoutParams(dim);
                    String test = "";
                    for(String key: temp.keySet()){
                        test += key + "-> " + temp.get(key) + "\n";

                    }
                    Log.e("look at the message", "find " + test);
                    input.add("Test" + test);


//                    t[i].setText(test);
//                    root.addView(t[i]);
                }
                mAdapter = new MyAdapter(database);
                recyclerView.setAdapter(mAdapter);
//                Initial View text
//                mSearchResultsTextView.setText(test);

            }
//            LinearLayout root=(LinearLayout)findViewById(R.id.FoodNames_appear_here);
//            TextView[] t=new TextView[10];
//            LinearLayout.LayoutParams dim=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            for(int i=0;i<10;i++)
//            {
//                t[i]=new TextView(MainActivity.this);
////                t[i].setLayoutParams(dim);
//                t[i].setText("YOHOHO: "+i);
//                root.addView(t[i]);
//            }
        }
    }
}