package com.staysilly.geotouristapp.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private static final String TAG = "**FileUtils";
    private static final String FILE_NAME_LOCATION_TRACKER = "location_tracker";


    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    public static void writeLocationUpdate(Context context, String content){
        if (context==null||content==null||content.isEmpty()){
            Log.d(TAG, "writeLocationUpdate");
            return;
        }

        try (FileOutputStream fos = context.openFileOutput(FILE_NAME_LOCATION_TRACKER, Context.MODE_PRIVATE)) {
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "IOException: " + e.getMessage());
        }
    }
    public static String getLocationFileUpdate(Context context){
        String retVal = "";
        try {
            FileInputStream fis = context.openFileInput(FILE_NAME_LOCATION_TRACKER);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append('\n');
                    line = reader.readLine();
                }
            } catch (IOException e) {
                // Error occurred when opening raw file for reading.
            } finally {
                retVal = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return retVal;
    }

}
