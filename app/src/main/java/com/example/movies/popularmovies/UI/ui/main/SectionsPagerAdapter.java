package com.example.movies.popularmovies.UI.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.movies.popularmovies.Fragments.MovieDescirption;
import com.example.movies.popularmovies.Fragments.ReviewsFragment;
import com.example.movies.popularmovies.Fragments.TrailerFragment;
import com.example.movies.popularmovies.Model.Movie;
import com.example.movies.popularmovies.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Movie movie;
    private  int movieID;
    private final Context mContext;


    public SectionsPagerAdapter(Context context, FragmentManager fm, Movie movie) {
        super(fm);
        mContext = context;
        this.movie = movie;
        this.movieID = movie.getId();

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
       Fragment fragment = null;
       switch (position){
           case 0:
               fragment = MovieDescirption.newInstance(movie);
               break;
           case 1:
               fragment = ReviewsFragment.newInstance(movieID);
               break;
           case 2:
               fragment = TrailerFragment.newInstance(movieID);
               break;
       }
       return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
         switch (position){
             case 0:
                 return mContext.getString(R.string.tab_movie);
             case 1:
                 return mContext.getString(R.string.tab_review);
             case 2:
                 return mContext.getString(R.string.tab_trailer);

         }
         return null;


    }

    @Override
    public int getCount() {
        return 3;
    }
}