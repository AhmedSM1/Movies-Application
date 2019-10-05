package com.example.movies.popularmovies.UI;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.movies.popularmovies.API.Client;
import com.example.movies.popularmovies.API.Services;
import com.example.movies.popularmovies.Adapters.ReviewAdapter;
import com.example.movies.popularmovies.Adapters.TrailerAdapter;
import com.example.movies.popularmovies.Model.Movie;
import com.example.movies.popularmovies.Model.Review;
import com.example.movies.popularmovies.Model.Trailer;
import com.example.movies.popularmovies.Model.TrailerReply;
import com.example.movies.popularmovies.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = DetailsActivity.class.getSimpleName();
    private static final String API_KEY = "bf3311f677001ebb53bbbeffd6ac9a32";
    public static final String POSTER_PATH = "https://image.tmdb.org/t/p/w500";
    private Movie mCurrentMovie;
    List<Review> reviews= new ArrayList<>();
    List<Trailer> trailers= new ArrayList<>();
    Context context;
    boolean isFavourite;
    private ImageView star;
    private RecyclerView trailerRecyclerView, reviewRecyclerView;
    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;

    //real-time database
    String userName;
    String key;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference movieRef;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);


        mCurrentMovie = getIntent().getParcelableExtra("movie");
        userName = getIntent().getStringExtra("name");




       star = findViewById(R.id.favoriteStar);
       movieRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Log.d(TAG, "datasnapshot "+dataSnapshot.getChildrenCount());
               if (dataSnapshot.hasChild(key)){
                //   star.setImageResource(R.drawable.fav);
                   isFavourite = true;
               }else {
               //    star.setImageResource(R.drawable.unfav);
                   isFavourite = false;
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
               Log.d(TAG,""+databaseError.getMessage());
           }
       });







        star = findViewById(R.id.favoriteStar);

                      star.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              if (!isFavourite) {
                                  addMovie(mCurrentMovie);
                        //          star.setImageResource(R.drawable.fav);
                                  isFavourite = true;
                                  Toast.makeText(getApplicationContext(), "Added to My Favourites", Toast.LENGTH_SHORT).show();
                              } else {
                                  deleteFavorite();
                             //     star.setImageResource(R.drawable.unfav);
                                  isFavourite = false;
                                  Toast.makeText(getApplicationContext(), "Removed from My Favourites", Toast.LENGTH_SHORT).show();
                              }
                          }
                      });

        String movieReleaseDate = mCurrentMovie.getReleaseDate();
        TextView releaseDateTextView = findViewById(R.id.release);
        releaseDateTextView.setText(movieReleaseDate);
        String movieTitle = mCurrentMovie.getTitle();
        TextView titleTextView = findViewById(R.id.movieTitle);
        titleTextView.setText(movieTitle);
        ImageView posterImageView = findViewById(R.id.movieImage);
        Glide.with(this).load(POSTER_PATH+mCurrentMovie.getPosterPath()).into(posterImageView);
        TextView voteAverageTextView = findViewById(R.id.movieVote);
        voteAverageTextView.setText(String.valueOf(mCurrentMovie.getVoteAverage())+"/10");
        String overview = mCurrentMovie.getOverview();
        TextView overviewTextView = findViewById(R.id.movieOverview);
        overviewTextView.setText(overview);

   //  populateTrailers(mCurrentMovie.getId());
   //  populateReview(mCurrentMovie.getId());
    }




     /*  //getting the trailers
       private void populateTrailers(int movie_id){
        Client client = new Client();
        Services services = client.getClient().create(Services.class);
        trailers = new ArrayList<>();
        Call<TrailerReply> trailerCall;
        trailerCall = services.getTrailer(movie_id,API_KEY);
        trailerCall.enqueue(new Callback<TrailerReply>() {
            @Override
            public void onResponse(Call<TrailerReply> call, Response<TrailerReply> response) {
                TrailerReply trailerReply = response.body();
                trailers.clear();
                if (trailerReply != null) {
                    trailers.addAll(trailerReply.getResults());
                }
                trailerRecyclerView=findViewById(R.id.trailersRecyclerView);
                trailerRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
                trailerAdapter = new TrailerAdapter(getApplicationContext(), trailers);
                trailerRecyclerView.setAdapter(trailerAdapter);
                trailerAdapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<TrailerReply> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"This Movie doesnt have trailers",Toast.LENGTH_LONG).show();
            }
        });}*/

     /*  //getting The reviews
       private void populateReview(int movie_id){
        Client client = new Client();
        Services services = client.getClient().create(Services.class);
        reviews = new ArrayList<>();
        Call<ReviewReply> call;
        call = services.getReviwes(movie_id,API_KEY);
        call.enqueue(new Callback<ReviewReply>() {
            @Override
            public void onResponse(Call<ReviewReply> call, Response<ReviewReply> response) {
                List<Review> reviews = response.body().getReviews();
                reviewAdapter = new ReviewAdapter(getApplicationContext(),reviews);
                GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getApplicationContext(),1);
                RecyclerView recyclerView2 = findViewById(R.id.reviewRecyclerView);
                recyclerView2.setLayoutManager(gridLayoutManager2);
                recyclerView2.setAdapter(reviewAdapter);
                reviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ReviewReply> call, Throwable t) {
                Log.d("Error", "Failed to get a response");
                Toast.makeText(getApplicationContext(), "unable to fetch data",Toast.LENGTH_SHORT).show();
            }
        }); }*/

    //methods for adding and deleting movies
    private void addMovie(Movie movie){

        String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        movieRef =  database.getReference().child(UID).child(key) ;

        key = mCurrentMovie.getTitle();

        movieRef.push().setValue(movie);

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
