package com.wordpress.ayo218.spacenews;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Ayo on 1/12/2018.
 */

public class JsonHelperUtils {

    private String TAG = "JsonHelperUtils";
    
    public static ArrayList<RocketData> getSimpleSpaceJsonData(Context context, String spaceJsonStr) throws JSONException {
        ArrayList<RocketData> parsedSpaceData = new ArrayList<>();

        JSONArray spaceJson = new JSONArray(spaceJsonStr);
        
        for (int i = 0; i < spaceJson.length(); i++) {
            JSONObject jsonObject = spaceJson.getJSONObject(i);

            String flight_number = jsonObject.getString("flight_number");
            String launch_year = jsonObject.getString("launch_year");

            JSONObject rocketSection = jsonObject.getJSONObject("rocket");

            String rocket_name = rocketSection.getString("rocket_name");

            Log.d(TAG, "getSimpleSpaceJsonData: Rocket: " + rocket_name + " - " +  flight_number + " - " +  launch_year);
            RocketData rocketData = new RocketData(rocket_name, flight_number, launch_year);
            parsedSpaceData.add(rocketData);
        }

        return parsedSpaceData;
    }
}
