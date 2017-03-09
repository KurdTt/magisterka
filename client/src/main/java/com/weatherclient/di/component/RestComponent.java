package com.weatherclient.di.component;

import com.weatherclient.di.UrlScope;
import com.weatherclient.di.module.RestModule;
import com.weatherclient.android.main.presenter.MainActivityPresenter;

import dagger.Component;

/**
 * Created by Przemysław Książek
 * on 2017-02-17.
 */

@UrlScope
@Component(dependencies = ApplicationComponent.class, modules = RestModule.class)
public interface RestComponent {
    void inject(MainActivityPresenter presenter);
}
