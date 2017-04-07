package com.vehicleapp.mac.user.weatherapp.process.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.vehicleapp.mac.user.weatherapp.process.WeatherManagerService;

import javax.inject.Inject;

/**
 * Created by user on 2017-04-06.
 */

public class Alarm extends BroadcastReceiver implements AlarmIntr {

    public static final int TWO_HOUR_INTERVAL_MILLIS = 1000 * 60 * 120;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, WeatherManagerService.class);
        intent1.setAction(WeatherManagerService.WEATHER_UPDATE);
        context.startService(intent1);
    }

    @Override
    public void setAlarm(Context context, long timeInMilis)
    {
        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, timeInMilis,
                TWO_HOUR_INTERVAL_MILLIS, alarmIntent);
    }

    @Override
    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
