package com.weatherclient.android.main.presenter;

import android.app.ProgressDialog;

import com.weatherclient.R;
import com.weatherclient.android.application.WeatherSystemApplication;
import com.weatherclient.android.main.view.MainActivity;
import com.weatherclient.android.main.view.MainView;
import com.weatherclient.android.model.WeatherParameter;
import com.weatherclient.android.model.generator.WeatherParameterGenerator;
import com.weatherclient.android.preferences.model.SettingsPreferences;
import com.weatherclient.android.rest.RestService;
import com.weatherclient.di.component.DaggerRestComponent;
import com.weatherclient.di.module.RestModule;
import com.weatherclient.utils.ToastUtils;
import com.weatherclient.utils.task.NetworkTask;
import com.weatherclient.utils.task.TaskRunnable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
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
    @Inject
    SettingsPreferences preferences;

    private DataCreatorObserver observer = new DataCreatorObserver();
    private NetworkTask downloadTask = new NetworkTask(new OnDownloadDataTask());

    private final MainView activity;

    public MainActivityPresenter(MainActivity activity) {
        this.activity = activity;
        inject();
    }

    private void inject() {
        DaggerRestComponent.builder()
                .restModule(new RestModule())
                .applicationComponent(WeatherSystemApplication
                        .get(activity.getContext()).getApplicationComponent())
                .build()
                .inject(this);

        preferences.isTestMode();
    }

    public void getWeatherParameters() {
        if (preferences.isTestMode()) {
            createTestValues();
        } else
            downloadTask.perform(activity.getContext());
    }

    private void createTestValues() {
        WeatherParameterGenerator generator = WeatherParameterGenerator.getInstance();
        Observable.just(generator.generate(100))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private final class OnDownloadDataTask implements TaskRunnable {

        @Override
        public void onPerform() {
            service.getWeatherParameters()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

        @Override
        public void onError() {
            activity.noDataFound();
            ToastUtils.longToast(activity.getContext(), R.string.noInternetConnection);
        }
    }

    private final class DataCreatorObserver implements Observer<List<List<WeatherParameter>>> {
        private ProgressDialog progressDialog;

        @Override
        public void onSubscribe(Disposable d) {
            progressDialog = createProgressDialog(activity.getContext());
            progressDialog.show();
        }

        @Override
        public void onNext(List<List<WeatherParameter>> value) {
            activity.onDataLoaded(value);
        }

        @Override
        public void onError(Throwable e) {
            ToastUtils.longToast(activity.getContext(),
                    activity.getContext().getString(R.string.connectionError, preferences.getUrl()));
            activity.noDataFound();
            progressDialog.dismiss();
        }

        @Override
        public void onComplete() {
            progressDialog.dismiss();
        }
    }
}
