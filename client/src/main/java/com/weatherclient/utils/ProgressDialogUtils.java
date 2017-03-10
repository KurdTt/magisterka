package com.weatherclient.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.weatherclient.R;

/**
 * Created by Przemysław Książek
 * on 2017-02-17.
 */

public final class ProgressDialogUtils {

    public static ProgressDialog createProgressDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.pleaseWait));
        progressDialog.setCancelable(false);
        return progressDialog;
    }

}
