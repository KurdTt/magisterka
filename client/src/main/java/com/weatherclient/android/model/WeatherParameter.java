package com.weatherclient.android.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Przemysław Książek
 on 2016-12-15.
 */

public class WeatherParameter implements Parcelable {

    private String id;
    private String deviceName;
    private Double temperature;
    private Double pressure;
    private Double pollination;
    private Date timestamp;

    public WeatherParameter() { }

    public WeatherParameter(String deviceName, Double temperature, Double pressure, Double pollination, Date timestamp) {
        this.deviceName = deviceName;
        this.temperature = temperature;
        this.pressure = pressure;
        this.pollination = pollination;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("DeviceName: %s, temperature: %f, pressure: %f\n", deviceName, temperature, pressure);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getPollination() {
        return pollination;
    }

    public void setPollination(Double pollination) {
        this.pollination = pollination;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.deviceName);
        dest.writeValue(this.temperature);
        dest.writeValue(this.pressure);
        dest.writeValue(this.pollination);
        dest.writeLong(this.timestamp != null ? this.timestamp.getTime() : -1);
    }

    protected WeatherParameter(Parcel in) {
        this.id = in.readString();
        this.deviceName = in.readString();
        this.temperature = (Double) in.readValue(Double.class.getClassLoader());
        this.pressure = (Double) in.readValue(Double.class.getClassLoader());
        this.pollination = (Double) in.readValue(Double.class.getClassLoader());
        long tmpTimestamp = in.readLong();
        this.timestamp = tmpTimestamp == -1 ? null : new Date(tmpTimestamp);
    }

    public static final Parcelable.Creator<WeatherParameter> CREATOR = new Parcelable.Creator<WeatherParameter>() {
        @Override
        public WeatherParameter createFromParcel(Parcel source) {
            return new WeatherParameter(source);
        }

        @Override
        public WeatherParameter[] newArray(int size) {
            return new WeatherParameter[size];
        }
    };
}
