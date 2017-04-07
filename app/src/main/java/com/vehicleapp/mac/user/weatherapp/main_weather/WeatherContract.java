package com.vehicleapp.mac.user.weatherapp.main_weather;

import com.vehicleapp.mac.user.weatherapp.main_weather.presenter.WeatherPresenterIntr;
import com.vehicleapp.mac.user.weatherapp.main_weather.ui.WeatherViewIntr;

/**
 * Created by user on 2017-04-05.
 */

public interface WeatherContract {
    public interface Presenter extends WeatherPresenterIntr {
    }

    public interface View extends WeatherViewIntr {
    }
}
