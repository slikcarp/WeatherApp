package com.vehicleapp.mac.user.weatherapp.process.shared_preferences;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;

import com.vehicleapp.mac.user.weatherapp.process.shared_preferences.gi.DaggerLocationPersisterComponent;
import com.vehicleapp.mac.user.weatherapp.process.shared_preferences.gi.LocationPersisterModule;

import javax.inject.Inject;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 */
public class LocationPersister extends IntentService {

    public static final String LOCATION_PARAM = "LOCATION_PARAM";

    @Inject
    SharedPreferencesIntr sharedPreferences;

    public LocationPersister() {
        super("LocationPersister");
        DaggerLocationPersisterComponent.builder()
                .locationPersisterModule(new LocationPersisterModule())
                .build()
                .inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Location location = intent.getParcelableExtra(LOCATION_PARAM);
            sharedPreferences.persistLocation(this, location);
        }
    }
}
