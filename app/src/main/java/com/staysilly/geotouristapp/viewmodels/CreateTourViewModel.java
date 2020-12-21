package com.staysilly.geotouristapp.viewmodels;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.staysilly.geotouristapp.models.Address;
import com.staysilly.geotouristapp.models.Tour;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class CreateTourViewModel extends BaseViewModel {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**"+this.getClass().getSimpleName();
    private static final String EMPTY_STRING = "";
    private boolean isStartingPointSet = false;
    public MutableLiveData<String> tourName = new MutableLiveData<>();
    public MutableLiveData<Boolean> isTourReadyToSave = new MutableLiveData<>();
    public MutableLiveData<String> currentAddress = new MutableLiveData<>();
    public MutableLiveData<String> startingAddress = new MutableLiveData<>();
    public MutableLiveData<String> destinationAddress = new MutableLiveData<>();
    public MutableLiveData<Boolean> signalShowInvalidNameToast = new MutableLiveData<>();
    public MutableLiveData<Boolean> signalSuccessTourSaved = new MutableLiveData<>();
    public MutableLiveData<Boolean> signalOpenGallery = new MutableLiveData<>();
    private long startLat;
    private long startLng;
    private long destinationLat;
    private long destinationLng;
    private List<String> mediaPathList = new ArrayList<>();


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
        String tourName = this.tourName.getValue();
        if (tourName==null||tourName.isEmpty()){
            signalShowInvalidNameToast.postValue(true);
            return;
        }

        Address startAddress = new Address(startLng, startLat, startingAddress.getValue());
        Log.d(TAG, "address: " + startingAddress.getValue());
        Log.d(TAG, "latitude: " + startLat);
        Log.d(TAG, "longitude: " + startLng);

        Address endAddress = new Address(destinationLat, destinationLng, destinationAddress.getValue());
        Log.d(TAG, "destination address: " + destinationAddress.getValue());
        Log.d(TAG, "destination latitude: " + destinationLat);
        Log.d(TAG, "destination longitude: " + destinationLng);
        Log.d(TAG, "total media: " + mediaPathList.size());
        Tour tour = new Tour(tourName, startAddress, endAddress);
        getRepository().saveTour(tour);
        clearScreen();
        signalSuccessTourSaved.postValue(true);
    }
    public void setTourMediaList(List<String> mediaList){
        if (mediaList==null||mediaList.isEmpty()){
            Log.d(TAG, "no media found");
            return;
        }

        mediaPathList.addAll(mediaList);
    }
    public void openGallery(){
        signalOpenGallery.postValue(true);
    }
    private void clearScreen(){
        this.tourName.setValue(EMPTY_STRING);
        this.startingAddress.setValue(EMPTY_STRING);
        this.destinationAddress.setValue(EMPTY_STRING);
        startLat = startLng = destinationLat = destinationLng = 0;
        isStartingPointSet = false;
        isTourReadyToSave.postValue(false);
    }

}
