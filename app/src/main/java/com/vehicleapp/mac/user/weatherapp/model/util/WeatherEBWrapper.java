package com.vehicleapp.mac.user.weatherapp.model.util;

import android.app.FragmentTransaction;

import com.vehicleapp.mac.user.weatherapp.model.WeatherReport;

/**
 * Created by user on 2017-04-06.
 */

public class WeatherEBWrapper {

    private String error;
    private WeatherReport currentWeather;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public WeatherReport getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(WeatherReport currentWeather) {
        this.currentWeather = currentWeather;
    }
}
