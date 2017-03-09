package com.server.task;

import com.database.mongodb.repository.WeatherParameterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Przemek on 2016-12-16.
 */
@Component
public class DeleteAllDataTask extends AbstractTask {

    @Autowired
    private WeatherParameterRepo repo;

    @Override
    public Object run() {
        repo.deleteAll();
        return "Usunięto wszystkie rekordy z bazy danych.";
    }
}
