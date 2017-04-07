package com.vehicleapp.mac.user.weatherapp.process.weather_retriever;

import android.location.Location;
import android.util.Log;

import com.vehicleapp.mac.user.weatherapp.model.WeatherReport;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2017-04-06.
 */

public class WeatherRetrieverService implements WeatherRetrieverServiceIntr {

    private static final String TAG = "MAC";
    private static final String BASE_URL = "api.openweathermap.org/";

    @Override
    public void requestWeatherByLocation(Location location) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherRetrieverIntr service = retrofit.create(WeatherRetrieverIntr.class);

        Call<List<WeatherReport>> result = service.requestWeatherByLocation(
                String.valueOf(location.getLatitude()),
                String.valueOf(location.getLongitude()));

        result.enqueue(new Callback<List<WeatherReport>>() {
            @Override
            public void onResponse(Call<List<WeatherReport>> call, Response<List<WeatherReport>> response) {
                if(response.isSuccessful()) {
                    //Print result.
                    for(WeatherReport repo: response.body()) {
                        Log.d(TAG, "onResponse: " + repo.toString());
                    }
                } else {
                    //The communication with the server was correct but there was a problem.
                    Log.e(TAG, "onResponse: Error response code-" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<WeatherReport>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
