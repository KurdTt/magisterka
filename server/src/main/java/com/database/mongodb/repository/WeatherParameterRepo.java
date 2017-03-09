package com.database.mongodb.repository;

import com.entity.model.WeatherParameter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Przemek on 2016-12-15.
 */
@Repository
public interface WeatherParameterRepo extends MongoRepository<WeatherParameter, Integer> {
}
