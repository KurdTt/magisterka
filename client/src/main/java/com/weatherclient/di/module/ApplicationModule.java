package com.weatherclient.di.module;

import android.app.Activity;
import android.content.SharedPreferences;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.weatherclient.android.application.WeatherSystemApplication;
import com.weatherclient.android.preferences.model.SettingsPreferences;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Przemysław Książek
 * on 2017-02-27.
 */

@Module
public class ApplicationModule {

    private static final String PREFS_NAME = "weatherSystemPrefs";
    private static final int READ_TIMEOUT = 20;
    private static final int CONNECTION_TIMEOUT = 3;

    private final WeatherSystemApplication mApplication;
    private final SettingsPreferences settingsPreferences;

    public ApplicationModule(WeatherSystemApplication application) {
        this.mApplication = application;
        SharedPreferences appSharedPrefs = application.getSharedPreferences(
                PREFS_NAME, Activity.MODE_PRIVATE);
        this.settingsPreferences = new SettingsPreferences(appSharedPrefs);
    }

    @Provides
    @Singleton
    WeatherSystemApplication providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    SettingsPreferences getSettingsPreferences() {
        return settingsPreferences;
    }

    @Provides
    @Singleton
    public GsonConverterFactory getGson() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public RxJava2CallAdapterFactory getRxJavaAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}
