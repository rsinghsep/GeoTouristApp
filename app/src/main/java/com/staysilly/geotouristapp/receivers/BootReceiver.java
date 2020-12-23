package com.staysilly.geotouristapp.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.staysilly.geotouristapp.services.JobUtils;

public class BootReceiver extends BroadcastReceiver {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**"+this.getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "boot complete");
        JobUtils.scheduleLocationTrackingJob(context);
    }
}
