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
import java.util.List;

public class FileUtils {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private static final String TAG = "**FileUtils";
    private static final String FILE_NAME_LOCATION_TRACKER = "location_tracker";

    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    public static void addCurrentLocationInFile(Context context, LocationStamp locationStamp){
        Log.d(TAG, "writeLocationUpdate begins");
        if (context==null||locationStamp==null){
            Log.d(TAG, "writeLocationUpdate");
            return;
        }
        Gson gson = new Gson();
        String json = gson.toJson(getLatestTenLocationStamp(getAllSavedLocationFromFile(context), locationStamp));
        try (FileOutputStream fos = context.openFileOutput(FILE_NAME_LOCATION_TRACKER, Context.MODE_PRIVATE)) {
            fos.write(json.getBytes());
            Log.d(TAG, "write to byte successful");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "IOException: " + e.getMessage());
        }
    }
    public static ArrayList<LocationStamp> getAllSavedLocationFromFile(Context context){
        ArrayList<LocationStamp> retVal = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(FILE_NAME_LOCATION_TRACKER);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                Log.d(TAG, "line : --- " + line);
                Gson gson = new Gson();
                Type collectionType = new TypeToken<List<LocationStamp>>(){}.getType();
                List<LocationStamp> locationStamp =  gson.fromJson(line, collectionType);
                retVal = (ArrayList<LocationStamp>) locationStamp;
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
    public static ArrayList<LocationStamp> getLatestTenLocationStamp(ArrayList<LocationStamp> locationStamps, LocationStamp currentLocationStamp){
        ArrayList<LocationStamp> retVal = new ArrayList<>();
        Log.d(TAG, "getLastTenLocationStamp");

        //if there is no location in file
        if (locationStamps==null||locationStamps.isEmpty()){
            Log.d(TAG, "no previous location found");
            if (currentLocationStamp==null){
                //case1: if no previous location and no current location return empty list
                Log.d(TAG, "case1: if no previous location and no current location");
            }else {
                //case2: if no previous location but has current location return current location in list
                Log.d(TAG, "case2: if no previous location but has current location");
                retVal.add(currentLocationStamp);
            }
        }else {
            int size = locationStamps.size();
            if (currentLocationStamp==null){
                //case3: found previous location but no current location
                Log.d(TAG, "case3: found previous location but no current location");
                retVal = locationStamps;
                return retVal;
            }else {
                if (size<10){
                    //case4: both previous location and current location found and max limit not achieved
                    Log.d(TAG, "case4: both previous location and current location found and max limit not achieved");
                    locationStamps.add(currentLocationStamp);
                    retVal = locationStamps;
                }else {
                    //case5: both previous location and current location found and max limit achieved
                    Log.d(TAG, "case5: both previous location and current location found and max limit achieved");
                    retVal = removeOldestLocation(locationStamps);
                    retVal.add(currentLocationStamp);
                }
            }
        }

        Log.d(TAG, "returning final value: " + retVal.size());
        return retVal;
    }
    private static ArrayList<LocationStamp> removeOldestLocation(ArrayList<LocationStamp> locationStamps){
        ArrayList<LocationStamp> retVal = new ArrayList<>();
        if (locationStamps==null||locationStamps.isEmpty()){
            Log.d(TAG, "invalid location stamp list");
        }else {
            LocationStamp oldestLocationStamp = null;
            long oldestStamp = -1;
            for (LocationStamp locationStamp : locationStamps){
                if (oldestLocationStamp==null){
                    oldestLocationStamp = locationStamp;
                }else {
                    if (locationStamp.getTimeStamp() < oldestStamp){
                        oldestLocationStamp = locationStamp;
                    }
                }
            }

            locationStamps.remove(oldestLocationStamp);
            retVal = locationStamps;
        }
        return retVal;
    }

}
