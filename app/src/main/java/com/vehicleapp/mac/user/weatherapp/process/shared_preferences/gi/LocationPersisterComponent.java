package com.vehicleapp.mac.user.weatherapp.process.shared_preferences.gi;

import com.vehicleapp.mac.user.weatherapp.process.shared_preferences.LocationPersister;

import dagger.Component;

/**
 * Created by user on 2017-04-07.
 */
@Component(modules = LocationPersisterModule.class)
public interface LocationPersisterComponent {
    void inject(LocationPersister locationPersister);
}
