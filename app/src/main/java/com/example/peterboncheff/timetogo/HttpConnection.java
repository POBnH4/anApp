package com.example.peterboncheff.timetogo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {

    private String http;

    HttpConnection(String http){
        this.http = http;
    }

    String connectAndParseJson(){
        HttpURLConnection connection = null;
        try {
            URL url = new URL(http);
            connection = (HttpURLConnection) url.openConnection();
            final String CONTENT_TYPE = "content-type", APPLICATION_JSON_CHARSET = "application/json; charset=utf-8";
            final String CONTENT_LANGUAGE = "Content-Language", LANGUAGE = "en-GB";
            connection.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON_CHARSET);
            connection.setRequestProperty(CONTENT_LANGUAGE, LANGUAGE);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(false);

            InputStream in;
            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) in = connection.getErrorStream();
            else in = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
                response.append("\n");
            }
            bufferedReader.close();
            return String.valueOf(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) connection.disconnect();
        }
    }
}
