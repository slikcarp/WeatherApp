package com.vehicleapp.mac.user.weatherapp.process.alarm;

import android.content.Context;

/**
 * Created by user on 2017-04-06.
 */

public interface AlarmIntr {
    void setAlarm(Context context, long timeInMilis);

    void cancelAlarm(Context context);
}
