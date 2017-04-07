package com.vehicleapp.mac.user.weatherapp.main_weather.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.vehicleapp.mac.user.weatherapp.R;
import com.vehicleapp.mac.user.weatherapp.main_weather.WeatherContract;
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

//        DaggerWeatherComponent.builder()
//                .weatherModule(new WeatherModule(this, this))
//                .build().inject(this);

        temperatureView = (TextView) findViewById(R.id.temperatureView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.loadWeather();
        presenter.registerAlarm(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.unregisterAlarm(this);
    }

    @Override
    public void displayWeather(WeatherReport currentWeather) {
        temperatureView.setText(currentWeather.getMain().getTemp().toString());
    }
}
