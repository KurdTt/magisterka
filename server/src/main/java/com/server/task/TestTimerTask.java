package com.server.task;

import com.database.mongodb.repository.WeatherParameterRepo;
import com.entity.generator.WeatherParameterGenerator;
import com.server.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

@Component
@RestController
public class TestTimerTask extends TimerTask {

    private static final Logger LOGGER = Logger.getLogger( TestTimerTask.class.getName() );

    @Autowired
    private WeatherParameterRepo repo;

    private AtomicBoolean start = new AtomicBoolean(false);
    private WeatherParameterGenerator generator = WeatherParameterGenerator.getInstance();

    @Override
    @RequestMapping("/test_task")
    public void run() {
        if(start.get()) {
            repo.insert(generator.generate("Poddasze"));
            LOGGER.info("Czas: " + DateUtils.format(new Date()));
        }
    }

    public void setStart(boolean newValue) {
        this.start.set(newValue);
    }
}
