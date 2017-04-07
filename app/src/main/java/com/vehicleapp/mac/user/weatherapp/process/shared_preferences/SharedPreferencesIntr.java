package com.vehicleapp.mac.user.weatherapp.process.shared_preferences;

import android.content.Context;
import android.location.Location;

/**
 * Created by user on 2017-04-07.
 */

public interface SharedPreferencesIntr {
    Location retrieveLastLocation(Context context);

    void persistLocation(Context context, Location location);
}
