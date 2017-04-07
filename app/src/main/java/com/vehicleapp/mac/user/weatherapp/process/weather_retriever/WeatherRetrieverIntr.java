package com.vehicleapp.mac.user.weatherapp.process.weather_retriever;

import com.vehicleapp.mac.user.weatherapp.model.WeatherReport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by user on 2017-04-06.
 */

public interface WeatherRetrieverIntr {

    @GET("data/2.5/weather?lat={lat}&lon={lon}")
    Call<List<WeatherReport>> requestWeatherByLocation(@Path("lat") String location, @Path("lon") String longitud);
}
