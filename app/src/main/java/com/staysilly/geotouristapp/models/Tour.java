package com.staysilly.geotouristapp.models;

public class Tour {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = this.getClass().getSimpleName();
    private String tourName;
    private Address startAddress;
    private Address DestinationAddress;


    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    public String getTourName() {
        return tourName;
    }
    public void setTourName(String tourName) {
        this.tourName = tourName;
    }
    public Address getStartAddress() {
        return startAddress;
    }
    public void setStartAddress(Address startAddress) {
        this.startAddress = startAddress;
    }
    public Address getDestinationAddress() {
        return DestinationAddress;
    }
    public void setDestinationAddress(Address destinationAddress) {
        DestinationAddress = destinationAddress;
    }

}
