package com.weatherclient.android.rest;

import com.weatherclient.android.model.WeatherParameter;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Przemysław Książek
 on 2017-02-17.
 */
public interface RestService {

    @GET("/send")
    Observable<List<List<WeatherParameter>>> getWeatherParameters();
}
