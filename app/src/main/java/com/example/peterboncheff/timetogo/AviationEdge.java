package com.example.peterboncheff.timetogo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

class AviationEdge {

    AviationEdge(String query, Context context) { }

    void implementAPI(String query, Context context){
        taskLoadUp(query, context);
    }

    private String getAPIJSON() {
        String JSON = "";
        final String FLIGHT_TERMINAL_API = "49f5b3a2d0mshd3b97efe440fdf6p1d9247jsnc7f3da915b02";
        final String WEBSITE_LINK = "https://flightlookup-terminalinfo-uri.p.rapidapi.com/TerminalInfo/?To=LAX&Date=12%2F17%2F2012&Airline=AA&From=BOS";
        final String RAPID_KEY_HEADER = "X-RapidAPI-Key";

        // departureTime arrivalTime arrivalTerminal flightNumber
        // delay scheduledTime estimatedTime actualTime
        // http://aviation-edge.com/v2/public/timetable?key=6c6a2c-1327d8&iataCode=EDI&type=arrival
        return JSON;
    }

    private void taskLoadUp(String query, Context context) {
        if (checkNetworkAvailability(context.getApplicationContext())) {
            FlightsCaller task = new FlightsCaller();
            task.execute(query);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class FlightsCaller extends AsyncTask<String, Void, String> {
        /**
         * Take url and pass forward;
         */
        @Override
        protected String doInBackground(String... urls) {
            final String BEGINNING_API_HTTP = "http://aviation-edge.com/v2/public/timetable?key=6c6a2c-1327d8&iataCode=", END_OF_API_HTTP = "&type=departure";
            // EDI
            final int GET_FIRST_URL = 0;
            return AviationEdge.execute(BEGINNING_API_HTTP + urls[GET_FIRST_URL] + END_OF_API_HTTP);
        }

        /**
         * Using the parsed JSON from an external source -> set the variables/icons/colors
         */
        @Override
        protected void onPostExecute(String query) {
            try {
                final JSONObject json = new JSONArray(query).getJSONObject(0);
                final JSONObject departure = json.getJSONObject("departure");
                final JSONObject arrival = json.getJSONObject("arrival");
                final JSONObject flight = json.getJSONObject("flight");
                final JSONObject codeShared = json.getJSONObject("codeshared").getJSONObject("flight");

                final JSONObject jsonTwo = new JSONArray(query).getJSONObject(2);
                //IMPORTANT! SOMETIMES THERE IS NO DELAY SO WE HAVE TO CHECK FIRST BEFORE PRINTING!

                // flight.getString("number"), flight.getString("iataNumber"),
                // flight.getString("icaoNumber") = "number":"4045","iataNumber":"EK4045","icaoNumber":"UAE4045"
                // flight.getString("number"), flight.getString("iataNumber"), flight.getString("icaoNumber");

                System.out.println("\n\n\n" + json + "\n\n\n");
                System.out.println("Scheduled time: " + departure.getString("scheduledTime"));
                System.out.println("arrival stuff: " + arrival.getString("scheduledTime"));
                System.out.println("flight stuff: " + flight.getString("number"));
                System.out.println("codeShared stuff: " + codeShared.getString("iataNumber") + "\n" + codeShared.getString("icaoNumber"));
                System.out.println("Type is: " + json.getString("type"));
                System.out.println("DELAY " + jsonTwo.getJSONObject("arrival").getString("delay"));

                //System.out.println("JSON TWO: " + jsonTwo);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private static boolean checkNetworkAvailability(Context context) {
        return ((ConnectivityManager)
                Objects.requireNonNull
                        (context.getSystemService(Context.CONNECTIVITY_SERVICE)))
                .getActiveNetworkInfo() != null;
    }

    /**
     * Get and parse the json info
     */
    private static String execute(String http) {
        HttpConnection httpConnection = new HttpConnection(http);
        return httpConnection.connectAndParseJson();
    }

}
