package com.example.movies.popularmovies.Widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.example.movies.popularmovies.R;

public class WidgetService extends IntentService {

    public static final String UPDATE_WIDGET = "android.appwidget.action.APPWIDGET_UPDATE";
   public static final String name = "WidgetService";

    public WidgetService() {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }



  private void handleWidgetUpdate(){
      AppWidgetManager widgetManager = AppWidgetManager.getInstance(this);
      int widgetIds[] = widgetManager.getAppWidgetIds(new ComponentName(this, Widget.class));
      widgetManager.notifyAppWidgetViewDataChanged(widgetIds, R.id.widget_components);

  }





}
