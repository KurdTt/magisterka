package com.server.task;

import com.database.mongodb.repository.WeatherParameterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Przemek on 2016-12-16.
 */
@Component
@RestController
public class DeleteAllDataTask extends AbstractTask {

    @Autowired
    private WeatherParameterRepo repo;

    @Override
    @RequestMapping("/delete")
    public Object run() {
        repo.deleteAll();
        return "Usunięto wszystkie rekordy z bazy danych.";
    }
}
