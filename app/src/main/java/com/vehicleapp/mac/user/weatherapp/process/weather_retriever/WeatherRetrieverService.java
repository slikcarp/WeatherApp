package com.vehicleapp.mac.user.weatherapp.process.weather_retriever;

import android.location.Location;
import android.util.Log;

import com.vehicleapp.mac.user.weatherapp.model.WeatherReport;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2017-04-06.
 */

public class WeatherRetrieverService implements WeatherRetrieverServiceIntr {

    private static final String TAG = "MAC";
    private static final String BASE_URL = "http://api.openweathermap.org/";
    private static final String APPID = "5ad7218f2e11df834b0eaf3a33a39d2a";

    @Override
    public WeatherReport requestWeatherByLocation(Location location) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherRetrieverIntr service = retrofit.create(WeatherRetrieverIntr.class);

        Call<WeatherReport> result = service.requestWeatherByLocation(
                String.valueOf(location.getLatitude()),
                String.valueOf(location.getLongitude()),
                APPID);

        try {
            Response<WeatherReport> response = result.execute();
            return manageResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
//        result.enqueue(new Callback<List<WeatherReport>>() {
//            @Override
//            public void onResponse(Call<List<WeatherReport>> call, Response<List<WeatherReport>> response) {
//                manageResponse(response);
//            }
//
//            @Override
//            public void onFailure(Call<List<WeatherReport>> call, Throwable t) {
//                Log.e(TAG, t.getMessage());
//                t.printStackTrace();
//            }
//        });
    }

    private WeatherReport manageResponse(Response<WeatherReport> response) {
        if (response.isSuccessful()) {
            //Print result.
            Log.e(TAG, response.body().toString());
            return response.body();
        } else {
            //The communication with the server was correct but there was a problem.
            Log.e(TAG, "onResponse: Error response code-" + response.code());
            return null;
        }
    }
}
