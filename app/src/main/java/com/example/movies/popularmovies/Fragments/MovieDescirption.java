package com.example.movies.popularmovies.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movies.popularmovies.Database.DetailMovieViewModel;
import com.example.movies.popularmovies.Database.DetailViewModelFactory;
import com.example.movies.popularmovies.Database.MovieViewModel;
import com.example.movies.popularmovies.Database.ViewModelFactory;
import com.example.movies.popularmovies.Model.Movie;
import com.example.movies.popularmovies.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MovieDescirption extends Fragment {
    public static final String ARG_MOVIE = "MOVIE";
    public static final String POSTER_PATH = "https://image.tmdb.org/t/p/w500";
    public static final String TAG = MovieDescirption.class.getName();
    public Movie mCurrentMovie;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference movieRef;
    private FirebaseAuth mFirebaseAuth;
    String userID;
    int key;
    private FloatingActionButton hart;
    String movieReleaseDate;
    String title;
    Float cal;
    String overview;
    String moviePoster;
    //onSave instant state
    public static final String IS_FAVORITE = "isFavorite";
    public static final String TITLE = "title";
    public static final String OVERVIEW = "overview";
    public static final String CAL = "cal";
    public static final String USER_ID = "userID";
    public static final String RELEASE_DATE = "releaseDate";
    public static final String KEY="key";
    public static final String CURRENT_MOVIE_POSTER = "moviePoster";


    boolean isFavourite;
    public MovieDescirption() {
    }

    public static MovieDescirption newInstance(Movie movie) {
        MovieDescirption fragment = new MovieDescirption();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE,movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            mCurrentMovie = getArguments().getParcelable(ARG_MOVIE);
            key = mCurrentMovie.getId();
        }


        userID = mFirebaseAuth.getInstance().getUid();
        movieRef = database.getReference().child(userID).child(String.valueOf(key));

        final DetailViewModelFactory factory = new DetailViewModelFactory(userID,key);
        DetailMovieViewModel model = ViewModelProviders.of(this,factory).get(DetailMovieViewModel.class);
        if (!model.getLiveData().hasActiveObservers() ) model.getLiveData().observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(DataSnapshot dataSnapshot) {

                Log.d(TAG, "datasnapshot "+dataSnapshot.toString());
                Log.d(TAG, "onDataChange: key == "+key);
                Log.d(TAG, "onDataChange: movie ref == "+movieRef.toString());
                if (dataSnapshot.exists()){

                    Log.d(TAG,"Movie ====> exists");
                    hart.setImageResource(R.drawable.ic_favorite_black_24dp);
                    isFavourite = true;

                }else {
                    Log.d(TAG,"Movie ====> does not exists");
                    hart.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    isFavourite = false;

                }
            }
        });





         title = mCurrentMovie.getTitle();
         overview = mCurrentMovie.getOverview();
         movieReleaseDate = mCurrentMovie.getReleaseDate();
         moviePoster = POSTER_PATH+mCurrentMovie.getPosterPath();
        Float rating = ((float) mCurrentMovie.getVoteAverage());
        cal = (5 * rating) / 10;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_descirption, container, false);

         movieReleaseDate = mCurrentMovie.getReleaseDate();
        TextView releaseDateTextView = view.findViewById(R.id.release);
        releaseDateTextView.setText(movieReleaseDate);

        ImageView posterImageView = view.findViewById(R.id.image_poster);

      Glide.with(this).load(moviePoster).into(posterImageView);


      //   title = mCurrentMovie.getTitle();
        TextView movieTitle = view.findViewById(R.id.title);
        movieTitle.setText(title);

      //  overview = mCurrentMovie.getOverview();
        TextView overviewTextView = view.findViewById(R.id.plot);
        overviewTextView.setText(overview);



        RatingBar ratingBar = view.findViewById(R.id.ratingbar);
        ratingBar.setIsIndicator(true);
        ratingBar.setRating(cal);


        hart = view.findViewById(R.id.fab_detail);
        Log.d(TAG, "onCreateView: IsFavorite is  "+isFavourite);

        if (isFavourite == true){
            hart.setImageResource(R.drawable.ic_favorite_black_24dp);
        }else {
            hart.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }




        hart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFavourite) {
                    addMovie(mCurrentMovie);
                    hart.setImageResource(R.drawable.ic_favorite_black_24dp);
                    isFavourite = true;
                    Toast.makeText(getActivity().getApplicationContext(), R.string.add_to_fav, Toast.LENGTH_SHORT).show();
                } else {
                    deleteFavorite();
                    hart.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    isFavourite = false;
                    Toast.makeText(getContext().getApplicationContext(), R.string.removed_from_fav, Toast.LENGTH_SHORT).show();
                }
            }
        });


        if (savedInstanceState!= null){
         savedInstanceState.getString(TITLE);
         savedInstanceState.getInt(KEY);
         savedInstanceState.getBoolean(IS_FAVORITE);
         savedInstanceState.getString(OVERVIEW);
         savedInstanceState.getString(RELEASE_DATE);
         savedInstanceState.getString(CURRENT_MOVIE_POSTER);
         savedInstanceState.getFloat(CAL);
         savedInstanceState.getString(USER_ID);
        }



        return view;

    }



    //methods for adding and deleting movies
    private void addMovie(Movie movie){
        movieRef.push().setValue(movie);
        Toast.makeText(getContext(), R.string.added_movie+movie.getTitle(),Toast.LENGTH_SHORT).show();
        Log.d(TAG,"key of the movie is"+key);
    }

    private void deleteFavorite(){

        movieRef.removeValue();
        Log.d(TAG,"key of the movie is"+key);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_FAVORITE,isFavourite);
        outState.putString(TITLE,title);
        outState.putString(OVERVIEW,overview);
        outState.putFloat(CAL,cal);
        outState.putString(USER_ID,userID);
        outState.putString(RELEASE_DATE,movieReleaseDate);
        outState.putInt(KEY,key);
        outState.putString(CURRENT_MOVIE_POSTER,moviePoster);

    }

}



