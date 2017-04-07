package com.vehicleapp.mac.user.weatherapp.process.weather_retriever.di;

import com.vehicleapp.mac.user.weatherapp.process.WeatherManagerService;
import com.vehicleapp.mac.user.weatherapp.process.di.WeatherManagerServiceModule;

import dagger.Component;

/**
 * Created by user on 2017-04-06.
 */
@Component(modules = WeatherManagerServiceModule.class)
public interface WeatherRetrieverHandlerComponent {
    void inject(WeatherManagerService weatherManagerService);
}
