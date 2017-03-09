package com.weatherclient.di.component;

import com.weatherclient.di.module.MainActivityModule;
import com.weatherclient.android.main.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Przemysław Książek
 * on 2017-02-17.
 */

@Singleton
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
