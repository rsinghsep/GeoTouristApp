package com.staysilly.geotouristapp.views.ui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.staysilly.geotouristapp.R;
import com.staysilly.geotouristapp.databinding.ActivityCreateTourBinding;
import com.staysilly.geotouristapp.viewmodels.CreateTourViewModel;

import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class CreateTourActivity extends BaseActivity implements OnMapReadyCallback {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**" + this.getClass().getSimpleName();
    private ActivityCreateTourBinding datBinding;
    private CreateTourViewModel viewModel;
    private GoogleMap googleMap;


    /*/////////////////////////////////////////////////
    //PRIVATE METHODS
    /*/////////////////////////////////////////////////
    private void initDataBinding() {
        datBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_tour);
        viewModel = ViewModelProviders.of(this).get(CreateTourViewModel.class);
        datBinding.setViewModel(viewModel);
        datBinding.setLifecycleOwner(this);
    }
    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    private void zoomMapToCurrentLocation(GoogleMap googleMap) {
        if (googleMap == null) {
            Log.d(TAG, "Google map is null");
            return;
        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null){
            Log.d(TAG, "moving camera to curent position");
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));
        }
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
        zoomMapToCurrentLocation(map);
    }

}