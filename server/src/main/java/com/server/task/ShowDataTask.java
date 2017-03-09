package com.server.task;

import com.database.mongodb.repository.WeatherParameterRepo;
import com.entity.model.WeatherParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowDataTask extends AbstractTask {

    @Autowired
    private WeatherParameterRepo repo;

    @Override
    public Object run() {
        StringBuilder builder = new StringBuilder();
        for(WeatherParameter wp : repo.findAll())
            builder.append(wp + "<br />");
        return builder.toString();
    }
}
