package com.vehicleapp.mac.user.weatherapp.main_weather.presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.vehicleapp.mac.user.weatherapp.main_weather.WeatherContract;
import com.vehicleapp.mac.user.weatherapp.model.util.WeatherEBWrapper;
import com.vehicleapp.mac.user.weatherapp.process.WeatherManagerService;
import com.vehicleapp.mac.user.weatherapp.process.shared_preferences.LocationPersister;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by user on 2017-04-05.
 */

public class WeatherPresenter implements WeatherContract.Presenter, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private final WeatherContract.View view;
    private final Context context;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private WeatherManagerService weatherManager = new WeatherManagerService();


    public WeatherPresenter(WeatherContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void createGooglePlayServicesInstance() {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
                Intent intent = new Intent(context, LocationPersister.class);
                intent.putExtra(LocationPersister.LOCATION_PARAM, mLastLocation);
                context.startService(intent);
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        showToastMessage(String.valueOf("Connection suspended: " + i));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        showToastMessage(connectionResult.getErrorMessage());
    }

    @Override
    public void connectGooglePlayServices() {
        mGoogleApiClient.connect();
    }

    @Override
    public void disconnectGooglePlayServices() {
        mGoogleApiClient.disconnect();
    }

    @Override
    public void loadWeather() {
        Intent intent = new Intent(context, WeatherManagerService.class);
        intent.setAction(WeatherManagerService.CURRENT_WEATHER);
        context.startService(intent);
    }

    @Override
    public void registerEvenBus() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void displayResult(WeatherEBWrapper result) {
        if (result.getError() != null && !result.getError().isEmpty()) {
            showToastMessage(result.getError());
        } else {
            view.displayWeather(result.getCurrentWeather());
        }
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
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
