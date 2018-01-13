package com.wordpress.ayo218.spacenews;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ayo on 1/12/2018.
 */

public class JsonHelperUtils {

    public static ArrayList<RocketData> getSimpleSpaceJsonData(Context context, String spaceJsonStr) throws JSONException {
        ArrayList<RocketData> parsedSpaceData = new ArrayList<>();

            JSONObject spaceJson = new JSONObject(spaceJsonStr);
        // TODO: 1/12/2018 Come back and fix 

            String  flight_number = spaceJson.getString("flight_number");
            String launch_year = spaceJson.getString("launch_year");

            JSONObject rocketSection = spaceJson.getJSONObject("rocket");

            String rocket_name = rocketSection.getString("rocket_name");

            RocketData rocketData = new RocketData(rocket_name,flight_number, launch_year);
            parsedSpaceData.add(rocketData);

        return parsedSpaceData;
    }
}
