package com.server.utils;

import com.entity.model.WeatherParameter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {

    private static final Gson gson = new Gson();

    public static String toJson(WeatherParameter weatherParameter) {
        return gson.toJson(weatherParameter);
    }

    public static String toJson(List<WeatherParameter> list) {
        return gson.toJson(list);
    }
}
