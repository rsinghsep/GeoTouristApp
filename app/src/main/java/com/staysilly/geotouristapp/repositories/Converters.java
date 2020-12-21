package com.staysilly.geotouristapp.repositories;

import com.google.gson.Gson;
import com.staysilly.geotouristapp.models.Address;

import androidx.room.TypeConverter;

public class Converters {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = this.getClass().getSimpleName();
    private Gson gson = null;


    /*/////////////////////////////////////////////////
    //PRIVATE METHODS
    /*/////////////////////////////////////////////////
    private Gson getGson(){
        return gson == null ? gson = new Gson() : gson;
    }


    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    @TypeConverter
    public String getAddressJson(Address address){
        return getGson().toJson(address);
    }
    @TypeConverter
    public Address jsonToAddress(String address){
        return getGson().fromJson(address, Address.class);
    }

}
