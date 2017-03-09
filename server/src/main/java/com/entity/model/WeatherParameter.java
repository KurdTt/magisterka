package com.entity.model;


import com.server.utils.DateUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Przemek on 2016-12-15.
 */
@Document
public class WeatherParameter {

    @Id
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
        return String.format("ID:%s, DeviceName: %s, Temp: %f C, pressure: %f hPa, pollination: %f µg/m3, Date: %s",
                id, deviceName, temperature, pressure, pollination, DateUtils.format(timestamp));
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
}
