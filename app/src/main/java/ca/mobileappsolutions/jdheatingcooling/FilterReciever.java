package ca.mobileappsolutions.jdheatingcooling;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.renderscript.RenderScript;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Nick on 2016-09-02.
 */

public class FilterReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String notText ="Time to clean your filters! Go to the JD Heating & Cooling app to update the date.";

        android.support.v4.app.NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Time to clean your filters!")
                        .setContentText(notText)
                        .setPriority(Notification.PRIORITY_MAX)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(notText))
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setAutoCancel(true);

        Intent resultIntent = new Intent(context, Filters.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(0, mBuilder.build());
        SharedPreferences myPrefs = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor e = myPrefs.edit();
        e.putBoolean("filterDateSet", false);
        e.apply();
    }
}
