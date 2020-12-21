package com.staysilly.geotouristapp.viewmodels;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.staysilly.geotouristapp.models.Address;
import com.staysilly.geotouristapp.models.Tour;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CreateTourViewModel extends BaseViewModel {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**"+this.getClass().getSimpleName();
    private boolean isStartingPointSet = false;
    public MutableLiveData<String> tourName = new MutableLiveData<>();
    public MutableLiveData<Boolean> isTourReadyToSave = new MutableLiveData<>();
    public MutableLiveData<String> currentAddress = new MutableLiveData<>();
    public MutableLiveData<String> startingAddress = new MutableLiveData<>();
    public MutableLiveData<String> destinationAddress = new MutableLiveData<>();
    private long startLat;
    private long startLng;
    private long destinationLat;
    private long destinationLng;


    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    public void setTourAddress(String address, LatLng latLng){
        Log.d(TAG, "setTourAddress begins");
        if (address==null || address.isEmpty()){
            Log.d(TAG, "invalid address");
            return;
        }

        if (!isStartingPointSet){
            startingAddress.setValue(address);
            isStartingPointSet = true;
            if (latLng!=null){
                startLat = (long) latLng.latitude;
                startLng = (long) latLng.longitude;
            }
        }else {
            destinationAddress.setValue(address);
            isTourReadyToSave.setValue(true);
            if (latLng!=null){
                destinationLat = (long) latLng.latitude;
                destinationLng = (long) latLng.longitude;
            }
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
    public void saveTour(){
        Log.d(TAG, "user requested save tour");
        Address startAddress = new Address(startLng, startLat, startingAddress.getValue());
        Address endAddress = new Address(destinationLat, destinationLng, destinationAddress.getValue());
        Tour tour = new Tour(tourName.getValue(), startAddress, endAddress);
        getRepository().saveTour(tour);
    }
    public LiveData<List<Tour>> getAllTour(){
        return getRepository().getAllTours();
    }

}
