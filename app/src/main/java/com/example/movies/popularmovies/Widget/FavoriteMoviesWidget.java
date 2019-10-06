package com.example.movies.popularmovies.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.movies.popularmovies.R;

/**
 * Implementation of App com.example.movies.popularmovies.Widget functionality.
 */
public class FavoriteMoviesWidget extends AppWidgetProvider {
//AppWidgetProvider extends BroadcastReceiver.
// SimpleAppWidget is indirectly a child of BroadcastReceiver.
// So our widget class is a receiver class.



    //onUpdate(): This is called to update the App com.example.movies.popularmovies.Widget at intervals
    // defined by the updatePeriodMillis attribute.
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.favorite_movies_widget);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }




   //This is called when an instance the App com.example.movies.popularmovies.Widget is created for the first time

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    //This is called when the last instance of your App com.example.movies.popularmovies.Widget is deleted from the App com.example.movies.popularmovies.Widget host.
    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

