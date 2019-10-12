package com.example.movies.popularmovies.Widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.movies.popularmovies.Model.Movie;
import com.example.movies.popularmovies.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WidgetService extends IntentService {
     public static final String UPDATE_WIDGET = "android.appwidget.action.APPWIDGET_UPDATE";
     public static final String SERVICE_NAME = "WidgetService";


    public WidgetService() {
        super(SERVICE_NAME);
    }

    public static void StartActionUpdateWidget(Context context){
        Intent intent = new Intent(context,WidgetService.class);
        intent.setAction(UPDATE_WIDGET);
        context.startService(intent);
    }






    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null){
            String action = intent.getAction();
            if (UPDATE_WIDGET.equals(action)){
                handleWidgetUpdate();
            }
        }
    }
    private void handleWidgetUpdate(){
        AppWidgetManager widgetManager = AppWidgetManager.getInstance(this);
        int widgetsIds[] = widgetManager.getAppWidgetIds(new ComponentName(this, FavoriteWidget.class));
        widgetManager.notifyAppWidgetViewDataChanged(widgetsIds, R.id.widget_list);
        FavoriteWidget.updateAppWidget(this, widgetManager, widgetsIds);



    }
}
