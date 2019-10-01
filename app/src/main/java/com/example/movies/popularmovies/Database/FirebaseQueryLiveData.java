package com.example.movies.popularmovies.Database;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class FirebaseQueryLiveData extends LiveData<DataSnapshot> {
    

    private static final String LOG_TAG = "FirebaseQueryLiveData";
    public static final String TAG = MyValueEventListener.class.getName();
    private final Query query;
    private final MyValueEventListener listener = new MyValueEventListener();


    public FirebaseQueryLiveData(Query query) {
        this.query = query;
    }

    public FirebaseQueryLiveData(DatabaseReference ref) {
        this.query = ref;
    }

    @Override
    protected void onActive() {
        Log.d(LOG_TAG, "onActive");
        query.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        Log.d(LOG_TAG, "onInactive");
        if (query != null && listener != null) {
            query.removeEventListener(listener);
        }
    }


    private class MyValueEventListener implements ValueEventListener {


        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(dataSnapshot);
            Log.d(TAG, "onDataChange: "+ dataSnapshot.toString());
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
             Log.d(TAG,"Error "+databaseError.getMessage() );
        }
    }
    }
