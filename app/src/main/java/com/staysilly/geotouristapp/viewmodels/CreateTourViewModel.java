package com.staysilly.geotouristapp.viewmodels;

import androidx.lifecycle.MutableLiveData;

public class CreateTourViewModel extends BaseViewModel {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = this.getClass().getSimpleName();
    public MutableLiveData<String> currentAddress = new MutableLiveData<>();
    public MutableLiveData<String> startingPoint = new MutableLiveData<>();
    public MutableLiveData<String> destination = new MutableLiveData<>();

    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////

}
