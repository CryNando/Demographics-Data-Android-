package com.example.root.workthread;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
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
    private TextView txtResult;
    private String option;
    private Button btShowGraph;
    private Context context;
    private InfoDemo infoDemo = new InfoDemo();

    public InternetAsync(Context context, TextView textView, Button btShowGraph){
        this.context = context;
        this.txtResult = textView;
        this.btShowGraph = btShowGraph;
    }

    @Override
    protected void onPreExecute() {
        txtResult.setText("");
    }

    @Override
    protected String doInBackground(String... args) {

        String host = hostUrl;
        host += "&countries=" + args[0] + "&data=" + args[1] + "&years=" + args[2] + ":" + args[3];
        option = args[1];
        infoDemo.setStartYear(Integer.valueOf(args[2]));
        infoDemo.setEndYear(Integer.valueOf(args[3]));
        infoDemo.setTopic(args[1]);

        HttpURLConnection httpURLConnection;

        try {
            URL url = new URL(host);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            //httpURLConnection.setReadTimeout(10000);
            //httpURLConnection.setConnectTimeout(15000);

            publishProgress();
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
    protected void onProgressUpdate(Void... values) {
        txtResult.append("Getting data... wait a moment..");
    }

    @Override
    protected void onPostExecute(String args) {

        try {
            JSONArray jsonArray = new JSONArray(args);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            infoDemo.setCountryCode(jsonObject.getString("countryCode"));
            infoDemo.setCountryName(jsonObject.getString("countryName"));
            JSONArray resultsArray = jsonObject.getJSONArray(option);
            int year;
            String data;


           txtResult.setText("Starting...\n\n");
           txtResult.append("CountryCode: " + infoDemo.getCountryCode() +
                       "\nCountryName: " + infoDemo.getCountryName());

            for(int i = 0; i<resultsArray.length(); i++){
                JSONObject jsonObject1 = resultsArray.getJSONObject(i);
                year  = Integer.valueOf((String) jsonObject1.get("year"));
                data = (String) jsonObject1.get("data");
                infoDemo.setResults(year,data);
                txtResult.append("\nYear: " + year + " Data: " + infoDemo.getResult(year));
            }

            txtResult.append("\n\n Finished");
            btShowGraph.setBackgroundColor(context.getResources().getColor(R.color.button_available));
            btShowGraph.setTag(1);

        }catch (JSONException ex){
            ex.printStackTrace();
        }

    }

    public InfoDemo getResults() {
        return infoDemo;
    }

}
