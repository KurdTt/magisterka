package com.weatherclient.android.preferences.model;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Przemysław Książek
 * on 2017-02-27.
 */

public class SettingsPreferences {

    public static final String URL_KEY = "serverUrl";
    public static final String TEST_VALUES = "testValues";

    private static final String URL_DEFAULT_VALUE = "http://10.10.61.19:9999";

    private final SharedPreferences preferences;
    private final Editor prefsEditor;

    public SettingsPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
        prefsEditor = preferences.edit();
        prefsEditor.commit();
    }

    public String getUrl() {
        return preferences.getString(URL_KEY, URL_DEFAULT_VALUE);
    }

    public boolean isTestMode() {
        return preferences.getBoolean(TEST_VALUES, false);
    }

    public String getString(String key) {
        return preferences.getString(key, StringUtils.EMPTY);
    }

    public void setString(String key, String value) {
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public void setBoolean(String key, boolean value) {
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }
}
