package com.example.movies.popularmovies.Database;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SingleMovieQueryLiveData extends LiveData<DataSnapshot> {
    private final Query query;
    public static final String TAG = SingleMovieQueryLiveData.class.getName();
    private final SingleValueEventListener listener = new SingleValueEventListener();



    public SingleMovieQueryLiveData(Query query) {
        this.query = query;
    }

    public SingleMovieQueryLiveData(DatabaseReference reference) {
        this.query = reference;
    }

    @Override
    protected void onActive() {
        query.addListenerForSingleValueEvent(listener);
    }


    @Override
    protected void onInactive() {
        Log.d(TAG, "onInactive");
        if (query != null && listener != null) {
            query.removeEventListener(listener);
        }
    }

    class SingleValueEventListener implements ValueEventListener{

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
         if (dataSnapshot.exists()){
             setValue(dataSnapshot);
         }
            Log.d(TAG, "onDataChange:  "+dataSnapshot.toString());
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
           Log.d(TAG,"Error = "+databaseError.getMessage());
        }
    }
}
