package com.staysilly.geotouristapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

public class CreateTourViewModel extends BaseViewModel {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = this.getClass().getSimpleName();
    private boolean isStartingPointSet = false;
    public MutableLiveData<String> currentAddress = new MutableLiveData<>();
    public MutableLiveData<String> startingPoint = new MutableLiveData<>();
    public MutableLiveData<String> destination = new MutableLiveData<>();

    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    public void setTourAddress(String address){
        Log.d(TAG, "setTourAddress begins");
        if (address==null || address.isEmpty()){
            Log.d(TAG, "invalid address");
            return;
        }

        if (isStartingPointSet){
            destination.setValue(address);
        }else {
            startingPoint.setValue(address);
            isStartingPointSet = true;
        }
    }
    public void setCurrentAddress(String address){
        Log.d(TAG, "setTourAddress begins");
        if (address==null || address.isEmpty()){
            Log.d(TAG, "invalid address");
            return;
        }

        currentAddress.setValue(address);
    }

}
