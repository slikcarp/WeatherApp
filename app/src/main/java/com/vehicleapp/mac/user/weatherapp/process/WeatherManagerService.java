package com.vehicleapp.mac.user.weatherapp.process;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;

import com.vehicleapp.mac.user.weatherapp.model.WeatherReport;
import com.vehicleapp.mac.user.weatherapp.model.util.WeatherEBWrapper;
import com.vehicleapp.mac.user.weatherapp.process.alarm.AlarmIntr;
import com.vehicleapp.mac.user.weatherapp.process.di.DaggerWeatherManagerServiceComponent;
import com.vehicleapp.mac.user.weatherapp.process.di.WeatherManagerServiceModule;
import com.vehicleapp.mac.user.weatherapp.process.shared_preferences.SharedPreferencesIntr;
import com.vehicleapp.mac.user.weatherapp.process.weather_retriever.WeatherRetrieverServiceIntr;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class WeatherManagerService extends IntentService {

    public static final String CURRENT_WEATHER = "CURRENT_WEATHER";
    public static final String WEATHER_UPDATE = "WEATHER_UPDATE";

    @Inject
    @Named("alarmManager")
    AlarmIntr alarmManager;

    @Inject
    @Named("weatherRetrieverService")
    WeatherRetrieverServiceIntr weatherRetrieverService;

    @Inject
    @Named("sharedPreferences")
    SharedPreferencesIntr sharedPreferences;

    public WeatherManagerService() {
        super("WeatherManagerService");
        DaggerWeatherManagerServiceComponent.builder()
                .weatherManagerServiceModule(new WeatherManagerServiceModule())
                .build()
                .inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            switch (action) {
                case WeatherManagerService.CURRENT_WEATHER :
                    retrieveWeather(this, WeatherManagerService.CURRENT_WEATHER);
                    break;
            }
        }
    }

    public void registerAlarm(Context context) {
        long timeMillis = 0;
        alarmManager.setAlarm(context, timeMillis);
    }

    public void unregisterAlarm(Context context) {
        alarmManager.cancelAlarm(context);
    }

    private void retrieveWeather(Context context, String retrieve) {
        Location location = sharedPreferences.retrieveLastLocation(context);
        WeatherReport weatherReport = weatherRetrieverService.requestWeatherByLocation(location);

        WeatherEBWrapper wrapper = new WeatherEBWrapper();
        if (weatherReport == null) {
            wrapper.setError("There was a problem retrieving the weather.");
        } else {
            wrapper.setCurrentWeather(weatherReport);
        }

        EventBus.getDefault().post(wrapper);
    }
}
