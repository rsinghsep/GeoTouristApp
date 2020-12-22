package com.staysilly.geotouristapp.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.staysilly.geotouristapp.models.Address;

import java.lang.reflect.Type;
import java.util.ArrayList;

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
    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

}
