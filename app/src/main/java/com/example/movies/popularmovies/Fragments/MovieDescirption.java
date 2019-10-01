package com.example.movies.popularmovies.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movies.popularmovies.Model.Movie;
import com.example.movies.popularmovies.R;


public class MovieDescirption extends Fragment {
    public static final String ARG_MOVIE = "MOVIE";
    public static final String POSTER_PATH = "https://image.tmdb.org/t/p/w500";

    public Movie mCurrentMovie;


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

        if (getArguments() != null) {
            mCurrentMovie = getArguments().getParcelable(ARG_MOVIE);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_descirption, container, false);


        String movieReleaseDate = mCurrentMovie.getReleaseDate();
        TextView releaseDateTextView = view.findViewById(R.id.release);
        releaseDateTextView.setText(movieReleaseDate);



        ImageView posterImageView = view.findViewById(R.id.movieImage);

        Glide.with(this).load(POSTER_PATH+mCurrentMovie.getPosterPath()).into(posterImageView);

        String overview = mCurrentMovie.getOverview();
        TextView overviewTextView = view.findViewById(R.id.movieOverview);
        overviewTextView.setText(overview);

        RatingBar ratingBar = view.findViewById(R.id.ratingBar);
        Float rating = ((float) mCurrentMovie.getVoteAverage());
        Float cal = (5 * rating) / 10;
        ratingBar.setRating(cal);


        return view;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}
