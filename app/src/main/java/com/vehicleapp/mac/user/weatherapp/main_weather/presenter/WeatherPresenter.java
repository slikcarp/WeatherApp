package com.vehicleapp.mac.user.weatherapp.main_weather.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.vehicleapp.mac.user.weatherapp.main_weather.WeatherContract;
import com.vehicleapp.mac.user.weatherapp.model.util.WeatherEBWrapper;
import com.vehicleapp.mac.user.weatherapp.process.WeatherManagerService;
import com.vehicleapp.mac.user.weatherapp.process.weather_retriever.WeatherRetrieverService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by user on 2017-04-05.
 */

public class WeatherPresenter implements WeatherContract.Presenter {

    private final WeatherContract.View view;
    private final Context context;

    WeatherManagerService weatherManager = new WeatherManagerService();

    public WeatherPresenter(WeatherContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void loadWeather() {
        Intent intent = new Intent(context, WeatherManagerService.class);
        intent.setAction(WeatherManagerService.CURRENT_WEATHER);
        context.startService(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void displayResult(WeatherEBWrapper result) {
        if(result.getError() != null && !result.getError().isEmpty())
        {
            showToastMessage(result.getError());
        } else {
            view.displayWeather(result.getCurrentWeather());
        }
    }

    private void showToastMessage(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerAlarm(Context context) {
        weatherManager.registerAlarm(context);
    }

    @Override
    public void unregisterAlarm(Context context) {
        weatherManager.unregisterAlarm(context);
    }
}
