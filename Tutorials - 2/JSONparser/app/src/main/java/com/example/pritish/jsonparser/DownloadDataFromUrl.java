package com.example.pritish.jsonparser;


import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadDataFromUrl {
    private static final String TAG = DownloadDataFromUrl.class.getSimpleName();

    public DownloadDataFromUrl(){

    }

    public String makeServiceCall(String reqUrl){
        String response = null;

        try{
            URL url = new URL(reqUrl);
            HttpURLConnection conn =(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

//            read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertToStreamToString(in);
        } catch (MalformedURLException e){
            Log.d("TAG ", e.getMessage());
        } catch (IOException e){
            Log.d("TAG ", e.getMessage() );
        } catch (Exception e){
            Log.d("TAG", e.getMessage());
        }
        return response;
    }

    private String convertToStreamToString(InputStream in){
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        String line;
        try{
            while( (line = reader.readLine()) != null ){
                sb.append(line).append('\n');
            }
        }catch (IOException e){
            Log.d("TAG", e.getMessage());
        } finally {
            try{
                in.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
