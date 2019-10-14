package com.example.movies.popularmovies.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;



public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    String mUserID;
    Context mContext;
    public ViewModelFactory(String mUserID,Context context) {
        this.mUserID = mUserID;
        this.mContext = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieViewModel(mContext,mUserID);
    }
}
