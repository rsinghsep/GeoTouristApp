package com.staysilly.geotouristapp.views.ui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.staysilly.geotouristapp.R;
import com.staysilly.geotouristapp.databinding.ActivityCreateTourBinding;
import com.staysilly.geotouristapp.viewmodels.CreateTourViewModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class CreateTourActivity extends BaseActivity implements OnMapReadyCallback {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**" + this.getClass().getSimpleName();
    private static final String EMPTY_STRING = "";
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
    private LatLng getCurrentLatLng() {
        LatLng retVal = null;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null) {
            return retVal;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return retVal;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        retVal = new LatLng(location.getLatitude(), location.getLongitude());

        return retVal;
    }
    private void setMarkerAt(LatLng latLng, int markerResourceId){
        MarkerOptions marker = new MarkerOptions().position(latLng).title("Hello Maps");
        // Changing marker icon
        marker.icon(BitmapDescriptorFactory.fromResource(markerResourceId));
        // adding marker
        googleMap.addMarker(marker);
    }
    private void zoomMapToCurrentLocation(GoogleMap googleMap) {
        if (googleMap == null) {
            Log.d(TAG, "Google map is null");
            return;
        }

        LatLng currentLatLang = getCurrentLatLng();
        Log.d(TAG, "moving camera to current position");
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(currentLatLang)      // Sets the center of the map to location user
                .zoom(15)                   // Sets the zoom
                .build();                   // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //set current marker
        setMarkerAt(currentLatLang, R.drawable.placeholder);
    }
    private String getAddressFromLatLong(LatLng latLng) {
        String retVal = EMPTY_STRING;
        if (latLng==null){
            return retVal;
        }

        // Get the location manager
        Geocoder gcd = new Geocoder(getBaseContext(),
                Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(latLng.latitude,
                    latLng.longitude, 1);
            if (addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                Log.d(TAG, "current address found");
                if (address != null) {
                    retVal = address;
                    Log.d(TAG, "Address : " + address);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return retVal;
    }
    private void setGoogleMapClickListener(GoogleMap map){
        if (googleMap==null){
            Log.d(TAG, "google map is null");
            return;
        }

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                String address = getAddressFromLatLong(latLng);
                setMarkerAt(latLng, R.drawable.placeholder);
                viewModel.setTourAddress(address, latLng);
                Log.d(TAG,"clicked at: " + address);
            }
        });
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
        String currentAddress = getAddressFromLatLong(getCurrentLatLng());
        viewModel.setCurrentAddress(currentAddress);
        setGoogleMapClickListener(map);
        Log.d(TAG, "current address : " + currentAddress);
    }

}