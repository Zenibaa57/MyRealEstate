package com.example.myrealestate.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myrealestate.PropertyListActivity;
import com.example.myrealestate.R;
import com.example.myrealestate.exchangeAPI.Retrofit;
import com.example.myrealestate.repository.RealEstateRepository;

public class NotificationBuilder {

    private static volatile NotificationBuilder instance;
    NotificationManagerCompat notificationManagerCompat;
    final String notificationChannelId = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ? "MyChannel" : null;

    public NotificationBuilder(Context context) {
    }

    public static NotificationBuilder getInstance(Context context) {
        if (instance == null) {
            synchronized (RealEstateRepository.class) {
                if (instance == null)
                    instance = new NotificationBuilder(context);
            }
        }
        return instance;
    }

    public void buildNotification(Context context,String title, String body) {

        notificationManagerCompat = NotificationManagerCompat.from(context);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            final NotificationChannel notificationChannel = new NotificationChannel(notificationChannelId, "My Channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManagerCompat.createNotificationChannel(notificationChannel);
        }

        final Intent intent = new Intent(context, PropertyListActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, notificationChannelId);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(body);
        notificationBuilder.setSmallIcon(R.drawable.type);
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setChannelId(notificationChannelId);
        notificationBuilder.setContentIntent(pendingIntent);

        notificationManagerCompat.notify(1, notificationBuilder.build());
    }
}
