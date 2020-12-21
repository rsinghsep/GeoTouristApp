package com.staysilly.geotouristapp.viewmodels;

import com.staysilly.geotouristapp.models.Address;

import androidx.databinding.BindingConversion;

public class Converters {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**"+this.getClass().getSimpleName();
    private static final String EMPTY_STRING = "";

    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    @BindingConversion
    public static String getReadableAddress(Address address){
        if (address==null){
            return EMPTY_STRING;
        }

        return address.getAddress();
    }

}
