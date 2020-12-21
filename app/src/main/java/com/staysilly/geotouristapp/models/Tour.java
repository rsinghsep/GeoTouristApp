package com.staysilly.geotouristapp.models;

public class Tour {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = this.getClass().getSimpleName();
    private String tourName;
    private Address startAddress;
    private Address destinationAddress;


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
        return destinationAddress;
    }
    public void setDestinationAddress(Address destinationAddress) {
        destinationAddress = destinationAddress;
    }


    /*/////////////////////////////////////////////////
    //CONSTRUCTOR
    /*/////////////////////////////////////////////////
    public Tour(String tourName, Address startAddress, Address destinationAddress){
        this.tourName = tourName;
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
    }

}
