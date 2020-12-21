package com.staysilly.geotouristapp.models;

import java.util.ArrayList;

import androidx.annotation.NonNull;
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
    @PrimaryKey
    @NonNull
    private String tourId;
    @ColumnInfo(name = "tourName")
    private String tourName;
    @ColumnInfo(name = "startAddress")
    private Address startAddress;
    @ColumnInfo(name = "destinationAddress")
    private Address destinationAddress;
    @ColumnInfo(name = "tourMediaPathList")
    private ArrayList<String> tourMediaPathList;


    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    public String getTourId() {
        return tourId;
    }
    public void setTourId(String tourId) {
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
    public ArrayList<String> getTourMediaPathList() {
        return this.tourMediaPathList;
    }
    public void setTourMediaPathList(ArrayList<String> tourMediaPathList) {
        this.tourMediaPathList = tourMediaPathList;
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
