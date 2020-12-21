package com.staysilly.geotouristapp.viewmodels;

import com.staysilly.geotouristapp.repositories.AppRepository;

import androidx.lifecycle.ViewModel;

class BaseViewModel extends ViewModel {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = this.getClass().getSimpleName();


    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    public AppRepository getRepository(){
        return AppRepository.getInstance();
    }

}
