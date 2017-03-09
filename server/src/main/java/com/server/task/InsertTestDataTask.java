package com.server.task;

import com.database.mongodb.repository.WeatherParameterRepo;
import com.entity.generator.WeatherParameterGenerator;
import com.entity.model.WeatherParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Przemek on 2016-12-16.
 */
@Component
public class InsertTestDataTask extends AbstractTask {

    private static final int N = 720; // 12h co 10 sekund

    @Autowired
    private WeatherParameterRepo repo;

    @Override
    public Object run() {
        WeatherParameterGenerator generator = WeatherParameterGenerator.getInstance();
        List<WeatherParameter> list = generator.generate("Dom", N);
        repo.insert(list);
        return String.format("Dodano %d rekordów", N);
    }

}
