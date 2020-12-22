package com.staysilly.geotouristapp.services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

class JobUtils {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**JobUtils";
    private static final int ONE_SECOND_IN_MS = 1000;
    private static final int ONE_MINUTE_IN_MS = ONE_SECOND_IN_MS*60;


    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void scheduleLocationTrackingJob(Context context){
        ComponentName componentName = new ComponentName(context, TrackerJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, componentName);
        builder.setMinimumLatency(13*ONE_MINUTE_IN_MS);
        builder.setOverrideDeadline(17*ONE_MINUTE_IN_MS);
        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        scheduler.schedule(builder.build());
    }

}
