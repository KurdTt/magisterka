package com.weatherclient.android.preferences.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import com.weatherclient.R;
import com.weatherclient.android.application.WeatherSystemApplication;
import com.weatherclient.android.preferences.model.SettingsPreferences;

import javax.inject.Inject;

import static com.weatherclient.android.preferences.model.SettingsPreferences.TEST_VALUES;
import static com.weatherclient.android.preferences.model.SettingsPreferences.URL_KEY;

/**
 * This fragment shows general preferences only. It is used when the
 * activity is showing a two-pane settings UI.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class GeneralPreferenceFragment extends PreferenceFragment {

    @Inject
    SettingsPreferences settingsPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        inject();

        setHasOptionsMenu(true);

        bindPreferenceSummaryToValue(findPreference(URL_KEY));
        bindPreferenceSummaryToValue(findPreference(TEST_VALUES));
    }

    private void inject() {
        WeatherSystemApplication.get(getActivity())
                .getApplicationComponent()
                .inject(this);
    }

    private Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener
            = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            if (preference instanceof SwitchPreference) {
                boolean booleanValue = Boolean.valueOf(stringValue);
                settingsPreferences.setBoolean(preference.getKey(), booleanValue);
            } else {
                preference.setSummary(stringValue);
                settingsPreferences.setString(preference.getKey(), stringValue);
            }

            return true;
        }
    };

    private void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);
        setSummary(preference);
    }

    private void setSummary(Preference preference) {
        if (!(preference instanceof SwitchPreference))
            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                    settingsPreferences.getString(preference.getKey()));
    }
}
