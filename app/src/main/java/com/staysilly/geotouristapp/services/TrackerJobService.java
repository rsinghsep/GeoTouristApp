package com.staysilly.geotouristapp.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
class TrackerJobService extends JobService {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = this.getClass().getSimpleName();


    /*/////////////////////////////////////////////////
    //LIFECYCLE METHODS
    /*/////////////////////////////////////////////////
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        return false;
    }
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

}
