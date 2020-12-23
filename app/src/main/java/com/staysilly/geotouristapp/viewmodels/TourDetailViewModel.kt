package com.staysilly.geotouristapp.viewmodels;

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.staysilly.geotouristapp.models.Tour
import com.staysilly.geotouristapp.repositories.AppRepository

open class TourDetailViewModel : ViewModel() {

    //member
    val TAG = "TourDetailViewModel"

    //public
    public fun setTourDetail(tour : Tour){

    }
    fun getTourById(tourId : String) : LiveData<Tour> {
        return AppRepository.getInstance().getTourById(tourId)
    }

}