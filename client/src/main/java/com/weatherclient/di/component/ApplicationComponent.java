package com.weatherclient.di.component;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.weatherclient.android.application.WeatherSystemApplication;
import com.weatherclient.di.module.ApplicationModule;
import com.weatherclient.android.preferences.model.SettingsPreferences;
import com.weatherclient.android.preferences.view.GeneralPreferenceFragment;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Przemysław Książek
 * on 2017-03-07.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    WeatherSystemApplication getApplication();
    SettingsPreferences getSettingsPreferences();
    GsonConverterFactory getGson();
    RxJava2CallAdapterFactory getJavaAdapter();
    OkHttpClient getHttpClient();
    void inject (GeneralPreferenceFragment fragment);
}
