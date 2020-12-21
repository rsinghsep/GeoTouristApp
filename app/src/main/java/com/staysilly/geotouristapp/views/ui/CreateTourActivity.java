package com.staysilly.geotouristapp.views.ui;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.staysilly.geotouristapp.R;
import com.staysilly.geotouristapp.databinding.ActivityCreateTourBinding;
import com.staysilly.geotouristapp.viewmodels.CreateTourViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class CreateTourActivity extends BaseActivity implements OnMapReadyCallback {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**"+this.getClass().getSimpleName();
    private ActivityCreateTourBinding datBinding;
    private CreateTourViewModel viewModel;
    private GoogleMap googleMap;


    /*/////////////////////////////////////////////////
    //PRIVATE METHODS
    /*/////////////////////////////////////////////////
    private void initDataBinding(){
        datBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_tour);
        viewModel = ViewModelProviders.of(this).get(CreateTourViewModel.class);
        datBinding.setViewModel(viewModel);
        datBinding.setLifecycleOwner(this);
    }
    private void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /*/////////////////////////////////////////////////
    //LIFECYCLE METHODS
    /*/////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        initMap();
    }


    /*/////////////////////////////////////////////////
    //OnMapReadyCallback Callbacks
    /*/////////////////////////////////////////////////
    @Override
    public void onMapReady(GoogleMap map) {
        Log.d(TAG, "googleMap is ready");
        googleMap = map;
    }

}