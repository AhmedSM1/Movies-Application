package com.example.movies.popularmovies.Database;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;



public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    String mUserName;

    public ViewModelFactory(String mUserName) {
        this.mUserName = mUserName;


    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieViewModel(mUserName);
    }
}
