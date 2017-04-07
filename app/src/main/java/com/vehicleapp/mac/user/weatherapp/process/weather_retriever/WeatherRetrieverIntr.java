package com.vehicleapp.mac.user.weatherapp.process.weather_retriever;

import com.vehicleapp.mac.user.weatherapp.model.WeatherReport;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 2017-04-06.
 */

public interface WeatherRetrieverIntr {

    @GET("data/2.5/weather")
    Call<WeatherReport> requestWeatherByLocation(@Query("lat") String latitude, @Query("lon") String longitude, @Query("appid") String appid);
}
