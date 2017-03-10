package com.weatherclient.utils.task;

import android.content.Context;

import com.weatherclient.utils.NetworkUtils;

/**
 * Created by Przemysław Książek
 * on 2017-03-09.
 */

public class NetworkTask {

    private TaskRunnable taskRunnable;

    public NetworkTask(TaskRunnable taskRunnable) {
        this.taskRunnable = taskRunnable;
    }

    public void perform(Context context) {
        if (NetworkUtils.isNetworkAvailable(context)) {
            taskRunnable.onPerform();
        } else {
            taskRunnable.onError();
        }
    }
}
