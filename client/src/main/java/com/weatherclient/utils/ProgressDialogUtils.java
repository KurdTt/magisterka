package com.weatherclient.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Przemysław Książek
 * on 2017-02-17.
 */

public final class ProgressDialogUtils {

    public static ProgressDialog createProgressDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Proszę czekać...");
        progressDialog.setCancelable(false);
        return progressDialog;
    }

}
