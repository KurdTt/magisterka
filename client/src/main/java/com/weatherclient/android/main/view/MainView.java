package com.weatherclient.android.main.view;

import com.weatherclient.android.activity.IContext;
import com.weatherclient.android.model.WeatherParameter;

import java.util.List;

/**
 * Created by Przemysław Książek
 * on 2017-03-09.
 */

public interface MainView extends IContext {

    void onDataLoaded(List<List<WeatherParameter>> wpList);

    void noDataFound();

    void clearCharts();
}
