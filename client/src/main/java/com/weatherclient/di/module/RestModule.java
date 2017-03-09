package com.weatherclient.di.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.weatherclient.android.preferences.model.SettingsPreferences;
import com.weatherclient.android.rest.RestService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Przemysław Książek
 * on 2017-02-17.
 */

@Module
public class RestModule {

    @Provides
    public RestService getRestService(SettingsPreferences preferences,
                                      GsonConverterFactory gsonConverterFactory,
                                      RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                                      OkHttpClient okHttpClient){

        return new Retrofit.Builder()
                .baseUrl(preferences.getUrl())
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .build().create(RestService.class);
    }

}
