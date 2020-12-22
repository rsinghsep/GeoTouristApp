package com.staysilly.geotouristapp;

import android.app.Application;
import android.util.Log;

import com.staysilly.geotouristapp.repositories.LocalDB;

public class MyApplication extends Application {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = this.getClass().getSimpleName();
    private static LocalDB localDB;


    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    public static LocalDB getLocalDB(){
        return localDB;
    }


    /*/////////////////////////////////////////////////
    //LIFECYCLE METHODS
    /*/////////////////////////////////////////////////
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "application created");
        localDB = LocalDB.getInstance(this);
    }

}
