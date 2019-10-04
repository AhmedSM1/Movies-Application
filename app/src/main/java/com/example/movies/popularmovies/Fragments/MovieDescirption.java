package com.example.movies.popularmovies.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
    String key;
    private FloatingActionButton hart;
    boolean isFavourite;
    public MovieDescirption() {
    }

    public static MovieDescirption newInstance(Movie movie) {
        MovieDescirption fragment = new MovieDescirption();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE,movie);
      //  args.putString(ARG_USERID,userID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mCurrentMovie = getArguments().getParcelable(ARG_MOVIE);
            key = mCurrentMovie.getTitle();
        }

        userID = mFirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d(TAG,"The userID is ==>"+userID);

        movieRef = database.getReference().child(userID).child(key);
        movieRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(TAG, "datasnapshot "+dataSnapshot.getChildrenCount());

                if (dataSnapshot.hasChild(key)){
                    Log.d(TAG,"Movie ====> exists");
                    hart.setImageResource(R.drawable.ic_favorite_black_24dp);
                    isFavourite = true;
                    Log.d(TAG, "onCreateView: IsFavorite is  "+isFavourite);
                }else {
                    Log.d(TAG,"Movie ====> does not exists");
                    hart.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    isFavourite = false;
                    Log.d(TAG, "onCreateView: IsFavorite is  "+isFavourite);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG,""+databaseError.getMessage());
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_descirption, container, false);

        String movieReleaseDate = mCurrentMovie.getReleaseDate();
        TextView releaseDateTextView = view.findViewById(R.id.release);
        releaseDateTextView.setText(movieReleaseDate);

        ImageView posterImageView = view.findViewById(R.id.image_poster);

      Glide.with(this).load(POSTER_PATH+mCurrentMovie.getPosterPath()).into(posterImageView);


        String title = mCurrentMovie.getTitle();
        TextView movieTitle = view.findViewById(R.id.title);
        movieTitle.setText(title);

        String overview = mCurrentMovie.getOverview();
        TextView overviewTextView = view.findViewById(R.id.plot);
        overviewTextView.setText(overview);



        RatingBar ratingBar = view.findViewById(R.id.ratingbar);
        ratingBar.setIsIndicator(true);
        Float rating = ((float) mCurrentMovie.getVoteAverage());
        Log.d(TAG, "onCreateView: vote Average"+mCurrentMovie.getVoteAverage());
        Log.d(TAG, "onCreateView: movie overview"+mCurrentMovie.getOverview());
        Float cal = (5 * rating) / 10;
        ratingBar.setRating(cal);


        hart = view.findViewById(R.id.fab_detail);
        Log.d(TAG, "onCreateView: IsFavorite is  "+isFavourite);

        hart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFavourite) {
                    addMovie(mCurrentMovie);
                    hart.setImageResource(R.drawable.ic_favorite_black_24dp);
                    isFavourite = true;
                    Toast.makeText(getActivity().getApplicationContext(), "Added to My Favourites", Toast.LENGTH_SHORT).show();
                } else {
                    deleteFavorite();
                    hart.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    isFavourite = false;
                    Toast.makeText(getContext().getApplicationContext(), "Removed from My Favourites", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }



    //methods for adding and deleting movies
    private void addMovie(Movie movie){

        movieRef.push().setValue(movie);

        Toast.makeText(getContext(), "movie have been added  "+movie.getTitle(),Toast.LENGTH_SHORT).show();
        Log.d(TAG,"key of the movie is"+key);
    }

    private void deleteFavorite(){

        movieRef.child(key).removeValue();
        Log.d(TAG,"key of the movie is"+key);
    }



}
