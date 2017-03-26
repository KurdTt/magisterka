package com.server.task;

import com.database.mongodb.repository.WeatherParameterRepo;
import com.entity.model.WeatherParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class ShowDataTask {

    @Autowired
    private WeatherParameterRepo repo;

    @RequestMapping("/show")
    public Object run() {
        StringBuilder builder = new StringBuilder();
        for(WeatherParameter wp : repo.findAll())
            builder.append(wp + "<br />");
        return builder.toString();
    }
}
