package com.server.task;

import com.database.mongodb.repository.WeatherParameterRepo;
import com.entity.model.WeatherParameter;
import com.server.utils.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by PRZEMEK on 2017-02-17.
 */
@Component
@RestController
public class SendDataTask extends AbstractTask {

    @Autowired
    private WeatherParameterRepo repo;

    @Override
    @RequestMapping("/send")
    public Object run() {
        return JsonUtils.toJson(getResultList());
    }

    private List<List<WeatherParameter>> getResultList() {
        List<List<WeatherParameter>> result = new ArrayList<>();
        List<String> uniqueDeviceNames = getUniqueDeviceNames(repo.findAll());
        for(String deviceName : uniqueDeviceNames) {
            result.add(getListByDeviceName(deviceName));
        }
        return result;
    }

    private List<WeatherParameter> getListByDeviceName(String deviceName) {
        List<WeatherParameter> wpList = new ArrayList<>();
        for(WeatherParameter wp : repo.findAll()) {
            if(wp.getDeviceName().equals(deviceName)) {
                wpList.add(wp);
            }
        }
        return wpList;
    }

    private List<String> getUniqueDeviceNames(List<WeatherParameter> wpList) {
        Set<String> uniqueNames = new HashSet<>();
        for(WeatherParameter weatherParameter: wpList) {
            uniqueNames.add(weatherParameter.getDeviceName());
        }
        return new ArrayList<>(uniqueNames);
    }

}
