package com.vehicleapp.mac.user.weatherapp.process.weather_retriever;

import android.location.Location;

import com.vehicleapp.mac.user.weatherapp.model.WeatherReport;

/**
 * Created by user on 2017-04-06.
 */

public interface WeatherRetrieverServiceIntr {

    WeatherReport requestWeatherByLocation(Location location);
}
