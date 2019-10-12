package com.example.movies.popularmovies.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;

import com.example.movies.popularmovies.R;
import com.example.movies.popularmovies.UI.MainActivity;

public class FavoriteWidget extends AppWidgetProvider {
    public static final String TAG = "FavoriteWidget";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int[] appWidgetId) {
           RemoteViews views = getMoviesListView(context);
           appWidgetManager.updateAppWidget(appWidgetId,views);

    }

    private static RemoteViews getMoviesListView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.favorite_widget);
        Intent listViewIntent = new Intent(context,MoviesListViews.class);
        views.setRemoteAdapter(R.id.widget_list,listViewIntent);
        
        //Pending intent
        Intent appIntent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,
                appIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        
        
        views.setPendingIntentTemplate(R.id.widget_list,pendingIntent);
        views.setEmptyView(R.id.widget_list,R.id.empty_view);
        return views;
        
        
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d(TAG, "onUpdate: ");
       WidgetService.StartActionUpdateWidget(context);

    }

    @Override
    public void onEnabled(Context context) {
        Log.d(TAG, "onEnabled: ");
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        Log.d(TAG, "onDisabled: ");
        // Enter relevant functionality for when the last widget is disabled
    }

  }


