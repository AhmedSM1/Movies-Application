package com.example.movies.popularmovies.WidgetDB;

import android.net.Uri;
import android.provider.BaseColumns;



public class WidgetDBContract {
    public static final String AUTHORITY = "com.example.movies.popularmovies.WidgetDB";
    public static final String MOVIE_PATH = ".DataProvider";


    public static final Uri BASE_CONTENT = Uri.parse("content://"+AUTHORITY);

     private WidgetDBContract(){
         throw new AssertionError("Not Available");
     }

     public static class MovieEntry implements BaseColumns{
         public static final Uri Content_URI =
                 BASE_CONTENT.buildUpon()
                 .appendPath(MOVIE_PATH)
                 .build();

         public static final String TABLE_NAME = "Movie";
         public static final String MOVIE_ID_COLUMN = "_movieID";
         public static final String MOVIE_TITLE_COLUMN = "_movieTitle";
         public static final String MOVIE_VOTE_COLUMN = "_movieVote";
         public static final String TIME_STAMP_COLUMN = "_time";
         public static final String POSTER_PATH = "_posterPath";



     }






}
