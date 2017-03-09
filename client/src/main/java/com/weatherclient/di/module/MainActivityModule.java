package com.weatherclient.di.module;

import com.weatherclient.android.main.presenter.MainActivityPresenter;
import com.weatherclient.android.main.view.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Przemysław Książek
 * on 2017-02-17.
 */

@Module
public class MainActivityModule {

    private final MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    public MainActivityPresenter getPresenter() {
        return new MainActivityPresenter(activity);
    }

}
