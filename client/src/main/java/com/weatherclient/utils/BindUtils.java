package com.weatherclient.utils;

import android.widget.TextView;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Przemysław Książek
 on 2017-02-04.
 */

public final class BindUtils {

    public static void bindString(String from, TextView to) {
        if(from == null)
            to.setText(StringUtils.EMPTY);
        if (!EqualsUtils.equals(from, ObjectUtils.toString(to.getText()))) {
            to.setText(from);
        }
    }
}
