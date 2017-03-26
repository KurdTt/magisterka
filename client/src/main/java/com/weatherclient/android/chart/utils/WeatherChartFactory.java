package com.weatherclient.android.chart.utils;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.weatherclient.android.model.ParameterPair;
import com.weatherclient.utils.formatter.HourAxisValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Przemysław Książek
 * on 2017-03-09.
 */

public class WeatherChartFactory {

    private static float CHART_HEIGHT = 160f;

    private static void setMinimumHeight(LineChart chart, ViewGroup view){
        DisplayMetrics metrics = view.getResources().getDisplayMetrics();
        chart.setMinimumHeight((int)(metrics.density * CHART_HEIGHT));
    }

    private static void setTitle(ViewGroup view, String title){
        DisplayMetrics metrics = view.getResources().getDisplayMetrics();
        TextView textView = new TextView(view.getContext());

        textView.setText(title);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize((int)(metrics.density * 8f));
        textView.setPadding(0, 0, 0, 40);

        view.addView(textView);
    }

    private static void setChartOptions(LineChart chart, LineData data, long startDate){
        chart.getXAxis().mLabelWidth = 15;
        chart.getXAxis().setLabelCount(3);

        chart.getXAxis().setValueFormatter(new HourAxisValueFormatter(startDate));
        chart.setData(data);

        chart.setDescription(null);
        chart.getLegend().setEnabled(false);
    }

    public static void createChart(ViewGroup view, List<ParameterPair> wpList, String title, int color){
        LineChart testChart = new LineChart(view.getContext());

        setTitle(view, title);
        setMinimumHeight(testChart, view);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(wpList.get(0).getX());

        long startDate = calendar.getTimeInMillis();

        ArrayList<Entry> entries = new ArrayList<>();

        for (ParameterPair wp : wpList) {
            calendar.setTime(wp.getX());
            long current = calendar.getTimeInMillis();
            long x = current - startDate;
            float y = wp.getY().floatValue();
            entries.add(new Entry(x, y));
        }

        LineDataSet dataSet = new LineDataSet(entries, title);

        dataSet.setColor(color);
        dataSet.setDrawCircles(true);
        dataSet.setDrawCircleHole(true);
        dataSet.setCircleColor(color);
        dataSet.setValueTextSize(9f);

        LineData data = new LineData(dataSet);

        setChartOptions(testChart, data, startDate);

        view.addView(testChart);
        testChart.invalidate();
    }

}
