package com.staysilly.geotouristapp.viewmodels;

import com.staysilly.geotouristapp.models.Tour;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ToursListViewModel extends BaseViewModel {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = this.getClass().getSimpleName();


    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    public LiveData<List<Tour>> getAllTours(){
        return getRepository().getAllTours();
    }

}
