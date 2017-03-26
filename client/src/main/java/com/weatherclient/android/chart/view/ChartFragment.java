package com.weatherclient.android.chart.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.weatherclient.R;
import com.weatherclient.android.chart.utils.ChartColors;
import com.weatherclient.android.chart.utils.WeatherChartFactory;
import com.weatherclient.android.model.WeatherParameter;
import com.weatherclient.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Przemysław Książek
 * on 2017-03-13.
 */

public class ChartFragment extends Fragment {

    private static final String WEATHER_PARAMETER_LIST = "wpList";

    @BindView(R.id.chartPanel)
    LinearLayout chartPanel;

    public static ChartFragment newInstance(List<WeatherParameter> wpList) {
        ChartFragment fragment = new ChartFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(WEATHER_PARAMETER_LIST, (ArrayList<WeatherParameter>) wpList);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<WeatherParameter> weatherParameterList =
                getArguments().getParcelableArrayList(WEATHER_PARAMETER_LIST);

        addCharts(weatherParameterList);
    }

    public void addCharts(List<WeatherParameter> wpList) {
        clearCharts();

        WeatherChartFactory.createChart(chartPanel,
                ArrayUtils.getTemperatureList(wpList), getString(R.string.temperature), ChartColors.TEMPERATURE_COLOR);
        WeatherChartFactory.createChart(chartPanel,
                ArrayUtils.getPressureList(wpList), getString(R.string.pressure), ChartColors.PRESSURE_COLOR);
        WeatherChartFactory.createChart(chartPanel,
                ArrayUtils.getPollinationList(wpList), getString(R.string.pollination), ChartColors.POLLINATION_COLOR);
    }

    public void clearCharts() {
        chartPanel.removeAllViews();
    }
}
