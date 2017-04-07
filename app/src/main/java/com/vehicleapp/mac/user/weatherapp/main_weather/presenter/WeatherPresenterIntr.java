package com.vehicleapp.mac.user.weatherapp.main_weather.presenter;

import android.content.Context;

/**
 * Created by user on 2017-04-05.
 */

public interface WeatherPresenterIntr {

    void createGooglePlayServicesInstance();

    void loadWeather();

    void registerAlarm(Context context);

    void unregisterAlarm(Context context);

    void connectGooglePlayServices();

    void disconnectGooglePlayServices();

    void registerEvenBus();

    void unregisterEventBus();
}
