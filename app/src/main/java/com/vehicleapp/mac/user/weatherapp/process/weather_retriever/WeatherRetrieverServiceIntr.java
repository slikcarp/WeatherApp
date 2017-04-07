package com.vehicleapp.mac.user.weatherapp.process.weather_retriever;

import android.location.Location;

/**
 * Created by user on 2017-04-06.
 */

public interface WeatherRetrieverServiceIntr {

    void requestWeatherByLocation(Location location);
}
