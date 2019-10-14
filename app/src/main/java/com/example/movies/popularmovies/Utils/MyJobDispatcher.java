package com.example.movies.popularmovies.Utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.movies.popularmovies.Database.AppExecutors;
import com.example.movies.popularmovies.R;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class MyJobDispatcher extends JobService {
    public static final String TAG = MyJobDispatcher.class.getName();
    @Override
    public boolean onStartJob(@NonNull JobParameters job) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                //notifications
                Log.d(TAG, "startJob");
                NotificationSender.myNotification(getApplicationContext(),getString(R.string.notification_title),getString(R.string.notification_body));
            }
        });
        return true;
    }


    @Override
    public boolean onStopJob(@NonNull JobParameters job) {
        Log.d(TAG, "onStopJob: ");
        NotificationSender.clearNotifications(getApplicationContext());
        return false;
    }
}
