package com.example.movies.popularmovies.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies.popularmovies.Model.Movie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailMovieViewModel extends ViewModel {

    MutableLiveData<Movie> movie = new MutableLiveData<>();
    public static final String TAG = DetailMovieViewModel.class.getName();
    //real-time database

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference movieRef;
    private SingleMovieQueryLiveData liveData;
    String userID;
    int key;


    public DetailMovieViewModel(String userID,int key) {
          this.movieRef = database.getReference().child(userID).child(String.valueOf(key));
          this.liveData = new SingleMovieQueryLiveData(movieRef);
    }

    public SingleMovieQueryLiveData getLiveData() {
        return liveData;
    }
}






