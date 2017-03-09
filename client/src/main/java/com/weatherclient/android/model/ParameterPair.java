package com.weatherclient.android.model;

import java.util.Date;

/**
 * Created by Przemysław Książek
 * on 2017-03-07.
 */

public class ParameterPair {

    private Date x;
    private Double y;

    public ParameterPair(Date x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Date getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}
