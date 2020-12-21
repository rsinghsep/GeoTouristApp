package com.staysilly.geotouristapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "tour_table",
        indices = {@Index(value = {"tourId"})})
public class Tour {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    @ColumnInfo(name = "tourId")
    @PrimaryKey(autoGenerate = true)
    private int tourId;
    @ColumnInfo(name = "tourName")
    private String tourName;
    @ColumnInfo(name = "startAddress")
    private Address startAddress;
    @ColumnInfo(name = "destinationAddress")
    private Address destinationAddress;


    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    public int getTourId() {
        return tourId;
    }
    public void setTourId(int tourId) {
        this.tourId = tourId;
    }
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
        this.destinationAddress = destinationAddress;
    }


    /*/////////////////////////////////////////////////
    //CONSTRUCTOR
    /*/////////////////////////////////////////////////
    public Tour(String tourName, Address startAddress, Address destinationAddress){
        this.tourName = tourName;
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
    }
    public Tour(){}

}
