package com.weatherclient.utils.task;

/**
 * Created by Przemysław Książek
 * on 2017-03-09.
 */

public interface TaskRunnable {

    void onPerform();

    void onError();
}
