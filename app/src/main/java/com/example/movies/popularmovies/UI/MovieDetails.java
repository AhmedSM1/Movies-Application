package com.example.movies.popularmovies.UI;

import android.os.Bundle;

import com.example.movies.popularmovies.Model.Movie;
import com.example.movies.popularmovies.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.NavUtils;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.movies.popularmovies.UI.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MovieDetails extends AppCompatActivity   {
    public static final String TAG = MovieDetails.class.getName();
    public Movie mCurrentMovie;
    public static final String MOVIE_KEY = "movie";
    public static final String MOVIE_STATE = "movieState";
    public ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__details);
        //getting the intent
        mCurrentMovie = getIntent().getParcelableExtra(MOVIE_KEY);
        actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(mCurrentMovie.getTitle());
        }
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),mCurrentMovie);
        Log.d(TAG, "onCreate: section PagerAdapter "+sectionsPagerAdapter.toString());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        if (savedInstanceState != null){
            mCurrentMovie = savedInstanceState.getParcelable(MOVIE_STATE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (getSupportFragmentManager().popBackStackImmediate()){
            return true;
        }

        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MOVIE_STATE,mCurrentMovie);

    }
}