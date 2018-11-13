package com.zendrive.cordova.plugin;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

//make sure to import your own R.drawable class

public class NotificationUtility {
    private static final String FOREGROUND_CHANNEL_KEY = "Foreground";
    private static final String ISSUES_CHANNEL_KEY = "Issues";

    static Notification getMaybeInDriveNotification(@NonNull Context context) {
        createNotificationChannels(context);
        return new NotificationCompat.Builder(context, FOREGROUND_CHANNEL_KEY)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("Zendrive")
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setContentText("Detecting possible drive.").build();
    }

    static Notification getInDriveNotification(@NonNull Context context) {
        createNotificationChannels(context);
        return new NotificationCompat.Builder(context, FOREGROUND_CHANNEL_KEY)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("Zendrive")
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setContentText("Drive Active.").build();
    }

    private static void createNotificationChannels(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = context.getSystemService(NotificationManager.class);

            NotificationChannel foregroundNotificationChannel = new NotificationChannel
                    (FOREGROUND_CHANNEL_KEY, "Zendrive trip tracking",
                            NotificationManager.IMPORTANCE_DEFAULT);
            foregroundNotificationChannel.setShowBadge(false);

            NotificationChannel issuesNotificationChannel = new NotificationChannel
                    (ISSUES_CHANNEL_KEY, "Issues",
                            NotificationManager.IMPORTANCE_DEFAULT);
            issuesNotificationChannel.setShowBadge(true);

            if (manager != null) {
                manager.createNotificationChannel(foregroundNotificationChannel);
                manager.createNotificationChannel(issuesNotificationChannel);
            }
        }
    }
}
