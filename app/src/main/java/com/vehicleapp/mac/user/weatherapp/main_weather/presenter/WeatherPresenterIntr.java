package com.vehicleapp.mac.user.weatherapp.main_weather.presenter;

import android.content.Context;

/**
 * Created by user on 2017-04-05.
 */

public interface WeatherPresenterIntr {

    void loadWeather();

    void registerAlarm(Context context);

    void unregisterAlarm(Context context);
}
