package com.weatherclient.android.main.presenter;

import android.app.ProgressDialog;

import com.weatherclient.android.application.WeatherSystemApplication;
import com.weatherclient.android.main.view.MainView;
import com.weatherclient.di.component.DaggerRestComponent;
import com.weatherclient.di.module.RestModule;
import com.weatherclient.android.model.WeatherParameter;
import com.weatherclient.android.rest.RestService;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.weatherclient.utils.ProgressDialogUtils.createProgressDialog;

/**
 * Created by Przemysław Książek
 * on 2017-02-17.
 */
public class MainActivityPresenter {

    @Inject
    RestService service;

    private WeatherParameterObserver observer = new WeatherParameterObserver();

    private final MainView activity;

    public MainActivityPresenter(MainView activity) {
        this.activity = activity;
    }

    private void createRestModule(){
        DaggerRestComponent.builder()
                .restModule(new RestModule())
                .applicationComponent(WeatherSystemApplication
                        .get(activity.getContext()).getApplicationComponent())
                .build()
                .inject(this);
    }

    public void getWeatherParameters() {
        createRestModule();

        service.getWeatherParameters()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private final class WeatherParameterObserver implements Observer<List<WeatherParameter>> {
        private long start;
        private ProgressDialog progressDialog;

        @Override
        public void onSubscribe(Disposable d) {
            progressDialog = createProgressDialog(activity.getContext());
            progressDialog.show();
            start = System.currentTimeMillis();
        }

        @Override
        public void onNext(List<WeatherParameter> value) {
            activity.setCharts(value);
        }

        @Override
        public void onError(Throwable e) {
            activity.setText(String.format("Error: %s", e.getMessage()));
            progressDialog.dismiss();
        }

        @Override
        public void onComplete() {
            long end = System.currentTimeMillis() - start;
            activity.setText(String.format(Locale.ENGLISH, "Time: %d\n", end));
            progressDialog.dismiss();
        }
    }
}
