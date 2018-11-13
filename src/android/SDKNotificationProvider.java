package com.zendrive.cordova.plugin;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.zendrive.sdk.ZendriveNotificationContainer;
import com.zendrive.sdk.ZendriveNotificationProvider;

public class SDKNotificationProvider implements ZendriveNotificationProvider {

    @NonNull
    @Override
    @RequiresApi(Build.VERSION_CODES.O)
    public ZendriveNotificationContainer getMaybeInDriveNotificationContainer(@NonNull Context context) {
        return new ZendriveNotificationContainer(
                NotificationUtility.FOREGROUND_MODE_NOTIFICATION_ID,
                NotificationUtility.getMaybeInDriveNotificationContainer(context));
    }

    @NonNull
    @Override
    public ZendriveNotificationContainer getInDriveNotificationContainer(@NonNull Context context) {
        return new ZendriveNotificationContainer(
                NotificationUtility.FOREGROUND_MODE_NOTIFICATION_ID,
                NotificationUtility.getInDriveNotificationContainer(context));
    }
}
