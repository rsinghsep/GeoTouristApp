package com.staysilly.geotouristapp.repositories;

import android.util.Log;

import com.staysilly.geotouristapp.models.Tour;

public class AppRepository {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**"+this.getClass().getSimpleName();
    private static AppRepository sAppRepository;


    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    public static AppRepository getInstance(){
        return sAppRepository == null ? sAppRepository = new AppRepository() : sAppRepository;
    }


    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    public void saveTour(Tour tour){
        Log.d(TAG, "save tour begins");
        if (tour==null){
            Log.d(TAG, "invalid tour can not be saved");
            return;
        }

        // TODO: 12/21/20
    }


    /*/////////////////////////////////////////////////
    //CONSTRUCTOR
    /*/////////////////////////////////////////////////
    private AppRepository(){}

}
