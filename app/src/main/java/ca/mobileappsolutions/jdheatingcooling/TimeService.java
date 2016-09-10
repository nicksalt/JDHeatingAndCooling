package ca.mobileappsolutions.jdheatingcooling;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.app.Service;
import android.os.IBinder;

import java.util.Calendar;

import static android.app.Service.START_NOT_STICKY;

/**
 * Created by Nick on 2016-09-02.
 */

public class TimeService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences myPrefs = getApplicationContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);

        if (myPrefs.getBoolean("filterDateSet", false)){
            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.MONTH, myPrefs.getInt("month", 0));
            calendar.set(Calendar.YEAR, myPrefs.getInt("year", 0));
            calendar.set(Calendar.DAY_OF_MONTH, myPrefs.getInt("day", 0));
            calendar.set(Calendar.HOUR_OF_DAY, 12);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            Intent myIntent = new Intent(TimeService.this, FilterReciever.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(TimeService.this, 0, myIntent,0);

            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

        }
        if (myPrefs.getBoolean("serviceDateSet", false)){
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.MONTH, myPrefs.getInt("serviceMonth", 0));
            calendar1.set(Calendar.YEAR, myPrefs.getInt("serviceYear", 0));
            calendar1.set(Calendar.DAY_OF_MONTH, myPrefs.getInt("serviceDay", 0));

            calendar1.set(Calendar.HOUR_OF_DAY, 12);
            calendar1.set(Calendar.MINUTE, 0);
            calendar1.set(Calendar.SECOND, 0);
            Intent myIntent1 = new Intent(TimeService.this, ServiceReciever.class);
            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(TimeService.this, 0, myIntent1,0);

            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, calendar1.getTimeInMillis(), pendingIntent1);

        }
        return START_NOT_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
