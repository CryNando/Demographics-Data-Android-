package com.example.root.workthread;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by root on 28/09/17.
 */

public class InternetAsync extends AsyncTask<String, Void, String> {

    public static final String key = "11521db3f9e377bf";
    public static final String hostUrl = "http://inqstatsapi.inqubu.com/?api_key=" + key;
    private TextView textView;



    public InternetAsync(TextView tv){
        this.textView = tv;
    }

    @Override
    protected String doInBackground(String... args) {

        String host = hostUrl;
        host += "&data=" + args[0] + "&countries=" + args[1] + "&years=" + args[2] + ":" + args[3];

        HttpURLConnection httpURLConnection;

        try {
            URL url = new URL(host);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(httpURLConnection.getInputStream()));


            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            return  sb.toString();

        } catch (Exception ex){
            ex.printStackTrace();
            return  null;
        }
    }

    @Override
    protected void onPostExecute(String args) {
        InfoDemo infoDemo = new InfoDemo();
        try {
            JSONArray jsonArray = new JSONArray(args);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            infoDemo.setCountryCode(jsonObject.getString("countryCode"));
            infoDemo.setCountryName(jsonObject.getString("countryName"));
            JSONArray jsonArray1 = jsonObject.getJSONArray("population");
            int year;
            String data;

            for(int i = 0; i<jsonArray1.length(); i++){
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                year  = Integer.valueOf((String) jsonObject1.get("year"));
                data = (String) jsonObject1.get("data");
                infoDemo.setResults(year,data);
            }

            textView.append("CountryCode " + infoDemo.getCountryCode() + "\nCountryName " + infoDemo.getCountryName());

            for(int i = 0; i<jsonArray1.length(); i++){
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                year  = jsonObject1.getInt("year");
                textView.append("\nYear: " + year + "Data: " + infoDemo.getResult(year));
            }

            textView.append("\n Finalizado");




        }catch (JSONException ex){
            ex.printStackTrace();
        }

    }

}
