package com.server.task;

import com.database.mongodb.repository.WeatherParameterRepo;
import com.server.utils.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by PRZEMEK on 2017-02-17.
 */
@Component
public class SendDataTask extends AbstractTask {

    @Autowired
    private WeatherParameterRepo repo;

    @Override
    public Object run() {
        return JsonUtils.toJson(repo.findAll());
    }
}
