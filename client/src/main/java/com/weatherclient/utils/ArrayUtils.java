package com.weatherclient.utils;

import com.weatherclient.android.model.ParameterPair;
import com.weatherclient.android.model.WeatherParameter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Przemysław Książek
 * on 2017-02-18.
 */

public final class ArrayUtils {

    private static List<Double> getTemperature(List<WeatherParameter> list){
        List<Double> result = new ArrayList<>();
        for(WeatherParameter wp : list) {
            result.add(wp.getTemperature());
        }
        return result;
    }

    private static List<Double> getPressure(List<WeatherParameter> list){
        List<Double> result = new ArrayList<>();
        for(WeatherParameter wp : list) {
            result.add(wp.getPressure());
        }
        return result;
    }

    private static List<Double> getPollination(List<WeatherParameter> list){
        List<Double> result = new ArrayList<>();
        for(WeatherParameter wp : list) {
            result.add(wp.getPollination());
        }
        return result;
    }

    private static List<Date> getTimestamp(List<WeatherParameter> list){
        List<Date> result = new ArrayList<>();
        for(WeatherParameter wp : list) {
            result.add(wp.getTimestamp());
        }
        return result;
    }

    public static List<ParameterPair> getTemperatureList(List<WeatherParameter> list){
        return createPairList(getTimestamp(list), getTemperature(list));
    }

    public static List<ParameterPair> getPressureList(List<WeatherParameter> list){
        return createPairList(getTimestamp(list), getPressure(list));
    }

    public static List<ParameterPair> getPollinationList(List<WeatherParameter> list){
        return createPairList(getTimestamp(list), getPollination(list));
    }

    private static List<ParameterPair> createPairList(List<Date> timestamps, List<Double> values){
        if(timestamps.size() != values.size())
            return null;
        else {
            List<ParameterPair> parameterPairs = new ArrayList<>();

            for(int i = 0; i < values.size(); i++){
                Date timestamp = timestamps.get(i);
                Double value = values.get(i);
                parameterPairs.add(new ParameterPair(timestamp, value));
            }

            return parameterPairs;
        }
    }

}
