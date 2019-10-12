package com.example.movies.popularmovies.Widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.movies.popularmovies.R;
import com.example.movies.popularmovies.Widget.WidgetDB.WidgetDBContract;

public class MoviesListViews extends RemoteViewsService {
    public static final String TAG = MoviesListViews.class.getName();
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListViewsFactory(this.getApplicationContext());
    }


    private class ListViewsFactory implements RemoteViewsFactory {
        private Cursor cursor;
        private Context context;


        public ListViewsFactory(Context applicationContext) {
            this.context = applicationContext;
        }

        @Override
        public void onCreate() {
            Log.d(TAG, "onCreate: ");
        }

        @Override
        public void onDataSetChanged() {
            if (cursor != null ) cursor.close();

            cursor = getContentResolver().query(WidgetDBContract.MovieEntry.Content_URI,
                    null,
                    null,
                    null,
                    WidgetDBContract.MovieEntry.TIME_STAMP_COLUMN);

        }

        @Override
        public void onDestroy() {
            Log.d(TAG, "onDestroy: ");
            if (cursor != null){
                Log.d(TAG, "getCount: "+cursor.getCount());
                cursor.close();
            }
        }

        @Override
        public int getCount() {
            if (cursor != null){
                return cursor.getCount();
            }else {
                return 0;
            }
        }

        @Override
        public RemoteViews getViewAt(int position) {
            if (cursor == null || cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToPosition(position);
            int titlePosition = cursor.getColumnIndex(WidgetDBContract.MovieEntry.MOVIE_TITLE_COLUMN);

            String movieTitle = cursor.getString(titlePosition);

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item);

            remoteViews.setTextViewText(R.id.widget_movie_title,movieTitle);

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
            Log.d(TAG, "getItemId: "+position);
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
