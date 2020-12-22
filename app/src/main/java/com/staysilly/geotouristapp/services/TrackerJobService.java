package com.staysilly.geotouristapp.services;

import android.Manifest;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.staysilly.geotouristapp.models.LocationStamp;
import com.staysilly.geotouristapp.utils.FileUtils;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TrackerJobService extends JobService {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**"+this.getClass().getSimpleName();


    /*/////////////////////////////////////////////////
    //LIFECYCLE METHODS
    /*/////////////////////////////////////////////////
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "onStartJob");
        JobUtils.scheduleLocationTrackingJob(getApplicationContext());
        LatLng currentLatLng = getCurrentLatLng();
        if (currentLatLng==null){
            return true;
        }
        String displayMessage = "latitude: " + currentLatLng.latitude +  " longitude: " + currentLatLng.longitude + System.currentTimeMillis();
        Toast.makeText(this, displayMessage, Toast.LENGTH_LONG).show();
        LocationStamp locationStamp = new LocationStamp(currentLatLng.latitude, currentLatLng.longitude, System.currentTimeMillis());
        saveLocationInFile(this, locationStamp);
        return true;
    }
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "onStopJob");
        return false;
    }


    /*/////////////////////////////////////////////////
    //PRIVATE METHODS
    /*/////////////////////////////////////////////////
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

        if (location!=null){
            retVal = new LatLng(location.getLatitude(), location.getLongitude());
        }

        return retVal;
    }
    private void saveLocationInFile(Context context, LocationStamp locationStamp){
        Log.d(TAG, "going to save location");
        FileUtils.writeLocationUpdate(context, locationStamp);
    }

}
