package com.vehicleapp.mac.user.weatherapp.main_weather.di;

import com.vehicleapp.mac.user.weatherapp.main_weather.ui.WeatherActivity;

import dagger.Component;

/**
 * Created by user on 2017-04-05.
 */
@Component(modules = WeatherModule.class)
public interface WeatherComponent {
    void inject(WeatherActivity activity);
}
