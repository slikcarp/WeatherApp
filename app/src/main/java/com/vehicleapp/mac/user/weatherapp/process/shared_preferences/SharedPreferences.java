package com.vehicleapp.mac.user.weatherapp.process.shared_preferences;

import android.content.Context;
import android.location.Location;

/**
 * Created by user on 2017-04-07.
 */

public class SharedPreferences implements SharedPreferencesIntr {

    private static final String CURRENT_LATITUDE = "CURRENT_LATITUDE";
    private static final String CURRENT_LONGITUDE = "CURRENT_LONGITUDE";
    private String WEATHER_SP = "WEATHER_SHARED_PREFERENCES";

    @Override
    public Location retrieveLastLocation(Context context) {
        Location location = new Location((String) null);

        android.content.SharedPreferences sp = getSharedPreferences(context);
        location.setLatitude(Double.valueOf(sp.getString(CURRENT_LATITUDE, "0")));
        location.setLongitude(Double.valueOf(sp.getString(CURRENT_LONGITUDE, "0")));

        return location;
    }

    @Override
    public void persistLocation(Context context, Location location) {

        android.content.SharedPreferences sp = getSharedPreferences(context);
        android.content.SharedPreferences.Editor editor = sp.edit();

        editor.putString(CURRENT_LATITUDE, String.valueOf(location.getLatitude()));
        editor.putString(CURRENT_LONGITUDE, String.valueOf(location.getLongitude()));
    }

    private android.content.SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(WEATHER_SP, Context.MODE_PRIVATE);
    }
}
