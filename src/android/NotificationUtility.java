package com.zendrive.cordova.plugin;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

public class NotificationUtility {
    private static final String FOREGROUND_CHANNEL_KEY = "Foreground";
    static int FOREGROUND_MODE_NOTIFICATION_ID = 1;
    private static final String ISSUES_CHANNEL_KEY = "Issues";
    public static int ZENDRIVE_FAILED_NOTIFICATION_ID = 4;

    static Notification getMaybeInDriveNotificationContainer(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new NotificationCompat.Builder(context, FOREGROUND_CHANNEL_KEY)
                    .setSmallIcon(android.support.v4.R.drawable.notification_icon_background)
                    .setContentTitle("Zendrive")
                    .setDefaults(0)
                    .setPriority(2)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .setChannelId(FOREGROUND_CHANNEL_KEY)
                    .setContentText("Detecting Possible Drive.").build();
        }

        return new NotificationCompat.Builder(context, FOREGROUND_CHANNEL_KEY)
                .setSmallIcon(android.support.v4.R.drawable.notification_icon_background)
                .setContentTitle("Zendrive")
                .setContentText("Detecting Possible Drive.")
                .build();
    }

    static Notification getInDriveNotificationContainer(@NonNull Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            return new NotificationCompat.Builder(context, FOREGROUND_CHANNEL_KEY)
                    .setSmallIcon(android.support.v4.R.drawable.notification_icon_background)
                    .setContentTitle("Zendrive")
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .setChannelId(FOREGROUND_CHANNEL_KEY)
                    .setContentText("Drive Active.").build();
        }

        return new NotificationCompat.Builder(context, FOREGROUND_CHANNEL_KEY)
                .setSmallIcon(android.support.v4.R.drawable.notification_icon_background)
                .setContentTitle("Zendrive")
                .setContentText("Drive Active.")
                .build();

    }


}
