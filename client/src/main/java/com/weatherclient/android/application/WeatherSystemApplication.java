package com.weatherclient.android.application;

import android.app.Application;
import android.content.Context;

import com.weatherclient.di.component.ApplicationComponent;
import com.weatherclient.di.component.DaggerApplicationComponent;
import com.weatherclient.di.module.ApplicationModule;

/**
 * Created by Przemysław Książek
 * on 2017-02-17.
 */

public class WeatherSystemApplication extends Application {

    private ApplicationComponent applicationComponent;

    public static WeatherSystemApplication get(Context context) {
        return (WeatherSystemApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
