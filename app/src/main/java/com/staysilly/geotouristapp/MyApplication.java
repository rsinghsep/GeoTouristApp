package com.staysilly.geotouristapp;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = this.getClass().getSimpleName();


    /*/////////////////////////////////////////////////
    //LIFECYCLE METHODS
    /*/////////////////////////////////////////////////
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "application created");
    }

}
