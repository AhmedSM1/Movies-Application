package com.example.movies.popularmovies.Widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;

public class RemoteViewsService extends android.widget.RemoteViewsService {

     private Cursor cursor;
     private Context context;
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridViewsFactory(this.getApplicationContext());
    }








    class GridViewsFactory implements RemoteViewsService.RemoteViewsFactory{
       private Cursor cursor;
       private Context context;

       public GridViewsFactory(Context context){
           this.context = context;
       }


        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
         //contract class


        }

        @Override
        public void onDestroy() {
              if (cursor != null){
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
        public RemoteViews getViewAt(int i) {
           return null;
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
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }





}
