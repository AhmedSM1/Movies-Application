package com.example.movies.popularmovies.WidgetDB;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.movies.popularmovies.R;

public class DataProvider extends ContentProvider {
    private WidgetDBHelper helper;
    private static final int FAVORITE_MOVIES = 100;
    private static UriMatcher sUriMatched = matchUris();

    private static UriMatcher matchUris(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(WidgetDBContract.AUTHORITY,WidgetDBContract.MOVIE_PATH,FAVORITE_MOVIES);
        return uriMatcher;
    }



    @Override
    public boolean onCreate() {
      helper = new WidgetDBHelper(getContext());
      return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor;
        switch (sUriMatched.match(uri)){
            case FAVORITE_MOVIES:
                cursor = database.query(WidgetDBContract.MovieEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                break;
                default:
                    throw new UnsupportedOperationException(String.valueOf(R.string.not_available));
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;





    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
       throw new UnsupportedOperationException(String.valueOf(R.string.not_available));
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase database = helper.getReadableDatabase();
        Uri movieAddress;
        switch (sUriMatched.match(uri)){
            case FAVORITE_MOVIES:
                long id = database.insert(WidgetDBContract.MovieEntry.TABLE_NAME,
                        null,
                        contentValues);
                if (id>0){
                    movieAddress = ContentUris.withAppendedId(uri, id);
                }else {
                    throw  new UnknownError(String.valueOf(R.string.not_available));
                }
                break;
             default:
                 throw  new UnsupportedOperationException(String.valueOf(R.string.not_available));

        }
        getContext().getContentResolver().notifyChange(movieAddress,null);
        return movieAddress;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        throw  new UnsupportedOperationException(String.valueOf(R.string.not_available));
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        throw  new UnsupportedOperationException(String.valueOf(R.string.not_available));

    }
}
