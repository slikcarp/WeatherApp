package com.vehicleapp.mac.user.weatherapp.main_weather.di;

import android.content.Context;

import com.vehicleapp.mac.user.weatherapp.main_weather.WeatherContract;
import com.vehicleapp.mac.user.weatherapp.main_weather.presenter.WeatherPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 2017-04-05.
 */
@Module
public class WeatherModule {

    private final WeatherContract.View view;
    private final Context context;

    public WeatherModule(WeatherContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Provides
    public WeatherContract.Presenter providePresenter() {
        return new WeatherPresenter(view, context);
    }
}
