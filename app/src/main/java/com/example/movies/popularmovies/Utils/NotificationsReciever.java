package com.example.movies.popularmovies.Utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.movies.popularmovies.R;
import com.example.movies.popularmovies.UI.MainActivity;

import java.util.Calendar;

public class NotificationsReciever {
    private static final int NOTIFICATION_ID = 1000;
    private static final int PENDING_INTENT_ID = 1001;
    private static final String NOTIFICATION_CHANNEL_ID = "notification_channel_name";
    private static NotificationCompat.Builder mNotificationBuilder;
    private static NotificationManager mNotificationManager;
    private static boolean isNotificationNew = true;


    //we will make other methods static
    private NotificationsReciever() {
    }

    public static void myNotification(Context context, String title, String body) {
        if (isNotificationNew == false) {
            updateNotification(title, body);
            return;
        }
        // Create a Notification Manager
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Create Notification Channel on Android 8.0 and Above.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Prepare name, description, and importance (PRIORITY) for our channel. More about: Set the importance level at: https://developer.android.com/training/notify-user/channels#importance

            int importance = NotificationManager.IMPORTANCE_HIGH;

            // Create the Channel
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, title, importance);
            mNotificationManager.createNotificationChannel(channel);

            // Set the description
            channel.setDescription(body);
        }

        mNotificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                // Put all notifications under one as a group
                .setGroup(NotificationCompat.CATEGORY_REMINDER)
                .setGroupSummary(true)
                // Required icon for the notification.
                .setSmallIcon(R.drawable.notifications_icon)



                // Title text
                .setContentTitle(title)

                // Body text
                .setContentText(body)

                // If you want more text use Style For longer text
                // Want more? Check: Create an Expandable Notification at:
                // https://developer.android.com/training/notify-user/expanded
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))

                // ContentIntent an intent to open when the user tap on the notification (We used our helper method):
                .setContentIntent(contentIntent(context))

                // Show notification on lock screen
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)


                // Used if you want to update the notification in silent.
                // .setOnlyAlertOnce(true)

                // Close the notification when user tap on it
                .setAutoCancel(true);


        // More Configuration to the notificationBuilder
        // Notification Intrusive (PRIORITY) on Android 7.1 and Lower.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            mNotificationBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        }


        // Build it
        mNotificationManager.notify(NOTIFICATION_ID, mNotificationBuilder.build());
        isNotificationNew = false;

    }


    //Pending intent to main Activity when the notification is clicked
    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(
                context,
                PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }



    public static void updateNotification(String title, String body) {
        mNotificationBuilder
                .setContentTitle(title)
                .setContentText(body)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .setOnlyAlertOnce(true)
        ;

        mNotificationManager.notify(NOTIFICATION_ID, mNotificationBuilder.build());
    }





    public static void clearNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }


}
