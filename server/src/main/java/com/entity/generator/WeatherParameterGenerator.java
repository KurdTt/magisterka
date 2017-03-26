package com.entity.generator;

import com.entity.model.WeatherParameter;

import java.util.*;

public class WeatherParameterGenerator {

    private static final String[] NAMES = {
            "Dom", "Firma", "Ogród"
    };

    private static WeatherParameterGenerator instance = null;
    private static final Random random = new Random();

    private WeatherParameterGenerator() {
    }

    public static WeatherParameterGenerator getInstance() {
        if (instance == null) {
            instance = new WeatherParameterGenerator();
        }
        return instance;
    }

    public List<List<WeatherParameter>> generate(int count){
        List<List<WeatherParameter>> result = new ArrayList<>();

        for (String name : NAMES) {
            result.add(generate(name, count));
        }

        return result;
    }

    public List<WeatherParameter> generate(String deviceName, int count) {
        List<WeatherParameter> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < count; i++) {
            calendar.add(Calendar.SECOND, 60);
            list.add(new WeatherParameter(
                    deviceName,
                    nextDouble(20, 1),
                    nextDouble(1000, 20),
                    nextDouble(100, 10),
                    calendar.getTime()));
        }
        return list;
    }

    public WeatherParameter generate(String deviceName) {
        return new WeatherParameter(
                deviceName,
                nextDouble(20, 1),
                nextDouble(1000, 20),
                nextDouble(100, 10),
                new Date());
    }

    public Double nextDouble(int start, int range) {
        return random.nextDouble() * range + start;
    }
}
