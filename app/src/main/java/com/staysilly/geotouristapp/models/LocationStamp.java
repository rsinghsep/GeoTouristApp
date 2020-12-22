package com.staysilly.geotouristapp.models;

public class LocationStamp {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private double lat;
    private double lng;
    private long timeStamp;


    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLng() {
        return lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
    public long getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }


    /*/////////////////////////////////////////////////
    //CONSTRUCTOR
    /*/////////////////////////////////////////////////
    public LocationStamp(double latitude, double longitude, long timeInMS){
        lat = latitude;
        lng = longitude;
        timeStamp = timeInMS;
    }
    public LocationStamp(){}

}
