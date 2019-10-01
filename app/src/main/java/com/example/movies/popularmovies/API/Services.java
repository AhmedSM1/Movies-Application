package com.example.movies.popularmovies.API;

import com.example.movies.popularmovies.Model.MoviesReply;
import com.example.movies.popularmovies.Model.ReviewReply;
import com.example.movies.popularmovies.Model.TrailerReply;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Services {
   // @GET ("movie?sort_by=popularity.desc")
//"movie/popular"
    @GET ("movie/popular")
   Call<MoviesReply> getPopularMovies(@Query("api_key") String apiKey);
//"/movie/top_rated"
    @GET ("movie/top_rated")
    Call<MoviesReply> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET ("movie/{id}/videos")
    Call<TrailerReply> getTrailer(
            @Path("id") long id,
            @Query("api_key") String apiKey);

    @GET ("movie/{id}/reviews")
    Call<ReviewReply> getReviwes (
            @Path("id") long id,
            @Query("api_key") String apiKey);
}
