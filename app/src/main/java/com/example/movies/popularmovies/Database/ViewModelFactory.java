package com.example.movies.popularmovies.Database;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;



public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    String mUserID;

    public ViewModelFactory(String mUserID) {
        this.mUserID = mUserID;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieViewModel(mUserID);
    }
}
