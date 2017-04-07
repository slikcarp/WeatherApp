package com.vehicleapp.mac.user.weatherapp.process.di;

import com.vehicleapp.mac.user.weatherapp.process.alarm.Alarm;
import com.vehicleapp.mac.user.weatherapp.process.alarm.AlarmIntr;
import com.vehicleapp.mac.user.weatherapp.process.weather_retriever.WeatherRetrieverService;
import com.vehicleapp.mac.user.weatherapp.process.weather_retriever.WeatherRetrieverServiceIntr;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 2017-04-07.
 */
@Module
public class WeatherManagerServiceModule {

    @Provides
    @Named("alarmManager")
    public AlarmIntr provideImplementation() {
        return new Alarm();
    }

    @Provides
    @Named("weatherRetrieverService")
    public WeatherRetrieverServiceIntr provideWeatherRetrieverHandler() {
        return new WeatherRetrieverService();
    }
}
