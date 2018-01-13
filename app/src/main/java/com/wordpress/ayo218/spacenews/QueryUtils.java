package com.wordpress.ayo218.spacenews;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Ayo on 10/12/2017.
 */

public final class QueryUtils {
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();


    public static URL buildUrl(String rocketUrl){
        Uri buildUrl = Uri.parse(rocketUrl).buildUpon().build();

        URL url = null;
        try {
            url = new URL(buildUrl.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        Log.i(LOG_TAG, "Built URL: " + url);
        return url;
    }

    static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Log.i(LOG_TAG, "Connected");

            StringBuilder output = new StringBuilder();
            if (in != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(in, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();


        } finally {
            urlConnection.disconnect();
        }
    }


}
