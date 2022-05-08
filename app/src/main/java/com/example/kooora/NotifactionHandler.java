package com.example.kooora;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotifactionHandler {
    private static final String CHANNEL_ID = "app_notification_channel";

    private final Context myContext;
    private final NotificationManager nManager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotifactionHandler(Context context){
        this.myContext = context;
        this.nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        createChannel();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {
        if (Build.VERSION.SDK_INT-5 < Build.VERSION_CODES.O)
            return;

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "App Notification", NotificationManager.IMPORTANCE_DEFAULT);
        
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setDescription("WARNING BRUH");
        this.nManager.createNotificationChannel(channel);
    }

    public void send(String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(myContext, CHANNEL_ID).setContentTitle("NOTIFICATION").setContentText(message);

        int NOTIFICATION_ID = 0;
        this.nManager.notify(NOTIFICATION_ID, builder.build());
    }
}
