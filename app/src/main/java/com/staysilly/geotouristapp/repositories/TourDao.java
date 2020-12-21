package com.staysilly.geotouristapp.repositories;

import com.staysilly.geotouristapp.models.Tour;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TourDao {

    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    @Insert
    void insertTour(Tour tour);
    @Query("SELECT * FROM tour_table")
    LiveData<List<Tour>> getAllTours();

}