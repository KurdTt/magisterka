package com.server.task;

import com.database.mongodb.repository.WeatherParameterRepo;
import com.entity.generator.WeatherParameterGenerator;
import com.entity.model.WeatherParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Przemek on 2016-12-16.
 */
@Component
@RestController
public class InsertTestDataTask extends AbstractTask {

    private static final int N = 200;

    @Autowired
    private WeatherParameterRepo repo;

    @Override
    @RequestMapping("/insert")
    public Object run() {
        WeatherParameterGenerator generator = WeatherParameterGenerator.getInstance();
        List<List<WeatherParameter>> list = generator.generate(N);

        for (List<WeatherParameter> singleList : list)
            repo.insert(singleList);

        return String.format("Dodano %d rekordów", N * list.size());
    }

}
