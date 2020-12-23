package com.staysilly.geotouristapp.views.ui;

import android.os.Bundle;
import android.util.Log;

import com.staysilly.geotouristapp.R;
import com.staysilly.geotouristapp.databinding.ActivityLocationHistoryBinding;
import com.staysilly.geotouristapp.models.LocationStamp;
import com.staysilly.geotouristapp.utils.FileUtils;

import java.sql.Timestamp;
import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;

public class LocationHistoryActivity extends BaseActivity {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**"+this.getClass().getSimpleName();
    private ActivityLocationHistoryBinding dataBinding;


    /*/////////////////////////////////////////////////
    //PRIVATE METHODS
    /*/////////////////////////////////////////////////
    private void initDataBinding(){
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_location_history);
        dataBinding.setLifecycleOwner(this);
    }
    private void showLocationHistory(){
        ArrayList<LocationStamp> locationHistory = FileUtils.getAllSavedLocationFromFile(this);
        StringBuilder stringBuilder = new StringBuilder();
        for (LocationStamp locationStamp : locationHistory){
            String latitude = String.valueOf(locationStamp.getLat());
            String longitude = String.valueOf(locationStamp.getLng());
            String time = String.valueOf(new Timestamp(locationStamp.getTimeStamp()));

            stringBuilder
                    .append("Latitude : ")
                    .append(latitude)
                    .append("\n")
                    .append("Longitude : ")
                    .append(longitude)
                    .append("\n")
                    .append("TimeStamp: ")
                    .append(time)
                    .append("\n")
                    .append("\n");
        }

        Log.d(TAG, "location history: " + stringBuilder.toString());
        dataBinding.setLocationHistory(stringBuilder.toString());
    }


    /*/////////////////////////////////////////////////
    //LIFECYCLE METHODS
    /*/////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        showLocationHistory();
    }

}