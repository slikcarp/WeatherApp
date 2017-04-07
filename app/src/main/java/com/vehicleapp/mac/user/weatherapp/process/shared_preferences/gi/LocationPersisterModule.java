package com.vehicleapp.mac.user.weatherapp.process.shared_preferences.gi;

import com.vehicleapp.mac.user.weatherapp.process.shared_preferences.SharedPreferences;
import com.vehicleapp.mac.user.weatherapp.process.shared_preferences.SharedPreferencesIntr;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 2017-04-07.
 */
@Module
public class LocationPersisterModule {

    @Provides
    public SharedPreferencesIntr provideSharedPreferences() {
        return new SharedPreferences();
    }
}
