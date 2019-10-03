package com.example.movies.popularmovies.UI;

import android.os.Bundle;

import com.example.movies.popularmovies.Model.Movie;
import com.example.movies.popularmovies.R;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.ActionBar;
import androidx.core.app.NavUtils;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movies.popularmovies.UI.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Movie_Details extends AppCompatActivity   {
    public Movie mCurrentMovie;
    public String userName;
    String key;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference movieRef;
    private FirebaseAuth mFirebaseAuth;





    private static final String API_KEY = "bf3311f677001ebb53bbbeffd6ac9a32";
    public static final String POSTER_PATH = "https://image.tmdb.org/t/p/w500";
    public static final String MOVIE_KEY = "movie";
    public static final String UID_KEY = "name ";
    public static final String TAG = Movie_Details.class.getName();
    TextView mTitle;
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
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

        //methods for adding and deleting movies
        private void addMovie(Movie movie){
        movieRef.child(key).push().setValue(movie);
        Toast.makeText(getApplicationContext(), "movie have been added  "+movie.getTitle(),Toast.LENGTH_SHORT).show();
        Log.d(TAG,"key of the movie is"+key);
    }

    private void deleteFavorite(){
        movieRef.child(key).removeValue();
        Log.d(TAG,"key of the movie is"+key);
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

}