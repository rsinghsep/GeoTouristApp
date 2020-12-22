package com.staysilly.geotouristapp.repositories;

import android.util.Log;

import com.staysilly.geotouristapp.MyApplication;
import com.staysilly.geotouristapp.models.Tour;

import java.util.List;

import androidx.lifecycle.LiveData;

public class AppRepository {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**"+this.getClass().getSimpleName();
    private static AppRepository sAppRepository;
    private LocalDB localDB;
    private TourDao tourDao;


    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    public static AppRepository getInstance(){
        return sAppRepository == null ? sAppRepository = new AppRepository() : sAppRepository;
    }


    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    public void saveTour(final Tour tour){
        Log.d(TAG, "save tour begins");
        if (tour==null){
            Log.d(TAG, "invalid tour can not be saved");
            return;
        }
        if (localDB==null){
            Log.d(TAG, "local database not found");
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "inserting tour in local DB");
                tourDao.insertTour(tour);
            }
        }).start();
    }
    public LiveData<List<Tour>> getAllTours(){
        return tourDao.getAllTours();
    }
    public LiveData<Tour> getTourById(String tourId){
        return tourDao.getTourById(tourId);
    }


    /*/////////////////////////////////////////////////
    //CONSTRUCTOR
    /*/////////////////////////////////////////////////
    private AppRepository(){
        localDB = MyApplication.getLocalDB();
        if (tourDao==null){
            tourDao=localDB.tourDao();
        }
    }

}
