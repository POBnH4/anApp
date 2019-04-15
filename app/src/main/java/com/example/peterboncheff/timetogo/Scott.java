package com.example.peterboncheff.timetogo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Scott extends AppCompatActivity {


    EditText originText;
    EditText destinationText;
    TextView responseView;
    TextView directionsView;
    ProgressBar progressBar;
    String geoCodeOrigin;
    String geoCodeDestination;
    String destinationPlaceID;
    String originPlaceID;

    static final String API_KEY_DM = "AIzaSyCxph7LFQyy4vk-iUHE2P4VYMmHTXy7fnA";
    static final String API_URL_DM = "https://maps.googleapis.com/maps/api/distancematrix/json?";
    static final String API_KEY_G = "AIzaSyDOW33H9i7GS1O-igvg2hrfovEwTvtxa5Q";
    static final String API_URL_G = "https://maps.googleapis.com/maps/api/geocode/json?";
    static final String API_KEY_DIR = "AIzaSyCMYrORN1GKpTYm0NIt9325hzGuGZM9kVA";
    static final String API_URL_DIR = "https://maps.googleapis.com/maps/api/directions/json?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //responseView = (TextView) findViewById(R.id.responseView);
        //originText = (EditText) findViewById(R.id.origin);
        //destinationText = (EditText) findViewById(R.id.destination);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //directionsView = (TextView) findViewById(R.id.directionsTextView);

//        (Button) findViewById(R.id.button);
//
//        queryButton.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
//            @Override
//            public void onClick(View v) {
//                new RetrieveCodeTask().execute();
//                new RetrieveDirectionsTask().execute();
//            }
//        });
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    class RetrieveCodeTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            responseView.setText("");
        }

        protected String doInBackground(Void... urls) {
            String origin = originText.getText().toString();
            String destination = destinationText.getText().toString();
            // Do some validation here

            try {
                URL geourl = new URL(API_URL_G + "address=" + origin + "&key=" + API_KEY_G);
                HttpURLConnection urlConnection = (HttpURLConnection) geourl.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    geoCodeOrigin = stringBuilder.toString();
                    JSONObject obj1 = new JSONObject(geoCodeOrigin);
                    JSONArray arr1 = obj1.getJSONArray("results");
                    originPlaceID = arr1.getJSONObject(0).getString("place_id");
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }

            try {
                URL geourl = new URL(API_URL_G + "address=" + destination + "&key=" + API_KEY_G);
                HttpURLConnection urlConnection = (HttpURLConnection) geourl.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    geoCodeDestination = stringBuilder.toString();
                    JSONObject obj1 = new JSONObject(geoCodeDestination);
                    JSONArray arr1 = obj1.getJSONArray("results");
                    destinationPlaceID = arr1.getJSONObject(0).getString("place_id");
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
            try {
                URL url = new URL(API_URL_DM + "origins=place_id:" + originPlaceID + "&destinations=place_id:" + destinationPlaceID + "&key=" + API_KEY_DM);
                HttpURLConnection urlConnection3 = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(urlConnection3.getInputStream()));
                    StringBuilder stringBuilder3 = new StringBuilder();
                    String line3;
                    while ((line3 = bufferedReader3.readLine()) != null) {
                        stringBuilder3.append(line3).append("\n");
                    }
                    bufferedReader3.close();
                    String dmResult = stringBuilder3.toString();
                    JSONObject obj2 = new JSONObject(dmResult);
                    JSONArray arr2 = obj2.getJSONArray("rows");
                    JSONArray arr3 = arr2.getJSONObject(0).getJSONArray("elements");
                    String finalResult = arr3.getJSONObject(0).getJSONObject("duration").getString("text");
                    return finalResult;
                } finally {
                    urlConnection3.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            responseView.setText(response);

        }
    }


    @SuppressLint("StaticFieldLeak")
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    class RetrieveDirectionsTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            directionsView.setText("");
        }

        protected String doInBackground(Void... urls) {

            try {
                URL directionurl = new URL(API_URL_DIR + "origin=place_id:" + originPlaceID + "&destination=place_id:" + destinationPlaceID + "&key=" + API_KEY_DIR);
                HttpURLConnection urlConnection = (HttpURLConnection) directionurl.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    String directionsResult = stringBuilder.toString();
                    JSONObject obj3 = new JSONObject(directionsResult);

                    return obj3.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps").getJSONObject(0).getString("html_instructions");
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            directionsView.setText(response);

        }
    }


}
