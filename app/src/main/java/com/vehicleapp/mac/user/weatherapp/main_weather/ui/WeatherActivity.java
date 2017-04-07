package com.vehicleapp.mac.user.weatherapp.main_weather.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.vehicleapp.mac.user.weatherapp.R;
import com.vehicleapp.mac.user.weatherapp.main_weather.WeatherContract;
import com.vehicleapp.mac.user.weatherapp.main_weather.di.DaggerWeatherComponent;
import com.vehicleapp.mac.user.weatherapp.main_weather.di.WeatherModule;
import com.vehicleapp.mac.user.weatherapp.model.WeatherReport;

import javax.inject.Inject;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View {

    @Inject
    WeatherContract.Presenter presenter;
    private TextView temperatureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        DaggerWeatherComponent.builder()
                .weatherModule(new WeatherModule(this, this))
                .build().inject(this);

        presenter.createGooglePlayServicesInstance();

        temperatureView = (TextView) findViewById(R.id.temperatureView);
    }

    @Override
    protected void onStart() {
        presenter.connectGooglePlayServices();

        presenter.registerEvenBus();
        presenter.registerAlarm(this);

        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadWeather();
    }

    @Override
    protected void onStop() {
        presenter.unregisterAlarm(this);
        presenter.unregisterEventBus();

        presenter.disconnectGooglePlayServices();

        super.onStop();
    }

    @Override
    public void displayWeather(WeatherReport currentWeather) {
        temperatureView.setText(currentWeather.getMain().getTemp().toString());
    }
}
