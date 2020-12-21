package com.staysilly.geotouristapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "address_table",
        indices = {@Index(value = {"addressId"})})
public class Address {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    @ColumnInfo(name = "addressId")
    @PrimaryKey(autoGenerate = true)
    private int addressId;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "latitude")
    private double latitude;
    @ColumnInfo(name = "longitude")
    private double longitude;


    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    public int getAddressId() {
        return addressId;
    }
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    /*/////////////////////////////////////////////////
    //CONSTRUCTOR
    /*/////////////////////////////////////////////////
    public Address(long latitude, long longitude, String streetAddress){
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = streetAddress;
    }
    public Address(){}

}
