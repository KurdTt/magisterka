package com.weatherclient.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Przemysław Książek
 on 2017-02-05.
 */

public final class ToastUtils {

    public static void shortToast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

}
