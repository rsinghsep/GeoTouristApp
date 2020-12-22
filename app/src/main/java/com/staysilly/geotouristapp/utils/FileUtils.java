package com.staysilly.geotouristapp.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.staysilly.geotouristapp.models.LocationStamp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileUtils {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private static final String TAG = "**FileUtils";
    private static final String FILE_NAME_LOCATION_TRACKER = "location_tracker";


    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    public static void writeLocationUpdate(Context context, LocationStamp locationStamp){
        if (context==null||locationStamp==null){
            Log.d(TAG, "writeLocationUpdate");
            return;
        }
        //ArrayList<LocationStamp> location_list = getLocationFileUpdate(context);
        ArrayList<LocationStamp> location_list = new ArrayList<>();
        location_list.add(locationStamp);

        Gson gson = new Gson();
        String json = gson.toJson(location_list);

        try (FileOutputStream fos = context.openFileOutput(FILE_NAME_LOCATION_TRACKER, Context.MODE_PRIVATE)) {
            fos.write(json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "IOException: " + e.getMessage());
        }
    }
    public static ArrayList<LocationStamp> getLocationFileUpdate(Context context){
        ArrayList<LocationStamp> retVal = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(FILE_NAME_LOCATION_TRACKER);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                Log.d(TAG, "line : --- " + line);
                Gson gson = new Gson();
                Type collectionType = new TypeToken<ArrayList<LocationStamp>>(){}.getType();
                LocationStamp locationStamp =  gson.fromJson(line, collectionType);
                retVal.add(locationStamp);
                Log.d(TAG, "line1" + line);
                while (line != null) {
                    stringBuilder.append(line).append('\n');
                    line = reader.readLine();
                    Log.d(TAG, "line: " + line);
                    locationStamp = gson.fromJson(line, LocationStamp.class);
                    if (locationStamp!=null){
                        Log.d(TAG, "locationStamp time: " + locationStamp.getLat());
                        retVal.add(locationStamp);
                    }
                }
            } catch (IOException e) {
                // Error occurred when opening raw file for reading.
            } finally {
                //retVal = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return retVal;
    }

}
