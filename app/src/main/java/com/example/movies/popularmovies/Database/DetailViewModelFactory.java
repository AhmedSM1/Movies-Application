package com.example.movies.popularmovies.Database;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailViewModelFactory extends ViewModelProvider.NewInstanceFactory  {
    String userID;
    int key;

    public DetailViewModelFactory(String userID, int key) {
        this.userID = userID;
        this.key = key;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailMovieViewModel(userID,key);
    }
}
