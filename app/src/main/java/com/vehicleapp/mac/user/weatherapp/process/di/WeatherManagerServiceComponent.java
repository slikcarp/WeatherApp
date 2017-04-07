package com.vehicleapp.mac.user.weatherapp.process.di;

import com.vehicleapp.mac.user.weatherapp.process.WeatherManagerService;

import dagger.Component;

/**
 * Created by user on 2017-04-07.
 */
@Component(modules = WeatherManagerServiceModule.class)
public interface WeatherManagerServiceComponent {
    void inject(WeatherManagerService weatherManagerService);
}
