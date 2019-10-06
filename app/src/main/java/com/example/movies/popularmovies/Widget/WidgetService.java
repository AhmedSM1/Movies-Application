package com.example.movies.popularmovies.Widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidx.annotation.NonNull;

import com.example.movies.popularmovies.Model.Movie;
import com.example.movies.popularmovies.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new  WidgetItemFactory(getApplicationContext(),intent);
    }

    class WidgetItemFactory implements RemoteViewsService.RemoteViewsFactory{
         List<Movie> movies = new ArrayList<>();
         Context context;
         Intent intent;

        public static final String TAG = "Widget";
        private FirebaseAuth auth;

        private DatabaseReference movieRef;

        private String userId;

        private FirebaseUser user;

         private void getMovie()throws NullPointerException{
             try {
                 movies.clear();

                 auth = FirebaseAuth.getInstance();

                 user = auth.getCurrentUser();
                 assert user != null;

                 userId = auth.getCurrentUser().getUid();

                 movieRef = FirebaseDatabase.getInstance().getReference().child(userId);
                 Log.d(TAG, "getMovie: Movie Ref = "+movieRef.toString());
                 movieRef.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         for (DataSnapshot itemSnapShot : dataSnapshot.getChildren()){
                             for (DataSnapshot data : itemSnapShot.getChildren()){
                                 Log.d(TAG, "onDataChange: Datasnapshot ==>"+data.toString());
                                 Movie movie = data.getValue(Movie.class);
                                 Log.d(TAG, "onDataChange: Movie is "+movie.getTitle());
                                 movies.add(movie);
                             }
                         }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {
                         Log.d(TAG, "onCancelled: "+databaseError.getMessage());
                     }
                 });




             }catch (NullPointerException e){
                 e.printStackTrace();
             }
         }

        public WidgetItemFactory(Context context,Intent intent) {
            this.context = context;
            this.intent = intent;
        }

        @Override
        public void onCreate() {
           getMovie();
        }

        @Override
        public void onDataSetChanged() {
             getMovie();
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            Log.d(TAG, "getCount: "+movies.size());
            return movies.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item);
            remoteViews.setTextViewText(R.id.widget_movie_title,movies.get(position).getTitle());
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }










}
