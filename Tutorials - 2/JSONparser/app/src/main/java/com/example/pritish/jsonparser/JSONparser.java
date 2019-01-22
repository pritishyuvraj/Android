package com.example.pritish.jsonparser;

import android.app.ProgressDialog;
import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JSONparser extends AsyncTask<Void, Void, Void> {

    private ProgressDialog pDialog;
    private Context MainActivityContext;
    private static String url = "https://api.androidhive.info/contacts/";
    ArrayList<HashMap<String, String>> contactList;
    private ListView lv;

    public JSONparser(Context MainActivityContext, ListView lv){
        this.MainActivityContext = MainActivityContext;
        this.lv = lv;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

//        Show progress Dialog
        pDialog = new ProgressDialog(this.MainActivityContext);
        pDialog.setMessage("Please Wait......");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        contactList = new ArrayList<>();
        DownloadDataFromUrl sh = new DownloadDataFromUrl();
        String jsonStr = sh.makeServiceCall(url);
        Log.e("TAG", "response from the url: " + jsonStr);

        if (jsonStr != null){
            try{
                JSONObject jsonObject = new JSONObject(jsonStr);

//                Getting JSON Array node
                JSONArray contacts = jsonObject.getJSONArray("contacts");
                Log.e("TAG JSON array", contacts.toString());

                for (int i=0; i<contacts.length(); i++){
                    JSONObject c = contacts.getJSONObject(i);

                    String  id = c.getString("id");
                    String name = c.getString("name");
                    String email = c.getString("email");
                    String address = c.getString("address");
                    String gender = c.getString("gender");

//                    Phone node is JSON Object
                    JSONObject phone = c.getJSONObject("phone");
                    String mobile = phone.getString("mobile");
                    String home = phone.getString("home");
                    String office = phone.getString("office");

//                    Tmp hash map for single contact
                    HashMap<String, String> contact = new HashMap<>();

                    contact.put("id", id);
                    contact.put("name", name);
                    contact.put("email", email);
                    contact.put("mobile", mobile);

                    contactList.add(contact);

                }


            }catch (JSONException e){
                Log.e("TAG", "JSON exception -> " + e.getMessage());
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(pDialog.isShowing()){
            pDialog.dismiss();
        }

        ListAdapter adapter = new SimpleAdapter(this.MainActivityContext, contactList, R.layout.list_item, new String[]{"name", "email", "mobile"}, new int[]{R.id.name, R.id.email, R.id.mobile});
        this.lv.setAdapter(adapter);
    }
}
